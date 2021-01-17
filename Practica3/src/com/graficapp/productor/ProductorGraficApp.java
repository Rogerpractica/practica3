package com.graficapp.productor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import com.consoleapp.menu.MenuController;
import com.models.productes.Producte;
import com.models.productes.ProducteGranel;
import com.models.productes.ProducteUnitat;
import com.models.productor.Productor;
import com.models.tablemodels.ProductorsTableModel;

public class ProductorGraficApp {

	private static JFrame frame;
	private static JTextField txtFNifProd;

	private static final String PATH_PRODUCTES = ".\\src\\com\\consoleapp\\main\\productes.txt";

	private static JLabel lblBenvingut;

	private static List<Productor> llistaProductors = new ArrayList<Productor>();
	private static List<Producte> llista = new ArrayList<Producte>();

	private static JScrollPane scrollPane;

	private static Productor productorSeleccionat;
	private static Producte producteSeleccionat;

	private static JCheckBox rBtnPGranel;
	private static JCheckBox rBtnPUnitat;

	private static JComboBox comboBoxProducte;

	private static JButton btnEditarProducte;

	private static JTable table;
	private static JTextField txtFStock;
	private static JTextField txtFPreu;

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
						initFiltres();
						initComboBoxProductes();

						if (!llista.isEmpty()) {
							comboBoxProducte.addItem("0: Cap producte sel·leccionat");
							for (Producte pro : llista) {
								comboBoxProducte.addItem(pro.getId() + ": " + pro.getNom());
							}
						}

