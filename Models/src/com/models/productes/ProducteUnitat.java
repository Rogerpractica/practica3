package com.models.productes;

public class ProducteUnitat extends Producte {
	
	public static String PREFIX = "UT_";

	private long stock;
	private double preu;
	private boolean isCeliac = Boolean.FALSE;

	public ProducteUnitat() {}

	public ProducteUnitat(long stock, double preu, boolean isCeliac) {
		super();
		this.stock = stock;
		this.preu = preu;
		this.isCeliac = isCeliac;
	}
	
	public ProducteUnitat(String id, String nom, long idProductor, 
			long stock, double preu, boolean isCeliac) {
		super(id, nom, idProductor);
		this.stock = stock;
		this.preu = preu;
		this.isCeliac = isCeliac;
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

	public boolean isCeliac() {
		return isCeliac;
	}

	public void setCeliac(boolean isCeliac) {
		this.isCeliac = isCeliac;
	}
	
}
