package com.consoleapp.main;

import java.util.Scanner;

import com.consoleapp.menu.MenuController;

public class Console {

	private static Scanner scanner = new Scanner(System.in);

	private static String latitud;
	private static String longitud;

	public void init() {
		System.out.println("Benvingut.");
		posicions();
		initMenu();
	}

	private void posicions() {
		System.out.println("Introdueix la latitud");
		latitud = scanner.next();

		System.out.println("Introdueix la longitud");
		longitud = scanner.next();
	}

	public void initMenu() {
		String i = "";
		int opcio = 0;
		boolean ok = true;
		
		System.out.println("============================MENU============================");
		System.out.println("============================================================");
		System.out.println("1.Carregar les dades dels fitxers");
		System.out.println("2.Llistar les dades de tots els productes");
		System.out.println("3.Llistar les dades dels productes que són aptes per celiacs");
		System.out.println("4.Llistar l'oferta de productes d'un determinat productor");
		System.out.println("5.Afegir un nou producte");
		System.out.println("6.Esborrar tots els productes que ofereix un productor");
		System.out.println("7.Modificar les dades d'un producte (preu i/o estoc)");
		System.out.println("8.Llistar les compres que hem fet");
		System.out.println("9.Modificar la nostra ubicació");
		System.out.println("10.Consultar els productors i els productes que ofereixen a una distància");
		System.out.println("11.Afegir una nova compra d'un producte");
		System.out.println("0.Exit");
		
		while (true) {
			System.out.println("============================================================");
			System.out.print("Que vols fer: ");
			try {
				i = scanner.next();
				opcio = Integer.parseInt(i);
			} catch (NumberFormatException ex) {
				System.out.println("Introdueix números solament del 0 al 11.");
				ok = false;
			}
			
			if(ok) {
				MenuController.opcioMenu(opcio);
			} else {
				ok = true;
			}
		}
	}

	public static void modificarLatitudILongitud(String lat, String lon) {
		if (lat != null && lat != "") {
			latitud = lat;
		}
		if (lon != null && lon != "") {
			longitud = lon;
		}
//		si long taltaltal
	}

	public static String getLatitud() {
		return latitud;
	}

	public static String getLongitud() {
		return longitud;
	}

}
