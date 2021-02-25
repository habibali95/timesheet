package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {
	private static final Logger logger = Logger.getLogger(EmployeServiceImpl.class);

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	@Override
	public Employe authenticate(String login, String password) {
		return employeRepository.getEmployeByEmailAndPassword(login, password);
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		int empID = -1;
		try {
			employeRepository.save(employe);
			empID = employe.getId();
		} catch (IllegalArgumentException e) {
			logger.error(e);
		}
		return empID;
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		try {
			Optional<Employe> employeDB = employeRepository.findById(employeId);
			if (employeDB.isPresent()) {
				employeDB.get().setEmail(email);
				employeRepository.save(employeDB.get());
			}
		} catch (IllegalArgumentException e) {
			logger.error(e);
		}

	}

	public void affecterEmployeADepartement(int employeId, int depId) {
		try {
			Optional<Departement> departDB = deptRepoistory.findById(depId);
			Optional<Employe> employeDB = employeRepository.findById(employeId);

			if (departDB.isPresent() && employeDB.isPresent()) {
				// Employe et le fils (contient le mappedBy) donc :
				departDB.get().getEmployes().add(employeDB.get());
			}
		} catch (IllegalArgumentException e) {
			logger.error(e);
		}
	}

	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId) {
		try {
			Departement dep = deptRepoistory.findById(depId).get();
			Employe empl = employeRepository.findById(employeId).get();

			dep.getEmployes().remove(empl);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		}
//		int employeNb = dep.getEmployes().size();
//		for(int index = 0; index < employeNb; index++){
//			if(dep.getEmployes().get(index).getId() == employeId){
//				dep.getEmployes().remove(index);
//				break;//a revoir
//			}
//		}

	}

	// Tablesapce (espace disque)

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		try {
			Optional<Contrat> contratDB = contratRepoistory.findById(contratId);
			Optional<Employe> employeDB = employeRepository.findById(employeId);
			if (contratDB.isPresent() && employeDB.isPresent()) {

				contratDB.get().setEmploye(employeDB.get());
			}
		} catch (IllegalArgumentException e) {
			logger.error(e);
		}

	}

	public String getEmployePrenomById(int employeId) {
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		return employeManagedEntity.getPrenom();
	}

	public void deleteEmployeById(int employeId) {
		try {
			Employe employe = employeRepository.findById(employeId).get();

			// Desaffecter l'employe de tous les departements
			// c'est le bout master qui permet de mettre a jour
			// la table d'association
			for (Departement dep : employe.getDepartements()) {
				dep.getEmployes().remove(employe);
			}

			employeRepository.delete(employe);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		}
	}

	public void deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		contratRepoistory.delete(contratManagedEntity);

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}
