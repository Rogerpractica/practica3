package com.consoleapp.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.models.productes.Producte;
import com.models.productes.ProducteGranel;
import com.models.productes.ProducteUnitat;

public class MenuController {
	
	private static List<Producte> llista = new ArrayList<Producte>();

	public static void opcioMenu(int opcio) {
		switch (opcio) {
		case 1:
	            carregarDades();
			break;
	        case 2:
	            llistarDades();
	            break;
	        case 3:
	            llistarDadesCeliacs();
	            break;
	        case 4:
	            llistarOfertaProductes();
	            break;
	        case 5:
	            afergirProducte();
	            break;
	        case 6:
	            esborrarProducte();
	            break;
	        case 7:
	            modificarDades();
	            break;
	        case 8:
	            llistarCompres();
	            break;
	        case 9:
	            modificarUbicacio();
	            break;
	        case 10:
	            consultarProductor();
	            break;
	        case 11:
	            afegirCompra();
	            break;
		case 0:
			System.out.println();
			System.out.println("PROGRAMA FINALITZAT");
			break;
		}
		
	}

	private static void carregarDades() {
		File f = new File("D:\\Code\\roger\\ConsoleApp\\src\\com\\consoleapp\\main\\productes.csv");   
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			int r=0;  
			String linea = "";
			while((r=fis.read())!=-1)  {
				linea = linea.concat(Character.toString((char)r));
			}
			
			String[] arr = linea.split(";");
			for (String s : arr) {
				String[] arr2 = s.split(",");
				if(arr2[0].charAt(0) == 'U') {
					ProducteUnitat pU = new ProducteUnitat();
					pU.setId(arr2[0]);
					pU.setNom(arr2[1]);
					pU.setIdProductor(new Long(arr2[2]));
					pU.setStock(new Long(arr2[3]));
					pU.setPreu(new Double(arr2[4]));
					int isCeliac = new Integer(arr2[5]);
					pU.setCeliac(isCeliac == 1 ? true : false);
					llista.add(pU);
				} else {
					ProducteGranel pG = new ProducteGranel();
					pG.setId(arr2[0]);
					pG.setNom(arr2[1]);
					pG.setIdProductor(new Long(arr2[2]));
					pG.setStock(new Long(arr2[3]));
					pG.setPreu(new Double(arr2[4]));
					pG.setPes(new Integer(arr2[5]));
					llista.add(pG);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No hem trobat el fitxer");
		} catch (IOException e) {
			System.out.println("Error llegint el fitxer");
		}   
	}
	
	private static void llistarDades() {
			for(Producte producte : llista) {
				if(producte instanceof ProducteUnitat) {
					ProducteUnitat product = (ProducteUnitat) producte;
					String prod = "Procute noseque" + product.getId() + 
							"el nom es: " + product.getNom();
					System.out.println(prod);
				}
			}
	}
	
	private static void llistarDadesCeliacs() {
		
	}
	
	private static void llistarOfertaProductes() {

	}
	
	private static void afergirProducte() {

	}
	
	private static void esborrarProducte() {

	}
	
	private static void modificarDades() {
		
	}
	
	private static void llistarCompres() {	
	
	}

	private static void modificarUbicacio() {

	}
	
	private static void consultarProductor() {
		
	}
	
	private static void afegirCompra() {
		
	}
}
