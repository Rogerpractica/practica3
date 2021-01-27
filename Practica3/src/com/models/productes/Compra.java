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

	/**
	 * Constructor buit
	 */
	public Compra() {
	}

	/**
	 * Getter producte
	 * @return producte
	 */
	public Producte getProducte() {
		return producte;
	}

	/**
	 * Setter producte
	 * @param producte
	 */
	public void setProducte(Producte producte) {
		this.producte = producte;
	}

	/**
	 * Getter quantitat
	 * @return quantitat
	 */
	public long getQuantitat() {
		return quantitat;
	}

	/**
	 * Setter quantitat
	 * @param quantitat
	 */
	public void setQuantitat(long quantitat) {
		this.quantitat = quantitat;
	}

	/**
	 * Getter data
	 * @return
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Setter data
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Getter total
	 * @return total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Setter total
	 * @param total
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	
	/**
	 * Getter index
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Setter index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
}
