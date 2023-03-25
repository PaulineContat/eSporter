package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Compte;
import modele.Personne;

public class testPersonne {
	
	private Personne pers;
	
	@Before
	public void setUp() {
		this.pers = new Personne("Chevalier", "Max", "0203040506", "abc123");
	}
	
	@Test
	public void testConstructeurNom() {
		assertEquals(this.pers.getNom(), "Chevalier");
	}
	
	@Test
	public void testConstructeurPrenom() {
		assertEquals(this.pers.getPrenom(), "Max");
	}

	@Test
	public void testConstructeurTel() {
		assertEquals(this.pers.getTel(), "0203040506");
	}
	
	@Test
	public void testSetNom() {
		this.pers.setNom("unnom");
		assertEquals(this.pers.getNom(), "unnom");
	}
	
	@Test
	public void testSetPrenom() {
		this.pers.setPrenom("unprenom");
		assertEquals(this.pers.getPrenom(), "unprenom");
	}
	
	@Test
	public void testSetTel() {
		this.pers.setNom("0102030405");
		assertEquals(this.pers.getNom(), "0102030405");
	}
	
}
