package com.models.productor;

import java.io.Serializable;

public class Productor implements Serializable {
	/**
	 * Fitxer serialitzable
	 */
	private static final long serialVersionUID = 3005165714585574738L;
	
	private String id;
	private String nom;
	private String latitud;
	private String longitud;
	
	public Productor() {}
	
	/**
	 * Metode constructor del productor.
	 * @param id
	 * @param nom
	 * @param latitud
	 * @param longitud
	 */
	public Productor(String id, String nom, String latitud, String longitud) {
		super();
		this.id = id;
		this.nom = nom;
		this.latitud = latitud;
		this.longitud = longitud;
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
	 * Getter latitud
	 * @return latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * Setter latitud
	 * @param latitud
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * Getter longitud
	 * @return longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * Setter longitud
	 * @param longitud
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
}