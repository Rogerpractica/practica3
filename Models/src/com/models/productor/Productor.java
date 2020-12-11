package com.models.productor;

public class Productor {
	private long id;
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
	public Productor(long id, String nom, String latitud, String longitud) {
		super();
		this.id = id;
		this.nom = nom;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
