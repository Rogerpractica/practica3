package com.models.productes;

import com.models.productor.Productor;

public class ProducteUnitat extends Producte {

	private static final long serialVersionUID = 2343271523251609980L;

	public static String PREFIX = "UT_";

	private boolean isCeliac = Boolean.FALSE;

	public ProducteUnitat() {
	}

	public ProducteUnitat(long stock, boolean isCeliac) {
		super();
		this.isCeliac = isCeliac;
	}

	public ProducteUnitat(String id, String nom, Productor productor, long stock, double preu, boolean isCeliac) {
		super(id, nom, productor, stock, preu);
		this.isCeliac = isCeliac;
	}

	public boolean isCeliac() {
		return isCeliac;
	}

	public void setCeliac(boolean isCeliac) {
		this.isCeliac = isCeliac;
	}

}
