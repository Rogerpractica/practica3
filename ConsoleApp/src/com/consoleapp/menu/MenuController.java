package com.consoleapp.menu;

public class MenuController {

	public static void opcioMenu(int opcio) {
		switch (opcio) {
		case 1:
	            carregarDades();
			break;
//	        case 2:
//	            llistarDades();
//	            break;
//	        case 3:
//	            llistarDadesCeliacs();
//	            break;
//	        case 4:
//	            llistarOfertaProductes();
//	            break;
//	        case 5:
//	            afergirProducte();
//	            break;
//	        case 6:
//	            esborrarProducte();
//	            break;
//	        case 7:
//	            modificarDades();
//	            break;
//	        case 8:
//	            llistarCompres();
//	            break;
//	        case 9:
//	            modificarUbicacio();
//	            break;
//	        case 10:
//	            consultarProductor();
//	            break;
//	        case 11:
//	            afegirCompra();
//	            break;
		case 0:
			System.out.println();
			System.out.println("PROGRAMA FINALITZAT");
			break;
		}

	}

	private static void carregarDades() {
		System.out.println("test");
	}
}
