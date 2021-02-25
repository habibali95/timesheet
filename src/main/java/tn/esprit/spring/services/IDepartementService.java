package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;

public interface IDepartementService {

	public List<Departement> getAllDepartements();

	public Departement addDepartement(Departement d);

	public Departement updateDepartement(Departement d);

	public Departement getDepartementById(Integer id);

	public void deletetDepartementById(Integer id);

}
