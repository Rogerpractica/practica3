package com.graficapp.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import com.graficapp.client.compres.CompraNovaApp;
import com.models.productes.Compra;
import com.models.productes.Producte;
import com.models.productes.ProducteGranel;
import com.models.productor.Productor;
import com.models.tablemodels.CompresTableModel;

public class ClientGraficApp {

//	private static final String PATH_COMPRES = "D:\\Code\\roger\\Practica3\\src\\com\\consoleapp\\main\\compres.txt";
	private static final String PATH_COMPRES = ".\\src\\com\\consoleapp\\main\\compres.txt";

	private static List<Compra> llistaCompres = new ArrayList<Compra>();
	private static List<Producte> llista = new ArrayList<Producte>();
	
	private static JFrame frame;
	private JTextField txtFLat;
	private JTextField txtFLon;
	private static JLabel lblSortir;

	private long latitud;
	private long longitud;

	private static JTable table;
	private static JTextField lblFins;
	private static JTextField lblDesde;
	
	private static JButton btnSortirSi;
	private static JButton btnSortirNo;

	private static JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */

	public static void run() {
		try {
			ClientGraficApp window = new ClientGraficApp();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public ClientGraficApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 599, 140);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblPosicio = new JLabel("Introdueix la teva posici\u00F3 per continuar: ");
		lblPosicio.setFont(new Font("Calibri", Font.BOLD, 15));
		lblPosicio.setBounds(10, 29, 249, 19);
		frame.getContentPane().add(lblPosicio);

		txtFLat = new JTextField();
		txtFLat.setBounds(319, 28, 82, 20);
		frame.getContentPane().add(txtFLat);
		txtFLat.setColumns(10);

		txtFLon = new JTextField();
		txtFLon.setBounds(483, 28, 82, 20);
		frame.getContentPane().add(txtFLon);
		txtFLon.setColumns(10);

		JLabel lblLat = new JLabel("Latitud: ");
		lblLat.setBounds(269, 30, 62, 14);
		frame.getContentPane().add(lblLat);

		JLabel lblLon = new JLabel("Longitud: ");
		lblLon.setBounds(425, 31, 74, 14);
		frame.getContentPane().add(lblLon);

		JLabel lblErrorPosicio = new JLabel("");
		lblErrorPosicio.setBounds(10, 93, 547, 14);
		frame.getContentPane().add(lblErrorPosicio);

		JButton btnPosicioContinuar = new JButton("CONTINUAR");
		btnPosicioContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnPosicioContinuar.getText().equals("CONTINUAR")) {
					if (!txtFLat.getText().isEmpty() && !txtFLon.getText().isEmpty()) {

						lblLat.setForeground(Color.BLACK);
						lblLon.setForeground(Color.BLACK);
						lblErrorPosicio.setText("");
						btnPosicioContinuar.setText("ACTUALITZAR");
						latitud = Long.parseLong(txtFLat.getText());
						longitud = Long.parseLong(txtFLon.getText());

						initTable();
						initlabels();
						initComboBoxFiltreProductors();
						initButtons();

						frame.setBounds(100, 100, 895, 475);

					} else {
						lblLat.setForeground(Color.RED);
						lblLon.setForeground(Color.RED);
						lblErrorPosicio.setText("Introdueix la teva posició per continuar.");
					}
				} else {
					if (!txtFLat.getText().isEmpty() && !txtFLon.getText().isEmpty()) {
						latitud = Long.parseLong(txtFLat.getText());
						longitud = Long.parseLong(txtFLon.getText());
						lblLat.setForeground(Color.BLACK);
						lblLon.setForeground(Color.BLACK);
						lblErrorPosicio.setText("Posició actualitzada correctament.");
					} else {
						lblLat.setForeground(Color.RED);
						lblLon.setForeground(Color.RED);
						lblErrorPosicio.setText("Introdueix la latitud i la longitud per actualitzar la teva posició.");
					}
				}

			}
		});
		btnPosicioContinuar.setBounds(10, 59, 547, 23);
		frame.getContentPane().add(btnPosicioContinuar);
	}

	private void initButtons() {
		// Button filtrar
		JButton btnFiltrarCompres = new JButton("FILTRAR");
		btnFiltrarCompres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Compra> listC = new ArrayList<Compra>();
				for (Compra c : llistaCompres) {
					String desde = lblDesde.getText();
					String fins = lblFins.getText();

					if (desde.equals("") && fins.equals("")) {
						listC = llistaCompres;
					} else if (!desde.equals("") && !fins.equals("")) {
						if (c.getProducte() instanceof ProducteGranel) {
							ProducteGranel p = (ProducteGranel) c.getProducte();
							double pesTotalCompra = c.getQuantitat() * p.getPes();
							if (pesTotalCompra <= Double.parseDouble(lblFins.getText())
									&& pesTotalCompra >= Double.parseDouble(lblDesde.getText())) {
								listC.add(c);
							}
						}
					} else {
						listC = llistaCompres;
					}
				}
				table.setModel(new CompresTableModel(listC));
				scrollPane.setViewportView(table);
			}
		});
		btnFiltrarCompres.setBounds(596, 181, 252, 23);
		frame.getContentPane().add(btnFiltrarCompres);

		// Button Afegir Compra

		JButton btnAfegirCompra = new JButton("AFEGIR COMPRA");
		btnAfegirCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjectInputStream objectinputstream = null;
				try {
					FileInputStream streamIn = new FileInputStream(new File(PATH_COMPRES));
					objectinputstream = new ObjectInputStream(streamIn);
					List<Compra> llistaCompresRecuperada = (List<Compra>) objectinputstream.readObject();
					CompraNovaApp.run(llistaCompresRecuperada);
				} catch (Exception ex) {
				}
			}
		});
		btnAfegirCompra.setBounds(594, 229, 254, 23);
		frame.getContentPane().add(btnAfegirCompra);
		
		JButton btnSortir = new JButton("Sortir");
		btnSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSortir.setText("Vols guardar les dades als fitxers?");
				btnSortirNo.setVisible(true);
				btnSortirSi.setVisible(true);
			}
		});
		btnSortir.setBounds(596, 290, 252, 23);
		frame.getContentPane().add(btnSortir);
		
		btnSortirSi = new JButton("Si");
		btnSortirSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					// Fitxer compres.
					FileOutputStream fileOut = new FileOutputStream(new File(PATH_COMPRES));
					ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
					objectOut.writeObject(llistaCompres);
					objectOut.close();
					System.out.println("Fitxers guardats correctament.");
					System.out.println("PROGRAMA FINALITZAT");
					System.exit(0);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		btnSortirSi.setBounds(596, 345, 89, 23);
		btnSortirSi.setVisible(false);
		frame.getContentPane().add(btnSortirSi);
		
		btnSortirNo = new JButton("No");
		btnSortirNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnSortirNo.setBounds(769, 345, 89, 23);
		btnSortirNo.setVisible(false);
		frame.getContentPane().add(btnSortirNo);
		
		lblSortir = new JLabel("");
		lblSortir.setBounds(596, 324, 252, 14);
		frame.getContentPane().add(lblSortir);
	}

	private static void initTable() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 555, 307);
		frame.getContentPane().add(scrollPane);

		carregarCompres();
		TableModel tM = new CompresTableModel(llistaCompres);
		table = new JTable();
		table.setModel(tM);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Filtres");
		lblNewLabel.setBounds(722, 31, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		lblFins = new JTextField();
		lblFins.setBounds(762, 93, 86, 20);
		frame.getContentPane().add(lblFins);
		lblFins.setColumns(10);

		lblDesde = new JTextField();
		lblDesde.setBounds(637, 93, 86, 20);
		frame.getContentPane().add(lblDesde);
		lblDesde.setColumns(10);

	}

	private static void initlabels() {
		JLabel lblNewLabel_1 = new JLabel("Filtrar per Kilos");
		lblNewLabel_1.setBounds(704, 63, 144, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Desde:");
		lblNewLabel_2.setBounds(596, 93, 138, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Fins:");
		lblNewLabel_3.setBounds(729, 93, 58, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Filtrar per Productors");
		lblNewLabel_4.setBounds(685, 125, 102, 14);
		frame.getContentPane().add(lblNewLabel_4);
	}

	private static void initComboBoxFiltreProductors() {
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(660, 150, 150, 20);

		List<Productor> listProductors = new ArrayList<Productor>();
		boolean hit = false;
		for (Compra c : llistaCompres) {
			hit = false;
			for (Productor p : listProductors) {
				if (p.getId().equals(c.getProducte().getProductor().getId())) {
					hit = true;
				}
			}
			if (!hit) {
				listProductors.add(c.getProducte().getProductor());
			}
		}

		comboBox.addItem("0: TOTS");
		for (Productor p : listProductors) {
			comboBox.addItem(p.getId() + ": " + p.getNom());
		}

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Compra> llistaC = new ArrayList<Compra>();
				if (!"0".equals(((String) comboBox.getSelectedItem()).split(": ")[0])) {
					llistaCompres.clear();
					carregarCompres();
					for (Compra c : llistaCompres) {
						if (c.getProducte().getProductor().getId()
								.equals(((String) comboBox.getSelectedItem()).split(": ")[0])) {
							llistaC.add(c);
						}
					}
					llistaCompres = llistaC;
				} else {
					llistaCompres.clear();
					carregarCompres();
				}
			}
		});
		frame.getContentPane().add(comboBox);
	}

	private static void carregarCompres() {
		ObjectInputStream objectinputstream = null;
		try {
			FileInputStream streamIn = new FileInputStream(new File(PATH_COMPRES));
			objectinputstream = new ObjectInputStream(streamIn);
			List<Compra> llistaCompresRecuperada = (List<Compra>) objectinputstream.readObject();
			if (llistaCompres.isEmpty()) {
				llistaCompres = llistaCompresRecuperada;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarCompra(Compra compra) {
		llistaCompres.add(compra);
		llista = CompraNovaApp.carregarProductes();
		table.setModel(new CompresTableModel(llistaCompres));
		scrollPane.setViewportView(table);
		for(Producte p : llista) {
			if(compra.getProducte().getId().equals(p.getId())) {
				p.setStock(p.getStock() - compra.getQuantitat());
			}
		}
	}
}
