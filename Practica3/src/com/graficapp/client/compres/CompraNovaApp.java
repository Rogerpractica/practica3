package com.graficapp.client.compres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.graficapp.client.ClientGraficApp;
import com.models.productes.Compra;
import com.models.productes.Producte;
import com.models.productes.ProducteGranel;
import com.models.productes.ProducteUnitat;
import com.models.productor.Productor;

@SuppressWarnings("rawtypes")
public class CompraNovaApp {

	private static final String PATH_PRODUCTES = ".\\src\\com\\consoleapp\\main\\productes.txt";
	private static final String PATH_PRODUCTORS = ".\\src\\com\\consoleapp\\main\\productors.txt";

	private static List<Producte> llista = new ArrayList<Producte>();
	private static List<Productor> llistaProductors = new ArrayList<Productor>();
	private static List<Compra> llistaCompres = new ArrayList<Compra>();

	private static JFrame frame;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private static Producte producteSelectionat;
	
	private static JTextField txtFieldData;
	private static JLabel lblError;
	private static JTextField txtFTotal;
	private static JComboBox comboBox;
	private JLabel lblProductor;
	private JLabel lblStock;
	private static JSpinner spinnerQuant;
	private JLabel lblPreu;
	private JTextField txtFNProd;
	private JTextField txtFNProductor;
	private JTextField txtFStock;
	private JTextField txtFPreu;
	private JLabel lblNomProducte;
	
	private JComboBox comboCompres;

	@SuppressWarnings("static-access")
	/**
	 * Mètode que ejecuta l'aplicació.
	 */
	public static void run(List<Compra> llistaC) {
		try {
			llistaCompres = llistaC;
			CompraNovaApp window = new CompraNovaApp();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mètode que crea l'aplicació.
	 */
	public CompraNovaApp() {
		initialize();
	}

	/**
	 * Mètode que inicialitza el contingut del frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 340, 358);
		frame.getContentPane().setLayout(null);

		initCampsCompra();
		initComboBoxProductes();

	}

	@SuppressWarnings("unchecked")
	/**
	 * Mètode que inicialitza les compres dels productes.
	 */
	private void initComboBoxProductes() {

		comboBox = new JComboBox();
		comboBox.setBounds(111, 11, 175, 20);
		frame.getContentPane().add(comboBox);

		carregarProductors();
		carregarProductes();

		comboBox.addItem("0: Sel·lecciona producte");

		lblProductor = new JLabel("Nom productor:");
		lblProductor.setBounds(24, 64, 124, 14);
		frame.getContentPane().add(lblProductor);

		lblStock = new JLabel("Estoc: ");
		lblStock.setBounds(68, 93, 59, 14);
		frame.getContentPane().add(lblStock);

		lblPreu = new JLabel("Preu: ");
		lblPreu.setBounds(72, 118, 76, 14);
		frame.getContentPane().add(lblPreu);

		txtFNProd = new JTextField();
		txtFNProd.setEditable(false);
		txtFNProd.setBounds(111, 39, 175, 20);
		frame.getContentPane().add(txtFNProd);
		txtFNProd.setColumns(10);

		txtFNProductor = new JTextField();
		txtFNProductor.setEditable(false);
		txtFNProductor.setBounds(111, 64, 175, 20);
		frame.getContentPane().add(txtFNProductor);
		txtFNProductor.setColumns(10);

		txtFStock = new JTextField();
		txtFStock.setEditable(false);
		txtFStock.setBounds(111, 90, 175, 20);
		frame.getContentPane().add(txtFStock);
		txtFStock.setColumns(10);

		txtFPreu = new JTextField();
		txtFPreu.setEditable(false);
		txtFPreu.setBounds(111, 115, 175, 20);
		frame.getContentPane().add(txtFPreu);
		txtFPreu.setColumns(10);

		lblNomProducte = new JLabel("Nom producte:");
		lblNomProducte.setBounds(26, 39, 133, 14);
		frame.getContentPane().add(lblNomProducte);

		spinnerQuant = new JSpinner();
		spinnerQuant.setEnabled(false);
		spinnerQuant.setBounds(111, 142, 175, 20);
		frame.getContentPane().add(spinnerQuant);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.setEnabled(false);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Compra compra = new Compra();
				if(validarCompra()) {
					compra.setProducte(producteSelectionat);
					compra.setQuantitat((int)spinnerQuant.getValue());
					compra.setTotal(Double.parseDouble(txtFTotal.getText()));
					ClientGraficApp.guardarCompra(compra);
					try {
						compra.setData(sdf.parse(txtFieldData.getText()));
					} catch (ParseException e1) {}
				}
				
			}
		});
		btnComprar.setBounds(12, 245, 302, 23);
		frame.getContentPane().add(btnComprar);
		
