package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Employe;

public interface IEmployeService {

	public void mettreAjourEmailByEmployeId(String email, int employeId);

	public Employe authenticate(String login, String password);

	public String getEmployePrenomById(int employeId);

	public void deleteEmployeById(int employeId);

	public List<Employe> getAllEmployes();

	int addOrUpdateEmploye(Employe employe);

	Employe findByID(int id);

}
