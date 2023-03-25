package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Ecurie;
import modele.Compte;

public class testEcurie {

	Ecurie ecurie;
	
	@Before
	public void setUp() {
		this.ecurie = new Ecurie("G2", "mdp123", null);
	}
	
	
	@Test
	public void testConstructeurEcurieNom() {
		assertEquals(this.ecurie.getNom(), "G2");
	}
	
	@Test
	public void testContructeurEcurieCompteMdp() {
		assertEquals(this.ecurie.getCompte().getMdp(), Compte.passwordHash("mdp123"));
	}
	
	@Test
	public void testContructeurEcurieCompteIdentifiant() {
		assertEquals(this.ecurie.getCompte().getIdentifiant(), "G2".toLowerCase().replaceAll("[^a-zA-Z0-9]", ""));
	}
	
	@Test
	public void testContructeurEquipes() {
		assertTrue(this.ecurie.getEquipes().isEmpty());
	}

}
