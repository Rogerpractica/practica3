package com.llista;

import com.models.productor.Productor;

public class LlistaProductors {

	private int nElem;

	public LlistaProductors() {
		this.nElem = 0;
	}

	public boolean isEmpty() {
		if (nElem == 0) {
			return true;
		}
		return false;
	}

	private Productor[] llistaGeneral = new Productor[100];

	public int getnElem() {
		return nElem;
	}

	public void add(Productor item) {
		if (nElem < llistaGeneral.length) {
			llistaGeneral[nElem] = item;
			nElem++;
		}
	}

	public int size() {
		return llistaGeneral.length;
	}

	public void remove(Productor item) {
		Productor[] llistaGeneralAux = llistaGeneral.clone();
		for (int i = 0; i < llistaGeneralAux.length; i++) {
			if (llistaGeneralAux[i] == item) {
				llistaGeneralAux[i] = null;
				nElem--;
			}
		}
		llistaGeneral = llistaGeneralAux;
	}

	public void clear() {
		llistaGeneral = new Productor[100];
	}

	public Productor[] getLlista() {
		return llistaGeneral;
	}

	public void setLLista(Productor[] llista) {
		llistaGeneral = llista;
	}

}
