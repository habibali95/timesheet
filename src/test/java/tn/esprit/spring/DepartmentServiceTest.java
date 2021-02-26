//package tn.esprit.spring;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import tn.esprit.spring.entities.Departement;
//import tn.esprit.spring.entities.Entreprise;
//import tn.esprit.spring.services.IDepartementService;
//import tn.esprit.spring.services.IEntrepriseService;
//
///**
// * @author ahabib
// *
// */
//@SpringBootTest
//public class DepartmentServiceTest {
//	private static final Logger l = LogManager.getLogger(DepartmentServiceTest.class);
//	@Autowired
//	IDepartementService deptService;
//	@Autowired
//	IEntrepriseService enttService;
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	void testAddDepartment() throws Exception {
//		l.info("starting test add department");
//
//		Departement dept = new Departement();
//		dept.setName("CITest");
//		Entreprise ent = enttService.getEntrepriseById(1);
//		if (ent == null) {
//			throw new Exception("Ent is null !!!");
//		}
//		dept.setEntreprise(ent);
//		Departement deptAdded = deptService.addDepartement(dept);
//		assertEquals(dept.getName(), deptAdded.getName());
//		l.info("finished test add department");
//
//	}
//
//	@Test
//	void testFindDepartment() throws Exception {
//		l.info("starting test find department");
//
//		Departement dept = deptService.getDepartementById(1);
//		assertNotNull(dept);
//		l.info("finished test find department");
//
//	}
//	
//	@Test
//	void testUpdateDepartment() throws Exception {
//		l.info("starting test update department");
//
//		Departement dept = deptService.getDepartementById(1);
//		assertNotNull(dept);
//		dept.setName("updatedName");
//		Departement deptUpdated = deptService.updateDepartement(dept);
//		assertEquals(deptUpdated.getName(), dept.getName());
//		l.info("finished test update department");
//
//	}
//	
//	@Test
//	void testDeleteDepartment() throws Exception {
//		l.info("starting test delete department");
//		 deptService.deletetDepartementById(3);
//		Departement dept = deptService.getDepartementById(3);
//		assertNull(dept);
//		
//		l.info("finished test delete department");
//	}
//
//}