		comboCompres = new JComboBox();
		comboCompres.setBounds(12, 279, 302, 20);
		frame.getContentPane().add(comboCompres);
		
		comboCompres.addItem("Sel·lecciona la compra que vulguis duplicar");
		
		lblError = new JLabel("");
		lblError.setBounds(12, 226, 302, 14);
		frame.getContentPane().add(lblError);
		for(Compra c : llistaCompres) {
			comboCompres.addItem(
					c.getProducte().getId() + 
					", " + c.getProducte().getNom() + 
					", " + c.getQuantitat() + 
					", " + sdf.format(c.getData()) + 
					", " + c.getTotal());
		}
		
		comboCompres.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(((String)comboCompres.getSelectedItem()).split(", ").length != 1) {
					String[] arr = ((String)comboCompres.getSelectedItem()).split(", ");
					btnComprar.setEnabled(true);
					spinnerQuant.setEnabled(true);
					Compra compraSel = new Compra();
					compraSel.setProducte(getProducteById(arr[0]));
					compraSel.setQuantitat(Long.parseLong(arr[2]));
					try {
						compraSel.setData(sdf.parse(arr[3]));
					} catch (ParseException e1) {}
					compraSel.setTotal(Double.parseDouble(arr[4]));
					
					for(Compra c : llistaCompres) {
						if((c.getProducte().getId().equals(compraSel.getProducte().getId())
								&& (c.getQuantitat() == compraSel.getQuantitat())
								&& (c.getTotal() == compraSel.getTotal())
								&& (sdf.format(c.getData()).equals(sdf.format(compraSel.getData()))))) {
							
							txtFNProd.setText(c.getProducte().getNom());
							txtFNProductor.setText(c.getProducte().getProductor().getNom());
							txtFStock.setText(Long.toString(c.getProducte().getStock()));
							txtFPreu.setText(Double.toString(c.getProducte().getPreu()));
							spinnerQuant.setValue(c.getQuantitat());
							txtFieldData.setText(sdf.format(c.getData()));
							txtFTotal.setText(Double.toString(c.getTotal()));
							producteSelectionat = c.getProducte();
						}
					}
				} else {
					txtFNProd.setText("");
					txtFNProductor.setText("");
					txtFStock.setText("");
					txtFPreu.setText("");
					spinnerQuant.setValue(0);
					txtFieldData.setText("");
					txtFTotal.setText("");
					btnComprar.setEnabled(false);
				}
				
				
			}
		});

		spinnerQuant.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (producteSelectionat != null) {
					double total = producteSelectionat.getPreu() * (int) spinnerQuant.getValue();
					txtFTotal.setText(Double.toString(total));
				}
			}
		});
			
		for (Producte p : llista) {
			comboBox.addItem(p.getId() + ": " + p.getNom());
		}

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if ("0".equals(((String) comboBox.getSelectedItem()).split(": ")[0])) {
					producteSelectionat = null;
					spinnerQuant.setEnabled(Boolean.FALSE);
					spinnerQuant.setValue(0);
					txtFieldData.setEditable(Boolean.FALSE);
					txtFNProd.setText("");
					txtFNProductor.setText("");
					txtFPreu.setText("");
					txtFStock.setText("");
					btnComprar.setEnabled(Boolean.FALSE);
					comboCompres.setEnabled(true);
				} else {
					btnComprar.setEnabled(Boolean.TRUE);
					spinnerQuant.setEnabled(Boolean.TRUE);
					txtFieldData.setEditable(Boolean.TRUE);
					producteSelectionat = getProducteById(((String) comboBox.getSelectedItem()).split(": ")[0]);

					txtFNProd.setText(producteSelectionat.getNom());
					txtFNProductor.setText(producteSelectionat.getProductor().getNom());
					txtFPreu.setText(Double.toString(producteSelectionat.getPreu()));
					txtFStock.setText(Long.toString(producteSelectionat.getStock()));
					comboCompres.setEnabled(false);
				}
			}
		});

	}

	/**
	 * Mètode que diu si la compra és vàlida o no
	 * @return booleà si la compra és validada o no.
	 */
	protected boolean validarCompra() {
		if(((int)spinnerQuant.getValue()) > 0 
				&& !txtFieldData.getText().equals("")
				&& Integer.parseInt(txtFStock.getText()) >= (int)spinnerQuant.getValue()) {
			lblError.setText("");
			return true;
		} else {
			lblError.setText("Introdueix tots els camps correctament.");
			return false;
		}
	}

	/**
	 * Mètode que diu si el id introduït pertany a algún producte de la llista. 
	 * @param id
	 * @return si existeix o no el producte introduït per ID.
	 */
	private static Producte getProducteById(String id) {
		for (Producte producte : llista) {
			if (producte.getId().equals(id)) {
				return producte;
			}
		}
		return null;
	}

	/**
	 * Mètode que carrega els productes de la llista.
	 * @return la llista carregada.
	 */
	public static List<Producte> carregarProductes() {
		llista.clear();
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(PATH_PRODUCTES));
			int r = 0;
			String linea = "";
			while ((r = fis.read()) != -1) {
				linea = linea.concat(Character.toString((char) r));
			}

			String[] arr = linea.split("\n");
			for (String s : arr) {
				String[] arr2 = s.split(";");
				if (arr2[0].charAt(0) == 'U') {
					ProducteUnitat pU = new ProducteUnitat();
					pU.setId(arr2[0]);
					pU.setNom(arr2[1]);
					pU.setProductor(getProductorById(arr2[2]));
					pU.setStock(Long.parseLong(arr2[3]));
					pU.setPreu(Double.parseDouble(arr2[4]));
					int isCeliac = Integer.parseInt(arr2[5]);
					pU.setCeliac(isCeliac == 1 ? true : false);
					llista.add(pU);
				} else {
					ProducteGranel pG = new ProducteGranel();
					pG.setId(arr2[0]);
					pG.setNom(arr2[1]);
					pG.setProductor(getProductorById(arr2[2]));
					pG.setStock(Long.parseLong(arr2[3]));
					pG.setPreu(Double.parseDouble(arr2[4]));
					pG.setPes(Double.parseDouble(arr2[5]));
					llista.add(pG);
				}
			}
			System.out.println("Fitxer carregat correctament.");
		} catch (FileNotFoundException e) {
			System.out.println("No hem trobat el fitxer");
		} catch (IOException e) {
			System.out.println("Error llegint el fitxer");
		}
		return llista;
	}

	/**
	 * Mètode que diu si el id introduït pertany a algún productor de la llista. 
	 * @param id
	 * @return si existeix o no el productor introduït per ID.
	 */
	private static Productor getProductorById(String id) {
		for (Productor productor : llistaProductors) {
			if (productor.getId().equals(id)) {
				return productor;
			}
		}
		return null;
	}

	/**
	 * Mètode que carrega els productors.
	 */
	private static void carregarProductors() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(PATH_PRODUCTORS));
			int r = 0;
			String lineaGeneral = "";
			while ((r = fis.read()) != -1) {
				lineaGeneral = lineaGeneral.concat(Character.toString((char) r));
			}
			String linea = lineaGeneral.replace("\r", "");
			String[] arr = linea.split("\n");
			for (String s : arr) {
				String[] arr2 = s.split(";");
				Productor p = new Productor();
				p.setId(arr2[0]);
				p.setNom(arr2[1]);
				p.setLatitud(arr2[2]);
				p.setLongitud(arr2[3]);
				llistaProductors.add(p);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No hem trobat el fitxer");
		} catch (IOException e) {
			System.out.println("Error llegint el fitxer");
		}

	}

	/**
	 * Mètode que inicialitza els camps de compra.
	 */
	private void initCampsCompra() {

		JLabel lblProducte = new JLabel("Producte:");
		lblProducte.setBounds(54, 14, 105, 14);
		frame.getContentPane().add(lblProducte);

		JLabel lblQuantitat = new JLabel("Quantitat: ");
		lblQuantitat.setBounds(48, 145, 53, 14);
		frame.getContentPane().add(lblQuantitat);

		JLabel lblData = new JLabel("Data (dd/mm/yyyy): ");
		lblData.setBounds(0, 173, 175, 14);
		frame.getContentPane().add(lblData);

		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(70, 201, 89, 14);
		frame.getContentPane().add(lblTotal);

		txtFieldData = new JTextField();
		txtFieldData.setEditable(false);
		txtFieldData.setBounds(111, 170, 175, 20);
		frame.getContentPane().add(txtFieldData);
		txtFieldData.setColumns(10);

		txtFTotal = new JTextField();
		txtFTotal.setEditable(false);
		txtFTotal.setBounds(111, 198, 175, 20);
		frame.getContentPane().add(txtFTotal);
		txtFTotal.setColumns(10);
	}
}
