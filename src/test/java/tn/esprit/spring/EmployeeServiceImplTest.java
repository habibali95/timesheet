package tn.esprit.spring;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;

 

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeServiceImplTest {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(EmployeeServiceImplTest.class);

	@Inject
	private IEmployeService employeeService;

	@Test
    @Order(1)    
	public void testAddOrUpdateEmploye() {
		logger.info("Start Test  method addOrUpdateEmploye");
		// TODO Auto-generated method stub
		Employe employe = new Employe();
		employe.setId(2);
		employe.setNom("Test" + System.currentTimeMillis());
		employe.setRole(Role.ADMINISTRATEUR);
		logger.info(employeeService.addOrUpdateEmploye(employe));
		assertTrue(employeeService.addOrUpdateEmploye(employe) > 0);
		logger.info("End Test  method addOrUpdateEmploye" + employeeService.findByID(2));
	}

	@Test
    @Order(2)    
	public void testMettreAjourEmailByEmployeId() {
		logger.info("Start Test  method mettreAjourEmailByEmployeId");
		String email = "jubran@hotmail.com";
		int employeId = 2;
		employeeService.mettreAjourEmailByEmployeId(email, employeId);
		logger.info("############################################" + employeeService.findByID(2));

		assertEquals(employeeService.findByID(2).getEmail(), email);
		logger.info("End Test  method mettreAjourEmailByEmployeId");
	}

	@Test
    @Order(3)    
	public void testDeleteEmployeById() {		
		logger.info("Start Test  method deleteEmployeById");
		int employeId = 2;
		employeeService.deleteEmployeById(employeId);
		assertEquals(employeeService.findByID(2), null);
		logger.info("End Test  method deleteEmployeById");

	}

}
