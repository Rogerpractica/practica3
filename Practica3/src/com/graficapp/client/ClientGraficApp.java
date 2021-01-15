package com.graficapp.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.models.productes.Compra;

public class ClientGraficApp {

	private static final String PATH_COMPRES = "D:\\Code\\roger\\Practica3\\src\\com\\consoleapp\\main\\compres.txt";

	private static List<Compra> llistaCompres = new ArrayList<Compra>();

	private JFrame frame;
	private JTextField txtFLat;
	private JTextField txtFLon;

	private long latitud;
	private long longitud;
	private JTable tableCompres;

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
		frame.setBounds(100, 100, 591, 473);
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
				if (!txtFLat.getText().isEmpty() && !txtFLon.getText().isEmpty()) {

					lblLat.setForeground(Color.WHITE);
					lblLon.setForeground(Color.WHITE);
					lblErrorPosicio.setText("");
					btnPosicioContinuar.setText("ACTUALITZAR");
					latitud = Long.parseLong(txtFLat.getText());
					longitud = Long.parseLong(txtFLon.getText());
					carregarCompres();

					DefaultListModel<String> listModel = new DefaultListModel<String>();

					Object[] arrC = (Object[]) llistaCompres.toArray();

					Object[][] arrGeneral = new Object[arrC.length][4];

					for (int i = 0; i < arrC.length; i++) {
						for (int j = 0; j < 4; j++) {
							switch (j) {
								case 0: {
									arrGeneral[i][j] = ((Compra)arrC[i]).getProducte().getId();
									break;
								}
								
								case 1: {
									arrGeneral[i][j] = ((Compra)arrC[i]).getQuantitat();
									break;
								}
								
								case 2: {
									SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
									arrGeneral[i][j] = sdf.format(((Compra)arrC[i]).getData());
									break;
								}
								
								case 3: {
									arrGeneral[i][j] = ((Compra)arrC[i]).getTotal();
									break;
								}
							}
						}
					}

					String[] columnes = { "Id Producte", "Quantitat", "Data", "Total" };

					tableCompres = new JTable(arrGeneral, columnes);
					tableCompres.setBounds(10, 118, 388, 140);
					tableCompres.setEnabled(Boolean.FALSE);
					frame.getContentPane().add(tableCompres);

				} else {
					lblLat.setForeground(Color.RED);
					lblLon.setForeground(Color.RED);
					lblErrorPosicio.setText("Introdueix la teva posició per continuar.");
				}
			}
		});
		btnPosicioContinuar.setBounds(10, 59, 547, 23);
		frame.getContentPane().add(btnPosicioContinuar);

//		list.setVisible(Boolean.FALSE);

	}

	private static void carregarCompres() {
		ObjectInputStream objectinputstream = null;
		try {
			FileInputStream streamIn = new FileInputStream(new File(PATH_COMPRES));
			objectinputstream = new ObjectInputStream(streamIn);
			List<Compra> llistaCompresRecuperada = (List<Compra>) objectinputstream.readObject();
			llistaCompres = llistaCompresRecuperada;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
