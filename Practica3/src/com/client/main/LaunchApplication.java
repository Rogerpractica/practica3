package com.client.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.consoleapp.main.Console;
import com.exception.ErrorGeneralException;
import com.graficapp.main.GraficApplication;

public class LaunchApplication {
	
	private Console console;

	private JFrame frame;
	private JButton btnModeConsola;
	private JButton btnModeGrafic;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaunchApplication window = new LaunchApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LaunchApplication() {
		initialize();
	}

	private void initialize() {
		initFrame();
		initBtns();
		
		
	}

	private void initFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 187);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	private void initBtns() {
		//Button Consola.
		btnModeConsola = new JButton("Mode Consola");
		btnModeConsola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					initConsole();
				} catch (ErrorGeneralException ex) {
					System.out.println("Error general: " + ex.getMessage());
				}
			}			
		});
		btnModeConsola.setBounds(10, 11, 449, 55);
		frame.getContentPane().add(btnModeConsola);
		
		//Button Grafic.
		btnModeGrafic = new JButton("Mode Gr\u00E0fic");
		btnModeGrafic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(Boolean.FALSE);
				GraficApplication.run();
			}
		});
		btnModeGrafic.setBounds(10, 77, 449, 55);
		frame.getContentPane().add(btnModeGrafic);
	}

	private void initConsole() throws ErrorGeneralException {
		console = new Console();
		console.init();
	}
	
	
}
