package com.consoleapp.main;

import java.util.Scanner;

import com.consoleapp.menu.MenuController;

public class Console {
	
	private static Scanner scanner = new Scanner(System.in);
	
	private String latitud;
	private String longitud;
	
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
        int i = 0;
        System.out.println("============================MENU============================");
        System.out.println("============================================================");
        System.out.println("1.Carregar les dades dels fitxers");
        System.out.println("2.Llistar les dades de tots els productes");
        System.out.println("3.Llistar les dades dels productes que s�n aptes per celiacs");
        System.out.println("4.Llistar l'oferta de productes d'un determinat productor");
        System.out.println("5.Afegir un nou producte");
        System.out.println("6.Esborrar tots els productes que ofereix un productor");
        System.out.println("7.Modificar les dades d'un producte (preu i/o estoc)");
        System.out.println("8.Llistar les compres que hem fet");
        System.out.println("9.Modificar la nostra ubicaci�");
        System.out.println("10.Consultar els productors i els productes que ofereixen a una dist�ncia");
        System.out.println("11.Afegir una nova compra d'un producte");
        System.out.println("0.Exit");
        System.out.println("============================================================");
        System.out.print("Que vols fer: ");
        i=scanner.nextInt();
        
        MenuController.opcioMenu(i);
        
    }
	
}