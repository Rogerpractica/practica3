package com.models.tablemodels;

import javax.swing.table.AbstractTableModel;

import com.llista.LlistaProductes;
import com.models.productes.Producte;
import com.models.productes.ProducteGranel;
import com.models.productes.ProducteUnitat;

@SuppressWarnings("serial")
public class ProductorsTableModel extends AbstractTableModel {
	private static final int COLUMN_INDEX = 0;
	private static final int COLUMN_ID = 1;
	private static final int COLUMN_NOM = 2;
	private static final int COLUMN_STOCK = 3;
	private static final int COLUMN_PREU = 4;
	private static final int COLUMN_PES = 5;
	private static final int COLUMN_CELIACS = 6;

	private String[] columnNames = { "Index", "Producte ID", "Nom", "Stock", "Preu", "Pes", "Apte Celíacs" };
	private LlistaProductes listProductes = new LlistaProductes();

	public ProductorsTableModel(Producte[] listProductes) {
		this.listProductes.setLLista(listProductes);

		int indexCount = 1;
		for (Producte p : listProductes) {
			if (p != null) {
				p.setIndex(indexCount++);
			}
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return listProductes.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (listProductes.isEmpty()) {
			return Object.class;
		}
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Producte p = (Producte) listProductes.getLlista()[rowIndex];
		Object returnValue = null;

		if (p != null) {
			switch (columnIndex) {
			case COLUMN_INDEX:
				returnValue = p.getIndex();
				break;
			case COLUMN_ID:
				returnValue = p.getId();
				break;
			case COLUMN_NOM:
				returnValue = p.getNom();
				break;
			case COLUMN_STOCK:
				returnValue = p.getStock();
				break;
			case COLUMN_PREU:
				returnValue = p.getPreu();
			case COLUMN_PES:
				if (p instanceof ProducteGranel) {
					ProducteGranel prod = (ProducteGranel) p;
					returnValue = prod.getPes();
				} else {
					returnValue = "";
				}
				break;
			case COLUMN_CELIACS:
				if (p instanceof ProducteUnitat) {
					ProducteUnitat prod = (ProducteUnitat) p;
					returnValue = prod.isCeliac() ? "Sí" : "No";
				} else {
					returnValue = "";
				}
				break;
			default:
				throw new IllegalArgumentException("Invalid column index");
			}
		}

		return returnValue;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Producte p = (Producte) listProductes.getLlista()[rowIndex];
		if (columnIndex == COLUMN_INDEX) {
			p.setPreu((int) value);
		}
	}

}
