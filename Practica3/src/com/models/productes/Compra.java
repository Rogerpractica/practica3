package com.models.productes;

import java.io.Serializable;
import java.util.Date;

public class Compra implements Serializable {

	private static final long serialVersionUID = 5077275284728628066L;

	private int index;
	private Producte producte;
	private long quantitat;
	private Date data;
	private double total;

	public Compra() {
	}

	public Producte getProducte() {
		return producte;
	}

	public void setProducte(Producte producte) {
		this.producte = producte;
	}

	public long getQuantitat() {
		return quantitat;
	}

	public void setQuantitat(long quantitat) {
		this.quantitat = quantitat;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
