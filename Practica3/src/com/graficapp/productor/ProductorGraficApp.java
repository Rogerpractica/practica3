package com.graficapp.productor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import com.consoleapp.menu.MenuController;
import com.models.productes.Producte;
import com.models.productor.Productor;
import com.models.tablemodels.ProductorsTableModel;

public class ProductorGraficApp {

	private static JFrame frame;
	private static JTextField txtFNifProd;

	private static JLabel lblBenvingut;

	private static List<Productor> llistaProductors = new ArrayList<Productor>();
	private static List<Producte> llista = new ArrayList<Producte>();

	private static JScrollPane scrollPane;

	private static Productor productorSeleccionat;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void run() {
		try {
			ProductorGraficApp window = new ProductorGraficApp();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public ProductorGraficApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 451, 164);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		initLabels();
		initButtons();

	}

	private static void initButtons() {
		JButton btnNifProd = new JButton("CONTINUAR");
		btnNifProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llistaProductors = MenuController.carregarProductors();
				productorSeleccionat = null;
				for (Productor p : llistaProductors) {
					List<Producte> llistaP = new ArrayList<Producte>();
					if (p.getId().equals(txtFNifProd.getText())) {
						productorSeleccionat = p;
						lblBenvingut.setText("Benvingut " + p.getNom());
						llistaP = MenuController.carregarProductes();
						llista.clear();
						for (Producte producte : llistaP) {
							if (producte.getProductor().getId().equals(productorSeleccionat.getId())) {
								llista.add(producte);
							}
						}
						initTaula();
						break;
					}
				}

				if (productorSeleccionat == null) {
					lblBenvingut.setText("No s'ha trobat el NIF introduït, introdueix un NIF vàlid.");
					frame.setBounds(100, 100, 451, 164);
				}

			}
		});
		btnNifProd.setBounds(10, 67, 414, 23);
		frame.getContentPane().add(btnNifProd);
		// ----------------
		// ----------------
	}

	protected static void initTaula() {
		frame.setBounds(100, 100, 714, 366);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 425, 190);
		frame.getContentPane().add(scrollPane);

		TableModel tM = new ProductorsTableModel(llista);
		tM.setValueAt((double)5, 0, 4);
		table = new JTable();
		table.setModel(tM);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
	}

	private static void initLabels() {
		JLabel lblProductorNif = new JLabel("Introdueix el NIF de productor", SwingConstants.CENTER);
		lblProductorNif.setBounds(10, 11, 414, 14);
		frame.getContentPane().add(lblProductorNif);

		txtFNifProd = new JTextField();
		txtFNifProd.setBounds(176, 36, 86, 20);
		frame.getContentPane().add(txtFNifProd);
		txtFNifProd.setColumns(10);

		lblBenvingut = new JLabel("");
		lblBenvingut.setBounds(10, 101, 414, 14);
		frame.getContentPane().add(lblBenvingut);
	}
}
