package com.consoleapp.main;

import java.util.Scanner;

public class Console {
	
	private Scanner scanner = new Scanner(System.in);
	
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
        System.out.println(latitud);
        
        System.out.println("Introdueix la longitud");
        longitud = scanner.next();
        System.out.println(longitud);
	}
	
	private void initMenu() {
		
	}
	
}