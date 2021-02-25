package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(EmployeServiceImpl.class);
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
		// return employeRepository.getEmployeByEmailAndPassword(login, password);
		return null;
	}

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		logger.info("In method addOrUpdateEmploye");
		int empID = -1;
		try {
			employeRepository.save(employe);
			empID = employe.getId();
			logger.info("Out of method addOrUpdateEmploye");
		} catch (IllegalArgumentException e) {
			logger.error("Out of method addOrUpdateEmploye with Errors : " + e);
		} finally {
			logger.info("Out of method addOrUpdateEmploye");

		}
		return empID;
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		logger.info("In method mettreAjourEmailByEmployeId");
		try {
			Optional<Employe> employeDB = employeRepository.findById(employeId);
			if (employeDB.isPresent()) {
				employeDB.get().setEmail(email);
				employeRepository.save(employeDB.get());
			}
			logger.info("Out of method mettreAjourEmailByEmployeId");
		} catch (IllegalArgumentException e) {
			logger.error("Out of method mettreAjourEmailByEmployeId with Errors : " + e);
		} finally {
			logger.info("Out of method mettreAjourEmailByEmployeId");

		}

	}

	public String getEmployePrenomById(int employeId) {
		logger.info("In method getEmployePrenomById");
		Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);
		logger.info("Out of method getEmployePrenomById");
		return employeManagedEntity.isPresent() ? employeManagedEntity.get().getPrenom() : "Employe ID does not exist";
	}

	public void deleteEmployeById(int employeId) {
		logger.info("In method deleteEmployeById");
		try {
			Optional<Employe> employe = employeRepository.findById(employeId);

			// Desaffecter l'employe de tous les departements
			// c'est le bout master qui permet de mettre a jour
			// la table d'association
			logger.info("Out of method deleteEmployeById");
			if (!employe.isPresent()) {
				logger.error("ID does not Exist");
				throw new IllegalArgumentException("ID does not Exist");
			}
			for (Departement dep : employe.get().getDepartements()) {
				dep.getEmployes().remove(employe.get());
			}

			employeRepository.delete(employe.get());
		} catch (IllegalArgumentException e) {
			logger.error("Out of method deleteEmployeById with Errors : " + e);
		} finally {
			logger.info("Out of method deleteEmployeById");

		}
	}

	public List<Employe> getAllEmployes() {
		logger.info("In method getAllEmployes");
		return (List<Employe>) employeRepository.findAll();
	}

	@Override
	public Employe findByID(int id) {
		logger.info("In method findByID");
		Optional<Employe> employee = employeRepository.findById(id);
		if (!employee.isPresent()) {
			logger.info("No result find for this ID : " + id);
			return null;
		}
		return employee.get();

	}

}
