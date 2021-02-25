package tn.esprit.spring;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;

@SpringBootTest
public class EmployeeServiceImplTest {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(EmployeeServiceImplTest.class);

	@Inject
	private IEmployeService employeeService;

	@Test
	public void testAddOrUpdateEmploye() {
		logger.info("Start Test  method addOrUpdateEmploye");
		// TODO Auto-generated method stub
		Employe employe = new Employe();
		employe.setId(2);
		employe.setNom("Test" + System.currentTimeMillis());
		employe.setRole(Role.ADMINISTRATEUR);
		logger.info(employeeService.addOrUpdateEmploye(employe));
		assertTrue(employeeService.addOrUpdateEmploye(employe) > 0);
		logger.info("End Test  method addOrUpdateEmploye");
	}

	@Test
	public void testMettreAjourEmailByEmployeId() {
		logger.info("Start Test  method mettreAjourEmailByEmployeId");
		String email = "jubran@hotmail.com";
		int employeId = 2;
		employeeService.mettreAjourEmailByEmployeId(email, employeId);
		assertTrue(employeeService.getAllEmployes().stream().filter(e -> e.getId() == employeId)
				.anyMatch(c -> c.getEmail().equals(email)));
		logger.info("End Test  method mettreAjourEmailByEmployeId");
	}

	@Test
	public void testDeleteEmployeById() {
		logger.info("Start Test  method deleteEmployeById");
		int employeId = 3;
		employeeService.deleteEmployeById(employeId);
		assertTrue(employeeService.findByID(3) == null);
		logger.info("End Test  method deleteEmployeById");

	}

}
