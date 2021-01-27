package com.models.tablemodels;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import com.llista.LlistaCompres;
import com.models.productes.Compra;
import com.models.productes.ProducteGranel;

@SuppressWarnings("serial")
public class CompresTableModel extends AbstractTableModel {
	private static final int COLUMN_INDEX = 0;
	private static final int COLUMN_PRODUCTE = 1;
	private static final int COLUMN_PRODUCTOR = 2;
	private static final int COLUMN_QUANTITAT = 3;
	private static final int COLUMN_PU = 4;
	private static final int COLUMN_DATA = 5;
	private static final int COLUMN_TOTAL = 6;

	private String[] columnNames = { "Compra ID", "Producte", "Productor", "Quantitat", "Pes Total", "Data", "Total" };
	private LlistaCompres listCompras = new LlistaCompres();

	public CompresTableModel(Compra[] listCompras) {
		this.listCompras.setLLista(listCompras);

		int indexCount = 1;
		for (Compra Compra : listCompras) {
			if (Compra != null) {
				Compra.setIndex(indexCount++);
			}
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return listCompras.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (listCompras.isEmpty()) {
			return Object.class;
		}
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Compra Compra = listCompras.getLlista()[rowIndex];
		Object returnValue = null;
		if (Compra != null) {
			switch (columnIndex) {
			case COLUMN_INDEX:
				returnValue = Compra.getIndex();
				break;
			case COLUMN_PRODUCTE:
				returnValue = Compra.getProducte().getNom();
				break;
			case COLUMN_PRODUCTOR:
				returnValue = Compra.getProducte().getProductor().getNom();
				break;
			case COLUMN_QUANTITAT:
				returnValue = Compra.getQuantitat();
				break;
			case COLUMN_PU:
				if (Compra.getProducte() instanceof ProducteGranel) {
					ProducteGranel p = (ProducteGranel) Compra.getProducte();
					returnValue = (p.getPes() * Compra.getQuantitat()) + "KG";
				} else {
					returnValue = -1;
				}
				break;
			case COLUMN_DATA:
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				returnValue = sdf.format(Compra.getData());
				break;
			case COLUMN_TOTAL:
				returnValue = Compra.getTotal();
				break;
			default:
				throw new IllegalArgumentException("Invalid column index");
			}
		}

		return returnValue;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Compra Compra = listCompras.getLlista()[rowIndex];
		if (columnIndex == COLUMN_INDEX) {
			Compra.setIndex((int) value);
		}
	}

}
