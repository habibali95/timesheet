package tn.esprit.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;

@SpringBootTest
public class EntrepriseServiceTest {
	
	@Inject
	private IEntrepriseService entrepriseService;
	
	@Test
	public void ajouterEntrepriseTest(){
		Entreprise en = new Entreprise();
		en.setName("VERMEG");
		en.setRaisonSocial("IT");
		int addedEntrepriseId = entrepriseService.ajouterEntreprise(en);
		assertNotNull(addedEntrepriseId);
		assertTrue(addedEntrepriseId > 0);
	}
	
	@Test
	public void getEntrepriseByIdTest(){
		Entreprise en = entrepriseService.getEntrepriseById(1);
		assertNotNull(en);
	}
}