						comboBoxProducte.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								for (Producte p : llista) {
									if (((String) comboBoxProducte.getSelectedItem()).split(": ")[0]
											.equals(p.getId())) {
										producteSeleccionat = p;
										txtFPreu.setEditable(true);
										txtFStock.setEditable(true);
										btnEditarProducte.setEnabled(true);
									} else if (((String) comboBoxProducte.getSelectedItem()).split(": ")[0]
											.equals("0")) {
										txtFPreu.setEditable(false);
										txtFStock.setEditable(false);
										btnEditarProducte.setEnabled(false);
									}
								}

							}
						});
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
//		initTaula();
//		initFiltres();
//		initComboBoxProductes();
		// ----------------
	}

	private static void initFiltres() {
		rBtnPGranel = new JCheckBox("Producte Granel");
		rBtnPGranel.setSelected(true);
		rBtnPGranel.setHorizontalAlignment(SwingConstants.CENTER);
		rBtnPGranel.setBounds(435, 35, 147, 23);
		frame.getContentPane().add(rBtnPGranel);

		rBtnPUnitat = new JCheckBox("Producte Unitat");
		rBtnPUnitat.setSelected(true);
		rBtnPUnitat.setHorizontalAlignment(SwingConstants.CENTER);
		rBtnPUnitat.setBounds(569, 35, 129, 23);
		frame.getContentPane().add(rBtnPUnitat);

		JCheckBox chckbxOcultarStock = new JCheckBox("Ocultar productes sense estoc");
		chckbxOcultarStock.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxOcultarStock.setBounds(448, 67, 240, 23);
		frame.getContentPane().add(chckbxOcultarStock);

		btnEditarProducte = new JButton("Editar producte");
		btnEditarProducte.setEnabled(false);
		btnEditarProducte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtFPreu.getText().equals("") || !txtFStock.getText().equals("")) {
					btnEditarProducte.setEnabled(false);
					txtFPreu.setEnabled(false);
					txtFStock.setEnabled(false);
					for (Producte p : llista) {
						if (p.getId().equals(producteSeleccionat.getId())) {
							p.setPreu(Double.parseDouble(txtFPreu.getText()));
							p.setStock(Long.parseLong(txtFStock.getText()));
							initTaula();
						}
					}
				}

			}
		});
		btnEditarProducte.setBounds(448, 146, 240, 23);
		frame.getContentPane().add(btnEditarProducte);

		txtFStock = new JTextField();
		txtFStock.setEditable(false);
		txtFStock.setBounds(587, 195, 101, 20);
		frame.getContentPane().add(txtFStock);
		txtFStock.setColumns(10);

		txtFPreu = new JTextField();
		txtFPreu.setEditable(false);
		txtFPreu.setBounds(448, 195, 101, 20);
		frame.getContentPane().add(txtFPreu);
		txtFPreu.setColumns(10);

		JLabel lblPreu = new JLabel("Preu");
		lblPreu.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreu.setBounds(448, 170, 101, 14);
		frame.getContentPane().add(lblPreu);

		JLabel lblStock = new JLabel("Estoc");
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setBounds(587, 170, 101, 14);
		frame.getContentPane().add(lblStock);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Producte> llistaP = new ArrayList<Producte>();
				List<Producte> llistaC = new ArrayList<Producte>();
				llistaC = MenuController.carregarProductes();
				llista.clear();
				for (Producte producte : llistaC) {
					if (producte.getProductor().getId().equals(productorSeleccionat.getId())) {
						if(!chckbxOcultarStock.isSelected()) {
							llista.add(producte);
						} else if(chckbxOcultarStock.isSelected() && producte.getStock() > 0) {
							llista.add(producte);
						}
					}
				}
				if (rBtnPGranel.isSelected()) {
					for (Producte p : llista) {
						if (p.getId().split("_")[0].equals("GR")) {
							llistaP.add(p);
						}
					}
				}
				if (rBtnPUnitat.isSelected()) {
					for (Producte p : llista) {
						if (p.getId().split("_")[0].equals("UT")) {
							llistaP.add(p);
						}
					}
				}
				llista = llistaP;
				initTaula();
			}
		});
		btnFiltrar.setBounds(448, 223, 240, 20);
		frame.getContentPane().add(btnFiltrar);

		JButton btnSortirSi = new JButton("S\u00ED");
		btnSortirSi.setEnabled(false);
		btnSortirSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Fitxer productes.
				try {
//					// Fitxer productes.
					String totsElsPAGuardar = new String();
					for (Producte producte : llista) {
						if (producte instanceof ProducteGranel) {
							ProducteGranel p = (ProducteGranel) producte;
							String productes = p.getId() + ";" + p.getNom() + ";" + p.getProductor().getId() + ";"
									+ p.getStock() + ";" + p.getPreu() + ";" + p.getPes() + "\n";
							totsElsPAGuardar = totsElsPAGuardar.concat(productes);
						} else {
							ProducteUnitat p = (ProducteUnitat) producte;
							String productes = p.getId() + ";" + p.getNom() + ";" + p.getProductor().getId() + ";"
									+ p.getStock() + ";" + p.getPreu() + ";" + (p.isCeliac() ? 1 : 0) + "\n";
							totsElsPAGuardar = totsElsPAGuardar.concat(productes);
						}
					}

					Writer writer = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(PATH_PRODUCTES), "UTF-8"));
					writer.write(totsElsPAGuardar);
					writer.close();

					System.out.println("Fitxers guardats correctament.");
					System.out.println("PROGRAMA FINALITZAT");
					System.exit(0);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSortirSi.setBounds(448, 291, 109, 23);
		frame.getContentPane().add(btnSortirSi);

		JButton btnSortirNo = new JButton("No");
		btnSortirNo.setEnabled(false);
		btnSortirNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSortirNo.setBounds(579, 291, 109, 23);
		frame.getContentPane().add(btnSortirNo);

		JButton btnSortir = new JButton("Sortir");
		btnSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSortirSi.setEnabled(true);
				btnSortirNo.setEnabled(true);
			}
		});

		btnSortir.setBounds(448, 244, 240, 23);
		frame.getContentPane().add(btnSortir);

	}

	private static void initComboBoxProductes() {
		comboBoxProducte = new JComboBox();
		comboBoxProducte.setBounds(448, 126, 240, 20);
		frame.getContentPane().add(comboBoxProducte);

		JLabel lblNewLabel = new JLabel("Producte");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(448, 101, 240, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Vols guardar els canvis?");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(448, 278, 241, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}

	protected static void initTaula() {
		frame.setBounds(100, 100, 714, 366);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 414, 190);
		frame.getContentPane().add(scrollPane);

		TableModel tM = new ProductorsTableModel(llista);
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
