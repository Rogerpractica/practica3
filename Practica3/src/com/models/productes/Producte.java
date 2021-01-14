package com.models.productes;

import java.io.Serializable;

import com.models.productor.Productor;

public abstract class Producte implements Serializable{ 
	
	private static final long serialVersionUID = -1230363787961150097L;
	
	private String id;
	private String nom;
	private Productor productor;
	private long stock;
	private double preu;

	/**
	 * Constructor buit.
	 */
	public Producte() {

	}

	/**
	 * Metode Constructor
	 * 
	 * @param id
	 * @param nom
	 * @param idProductor
	 */
	public Producte(String id, String nom, Productor productor, long stock, double preu) {
		this.id = id;
		this.nom = nom;
		this.productor = productor;
		this.stock = stock;
		this.preu = preu;
	}

	/**
	 * Metode getter id
	 * 
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

	public Productor getProductor() {
		return productor;
	}

	public void setProductor(Productor productor) {
		this.productor = productor;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public double getPreu() {
		return preu;
	}

	public void setPreu(double preu) {
		this.preu = preu;
	}

}
