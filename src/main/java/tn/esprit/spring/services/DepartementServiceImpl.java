package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.DepartementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class DepartementServiceImpl implements IDepartementService {
	private static final Logger l = LogManager.getLogger(DepartementServiceImpl.class);

	@Autowired
	DepartementRepository deptRepository;

	public List<Departement> getAllDepartements() {
		l.info("In  getAllDepartements : ");
		List<Departement> depts = (List<Departement>) deptRepository.findAll();
		for (Departement dept : depts) {
			l.debug("department  : " + dept);
		}
		l.info("Out of getAllDepartements.");
		return depts;
	}

	@Override
	public Departement addDepartement(Departement d) {
		l.info("In  addDepartement : " + d);
		Departement dept = deptRepository.save(d);
		l.info("Out of  addDepartement. ");
		return dept;
	}

	@Override
	public Departement updateDepartement(Departement d) {
		l.info("In  updateDepartement : " + d);
		Departement dept = deptRepository.save(d);
		l.info("Out of  updateDepartement. ");
		return dept;
	}

	@Override
	public Departement getDepartementById(Integer id) {
		l.info("in  getDepartementById id = " + id);
		Departement u = deptRepository.findById(id).orElse(null);
		l.info("Departement returned : " + u);
		return u;
	}

	@Override
	public void deletetDepartementById(Integer id) {
		deptRepository.deleteById(id);

	}

}
