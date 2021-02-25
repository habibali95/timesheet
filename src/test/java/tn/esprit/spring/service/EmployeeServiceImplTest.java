package tn.esprit.spring.service;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;

@SpringBootTest
public class EmployeeServiceImplTest {

	@Inject
	private IEmployeService employeeService;

	@Test
	public void mettreAjourEmailByEmployeId() {
		String email = "jubran@hotmail.com";
		int employeId = 1;
		employeeService.mettreAjourEmailByEmployeId(email, employeId);
		assertTrue(employeeService.getAllEmployes().stream().filter(e -> e.getId() == employeId)
				.anyMatch(c -> c.getEmail().equals(email)));
	}

	@Test
	public void affecterEmployeADepartement() {
		int employeId = 1;
		int depId = 1;
		employeeService.affecterEmployeADepartement(employeId, depId);
		assertTrue(employeeService.getAllEmployes().stream().filter(e -> e.getId() == employeId)
				.map(e -> e.getDepartements()).flatMap(List::stream).collect(Collectors.toList()).stream()
				.anyMatch(e -> e.getId() == depId));
	}

	@Test
	public void deleteEmployeById() {
		int employeId = 1;
		employeeService.deleteEmployeById(employeId);
		assertTrue(employeeService.getEmployePrenomById(employeId) == null);

	}

	@Test
	public void addOrUpdateEmploye() {
		// TODO Auto-generated method stub
		Employe employe = new Employe();
		employe.setId(1);
		employe.setNom("Test");
		employe.setRole(Role.ADMINISTRATEUR);
		assertTrue(employeeService.addOrUpdateEmploye(employe) > 1);
	}
}
