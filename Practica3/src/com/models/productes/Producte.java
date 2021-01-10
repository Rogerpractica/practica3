package com.models.productes;

public abstract class Producte { //abstract: per a classes pare, no es podran instanciar objectes.
	private String id;
	private String nom;
	private long idProductor;
	
	/**
	 * Constructor buit.
	 */
	public Producte() {
		
	}
	/**
	 * Metode Constructor
	 * @param id
	 * @param nom
	 * @param idProductor
	 */
	public Producte(String id, String nom, long idProductor) {
		this.id = id;
		this.nom = nom;
		this.idProductor = idProductor;
	}
	
	/**
	 * Metode getter id
	 * @return
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public long getIdProductor() {
		return idProductor;
	}
	public void setIdProductor(long idProductor) {
		this.idProductor = idProductor;
	}
	
}
