package com.graficapp.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.graficapp.client.ClientGraficApp;
import com.graficapp.productor.ProductorGraficApp;

public class GraficApplication {

	private JFrame frame;

	public static void run() {
		try {
			GraficApplication window = new GraficApplication();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GraficApplication() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 443, 211);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel titolPrincipal = new JLabel("Qu\u00E8 ets productor o comprador?");
		titolPrincipal.setFont(new Font("Calibri", Font.BOLD, 25));
		titolPrincipal.setBounds(42, 11, 352, 31);
		frame.getContentPane().add(titolPrincipal);
		
		JButton btnProductor = new JButton("PRODUCTOR");
		btnProductor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(Boolean.FALSE);
				ProductorGraficApp.run();
			}
		});
		btnProductor.setFont(new Font("Calibri", Font.BOLD, 20));
		btnProductor.setBounds(10, 53, 400, 49);
		frame.getContentPane().add(btnProductor);
		
		JButton btnClient = new JButton("CLIENT");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(Boolean.FALSE);
				ClientGraficApp.run();
			}
		});
		btnClient.setFont(new Font("Calibri", Font.BOLD, 20));
		btnClient.setBounds(10, 113, 400, 49);
		frame.getContentPane().add(btnClient);
	}
}
