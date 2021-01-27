package com.models.productes;

import com.models.productor.Productor;

public class ProducteGranel extends Producte{

	/**
	 * Fitxer serialitzable
	 */
	private static final long serialVersionUID = 3482683547607610778L;

	/**
	 * Prefix en el id del producte granel
	 */
	public final static String PREFIX = "GR_";

	private double pes;

	/**
	 * Constructor buit
	 */
	public ProducteGranel() {}

	public ProducteGranel(double pes) {
		super();
		this.pes = pes;
	}
	
	/**
	 * Mètode constructor de Producte Granel
	 * @param id
	 * @param nom
	 * @param productor
	 * @param stock
	 * @param preu
	 * @param pes: hi afegim pes
	 */
	public ProducteGranel(String id, String nom, Productor productor, 
			long stock, double preu, double pes) {
		super(id, nom, productor, stock, preu);
		this.pes = pes;
	}

	/**
	 * Getter pes
	 * @return pes
	 */
	public double getPes() {
		return pes;
	}

	/**
	 * Setter pes
	 * @param pes
	 */
	public void setPes(double pes) {
		this.pes = pes;
	}
	
	/**
	 * Getter test
	 * @param test
	 * @return test
	 */
	public static String test(String test) {
		return test;
	}
}