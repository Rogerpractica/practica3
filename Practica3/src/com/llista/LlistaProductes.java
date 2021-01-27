package com.llista;

import com.models.productes.Producte;

public class LlistaProductes {

	private int nElem;

	public LlistaProductes() {
		this.nElem = 0;
	}

	public boolean isEmpty() {
		if (nElem == 0) {
			return true;
		}
		return false;
	}

	private Producte[] llistaGeneral = new Producte[100];

	public int getnElem() {
		return nElem;
	}

	public void add(Producte item) {
		if (nElem < llistaGeneral.length) {
			llistaGeneral[nElem] = item;
			nElem++;
		}
	}

	public int size() {
		return llistaGeneral.length;
	}

	public void remove(Producte item) {
		Producte[] llistaGeneralAux = llistaGeneral.clone();
		for (int i = 0; i < llistaGeneralAux.length; i++) {
			if (llistaGeneralAux[i] == item) {
				llistaGeneralAux[i] = null;
				nElem--;
			}
		}
		llistaGeneral = llistaGeneralAux;
	}

	public void clear() {
		llistaGeneral = new Producte[100];
	}

	public Producte[] getLlista() {
		return llistaGeneral;
	}

	public void setLLista(Producte[] llista) {
		llistaGeneral = llista;
	}

}
