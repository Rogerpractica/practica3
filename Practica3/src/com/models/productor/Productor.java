package com.models.productor;

import java.io.Serializable;

public class Productor implements Serializable {
	
	private static final long serialVersionUID = 3005165714585574738L;
	
	private String id;
	private String nom;
	private String latitud;
	private String longitud;
	
	public Productor() {}
	
	/**
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

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
}
