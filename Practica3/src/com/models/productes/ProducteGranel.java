package com.models.productes;

import com.models.productor.Productor;

public class ProducteGranel extends Producte{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3482683547607610778L;

	public final static String PREFIX = "GR_";

	private double pes;

	public ProducteGranel() {}

	public ProducteGranel(double pes) {
		super();
		this.pes = pes;
	}
	
	public ProducteGranel(String id, String nom, Productor productor, 
			long stock, double preu, double pes) {
		super(id, nom, productor, stock, preu);
		this.pes = pes;
	}

	public double getPes() {
		return pes;
	}

	public void setPes(double pes) {
		this.pes = pes;
	}
	
	public static String test(String test) {
		return test;
	}
	
}
