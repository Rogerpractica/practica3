package com.models.productes;

import java.io.Serializable;

import com.models.productor.Productor;

public abstract class Producte implements Serializable { 
	
	private static final long serialVersionUID = -1230363787961150097L;
	
	private int index;
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
	 * Getter id
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter productor
	 * @return productor
	 */
	public Productor getProductor() {
		return productor;
	}

	/**
	 * Setter productor
	 * @param productor
	 */
	public void setProductor(Productor productor) {
		this.productor = productor;
	}

	/**
	 * Getter estoc
	 * @return estoc
	 */
	public long getStock() {
		return stock;
	}

	/**
	 * Setter estoc
	 * @param stock
	 */
	public void setStock(long stock) {
		this.stock = stock;
	}

	/**
	 * Getter preu
	 * @return preu
	 */
	public double getPreu() {
		return preu;
	}

	/**
	 * Setter preu
	 * @param preu
	 */
	public void setPreu(double preu) {
		this.preu = preu;
	}
	
	/**
	 * Getter �ndex
	 * @return �ndex
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Setter �ndex
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

}
