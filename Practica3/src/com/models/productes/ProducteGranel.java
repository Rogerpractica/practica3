package com.models.productes;

public class ProducteGranel extends Producte {

	public final static String PREFIX = "GR_";
	
	private long stock;
	private double preu;
	private double pes;

	public ProducteGranel() {}

	public ProducteGranel(long stock, double preu, double pes) {
		super();
		this.stock = stock;
		this.preu = preu;
		this.pes = pes;
	}
	
	public ProducteGranel(String id, String nom, long idProductor, 
			long stock, double preu, double pes) {
		super(id, nom, idProductor);
		this.stock = stock;
		this.preu = preu;
		this.pes = pes;
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
