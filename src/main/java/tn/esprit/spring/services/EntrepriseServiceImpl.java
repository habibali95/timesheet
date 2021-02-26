package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(EmployeServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		logger.info("In method ajouterEntreprise");
		entrepriseRepoistory.save(entreprise);
		logger.info("Out method ajouterEntreprise");
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		logger.info("In method ajouterDepartement");
		deptRepoistory.save(dep);
		logger.info("Out method ajouterDepartement");
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		logger.info("In method affecterDepartementAEntreprise");
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();		
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		logger.info("Out method affecterDepartementAEntreprise");
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		logger.info("In method getAllDepartementsNamesByEntreprise");
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		logger.info("Out method getAllDepartementsNamesByEntreprise");
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		logger.info("In method deleteEntrepriseById");
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
		logger.info("Out method deleteEntrepriseById");
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		logger.info("In method deleteDepartementById");
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
		logger.info("In method deleteDepartementById");
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		logger.info("In method getEntrepriseById");
		logger.info("Out method getEntrepriseById");
		return entrepriseRepoistory.findById(entrepriseId).orElse(null);
		
	}

}
