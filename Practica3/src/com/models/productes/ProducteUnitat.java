package com.models.productes;

import com.models.productor.Productor;

public class ProducteUnitat extends Producte {

	/**
	 * Fitxer serialitzable
	 */
	private static final long serialVersionUID = 2343271523251609980L;

	/**
	 * Prefix en el id del producte unitat
	 */
	public static String PREFIX = "UT_";

	private boolean isCeliac = Boolean.FALSE;

	/**
	 * Constructor buit
	 */
	public ProducteUnitat() {
	}

	public ProducteUnitat(long stock, boolean isCeliac) {
		super();
		this.isCeliac = isCeliac;
	}

	/**
	 * Mètode constructor de producte unitat
	 * @param id
	 * @param nom
	 * @param productor
	 * @param stock
	 * @param preu
	 * @param isCeliac: hi afegim si és o no celiac
	 */
	public ProducteUnitat(String id, String nom, Productor productor, long stock, double preu, boolean isCeliac) {
		super(id, nom, productor, stock, preu);
		this.isCeliac = isCeliac;
	}

	/**
	 * Getter celiac
	 * @return celiac
	 */
	public boolean isCeliac() {
		return isCeliac;
	}

	/**
	 * Setter celiac
	 * @param isCeliac
	 */
	public void setCeliac(boolean isCeliac) {
		this.isCeliac = isCeliac;
	}
}