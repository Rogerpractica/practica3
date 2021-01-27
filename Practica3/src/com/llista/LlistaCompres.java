package com.llista;

import java.io.Serializable;

import com.models.productes.Compra;

public class LlistaCompres implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int nElem;

	public LlistaCompres() {
		this.nElem = 0;
	}

	public boolean isEmpty() {
		if (nElem == 0) {
			return true;
		}
		return false;
	}

	private Compra[] llistaGeneral = new Compra[100];

	public int getnElem() {
		return nElem;
	}

	public void add(Compra item) {
		if (nElem < llistaGeneral.length) {
			llistaGeneral[nElem] = item;
			nElem++;
		}
	}

	public int size() {
		return llistaGeneral.length;
	}

	public void remove(Compra item) {
		Compra[] llistaGeneralAux = llistaGeneral.clone();
		for (int i = 0; i < llistaGeneralAux.length; i++) {
			if (llistaGeneralAux[i] == item) {
				llistaGeneralAux[i] = null;
				nElem--;
			}
		}
		llistaGeneral = llistaGeneralAux;
	}

	public void clear() {
		llistaGeneral = new Compra[100];
	}

	public Compra[] getLlista() {
		return llistaGeneral;
	}

	public void setLLista(Compra[] llista) {
		llistaGeneral = llista;
	}

}
