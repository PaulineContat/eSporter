package test;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import modele.Compte;

public class testCompte {
	
	Compte compte;
	
	@Before
	public void setUp() {
		compte = new Compte("test", "5a7e42bc6e6f87048ca5d5fe55b0d1eb");
	}

	@Test
	public void testConstructeurIdentifiant() throws NoSuchAlgorithmException {
		Compte compte = new Compte("ninjasinpyjamas", "5a7e42bc6e6f87048ca5d5fe55b0d1eb");
		assertEquals(compte.getIdentifiant(), "ninjasinpyjamas");
	}
	
	@Test
	public void testMDPHash() throws NoSuchAlgorithmException {
		String mdp = "mdp123";
		
		MessageDigest md = MessageDigest.getInstance("MD5");

    	md.update(mdp.getBytes());

    	byte[] bytes = md.digest();

    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < bytes.length; i++) {
    		sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
    	}
    	
    	mdp = sb.toString();
    	
    	assertEquals(mdp, "5a7e42bc6e6f87048ca5d5fe55b0d1eb");
	}
	
	@Test
	public void testConnexionBonMDP() {
		assertEquals(this.compte.connexion("mdp123"), 1);
	}
	
	@Test
	public void testConnexionMauvaisMDPUnEssai() {
		assertEquals(this.compte.connexion("pouletfrit321"), 0);
	}
	
	@Test
	public void testConnexionMauvaisMDP4Essai() {
		this.compte.connexion("pouletfrit321");
		this.compte.connexion("pouletfrit321");
		this.compte.connexion("pouletfrit321");
		assertEquals(this.compte.connexion("pouletfrit321"), -1);
	}
	
	@Test
	public void testSetIdentifiant() {
		this.compte.setIdentifiant("test2");
		assertEquals(this.compte.getIdentifiant(), "test2");
	}
	
	@Test
	public void testSetMDP() {
		this.compte.setMdp("pouletfrit321");
		assertEquals(this.compte.connexion("pouletfrit321"), 1);
	}
}
