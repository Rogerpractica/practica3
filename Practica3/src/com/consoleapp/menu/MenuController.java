package com.consoleapp.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.consoleapp.main.Console;
import com.models.productes.Compra;
import com.models.productes.Producte;
import com.models.productes.ProducteGranel;
import com.models.productes.ProducteUnitat;
import com.models.productor.Productor;

public class MenuController {

	private static List<Producte> llista = new ArrayList<Producte>();
	private static List<Productor> llistaProductors = new ArrayList<Productor>();
	private static List<Compra> llistaCompres = new ArrayList<Compra>();
	private static Scanner scanner = new Scanner(System.in);

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
			System.out.println("Quin ID de productor? ");
			String i = scanner.next();
			llistarOfertaProductes(i);
			break;
		case 5:
			afegirProducte();
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
			System.exit(0);
			break;
		}
	}

	private static void carregarDades() {
		carregarProductors();
		carregarProductes();
	}

	private static void llistarDades() {
		for (Producte producte : llista) {
			if (producte instanceof ProducteUnitat) {
//				ProducteUnitat product = (ProducteUnitat) producte;
				toStringProductes(producte);
			} else {
//				ProducteGranel product = (ProducteGranel) producte;
				toStringProductes(producte);
			}
		}
	}

	private static void llistarDadesCeliacs() {
		for (Producte producte : llista) {
			if (producte instanceof ProducteUnitat) {
				if (((ProducteUnitat) producte).isCeliac()) {
					toStringProductes(producte);
				}
			}
		}
	}

	private static void llistarOfertaProductes(String idProductor) {
		for (Producte producte : llista) {
			if (producte.getProductor().getId().equals(idProductor)) {
				toStringProductes(producte);
			}
		}
	}

	private static void afegirProducte() {
		System.out.println("Afegir producte granel(g) o unitat(u) ?");
		boolean j = false;
		while (j == false) {
			String r = scanner.next();
			if (r.equals("g")) {
				ProducteGranel producte = new ProducteGranel();

//				Id producte
				System.out.println("ID producte: ");
				String id = scanner.next();
				producte.setId(id);
//				
//				preguntar si volen un o l'altre ->
//				llistar tots els productes sense mostrar res
//				lultim producte, agafes el substring i li sumes 1
//				String test = "GT_0005";
//				String test2 = test.substring(3, test.length());
//				
//				long dob = Long.parseLong(test2);
//				
//				System.out.println(dob+1);
//				
//				TODO:

//				Nom producte
				System.out.println("Nom producte: ");
				String nom = scanner.next();
				producte.setNom(nom);

//				Productor
				System.out.println("Quin dels següents productors:");
				boolean p = false;
				while (p == false) {
					int cont = getLlistaProductors();
					System.out.println("Escriu el ID del productor:");
					String idProd = scanner.next();
					if (Integer.parseInt(idProd) > cont || Integer.parseInt(idProd) < 0) {
						System.out
								.println("El productor sel·leccionat no existeix," + " selecciona un productor vàlid");
					} else {
						producte.setProductor(getProductorById(idProd));
						p = true;
					}
				}

//				Stock
				System.out.println("Stock: ");
				long stock = scanner.nextInt();
				producte.setStock(stock);

//				Preu
				System.out.println("Preu producte: ");
				double preu = scanner.nextInt();
				producte.setPreu(preu);

//				Pes
				System.out.println("Pes producte: ");
				double pes = scanner.nextInt();
				producte.setPes(pes);

				llista.add(producte);
				j = true;

			} else if (r.equals("u")) {
				ProducteUnitat producte = new ProducteUnitat();

//				Id producte
				System.out.println("ID producte: ");
				String id = scanner.next();
				producte.setId(id);

//				Nom producte
				System.out.println("Nom producte: ");
				String nom = scanner.next();
				producte.setNom(nom);

//				Productor
				System.out.println("Quin dels següents productors:");
				boolean p = false;
				while (p == false) {
					int cont = getLlistaProductors();
					System.out.println("Escriu el ID del productor:");
					String idProd = scanner.next();
					if (Integer.parseInt(idProd) > cont || Integer.parseInt(idProd) < 0) {
						System.out
								.println("El productor sel·leccionat no existeix," + " selecciona un productor vàlid");
					} else {
						producte.setProductor(getProductorById(idProd));
						p = true;
					}
				}

//				Stock
				System.out.println("Stock: ");
				long stock = scanner.nextInt();
				producte.setStock(stock);

//				Preu
				System.out.println("Preu producte: ");
				double preu = scanner.nextInt();
				producte.setPreu(preu);

//				Celiac
				System.out.println("És celíac: ");
				boolean celiac = scanner.nextBoolean();
				producte.setCeliac(celiac);
				j = true;
				llista.add(producte);

			} else if (r.equals("0")) {
				j = true;
			} else {
				System.out.println("Opcions vàlides, G o U, escriu 0 per tornar al menú");
			}
		}
	}

	private static void esborrarProducte() {
		boolean t = false;
		String r = "";
		while (t == false) {
			System.out.println("Escolleix dels següents productors el que vols eliminar tots els productes.");
			int cont = 0;
			for (Productor productor : llistaProductors) {
				System.out.println("Productor: ");
				toStringProductors(productor);

				System.out.println("Productes del productor: ");
				for (Producte producte : llista) {
					if (producte.getProductor().getId().equals(productor.getId())) {
						toStringProductes(producte);
					}
				}
				cont++;
			}

			System.out.println("Introdueix el Id del productor del qual vols eliminar els productes: ");
			r = scanner.next();

			if (Integer.parseInt(r) > cont || Integer.parseInt(r) < 0) {
				System.out.println(
						"El id del productor es incorrecte, escull un ID correcte o escriu 0 per sortir al menú.");
			} else {
				t = true;
			}
		}

		System.out.println("Estàs segur que vols esborrar els següents productes?");
		for (Productor productor : llistaProductors) {
			if (productor.getId().equals(r)) {
				System.out.println("Productor: ");
				toStringProductors(productor);

				System.out.println("Productes del productor: ");
				for (Producte producte : llista) {
					if (producte.getProductor().getId().equals(r)) {
						toStringProductes(producte);
					}
				}
			}
		}

		System.out.println("Sí (S) o No (N), 0 per tornar al menú.");
		String b = scanner.next();

		boolean f = false;
		while (f == false) {
			if (b.equals("S")) {
				ArrayList<Producte> list = new ArrayList<Producte>();
				for (Producte producte : llista) {
					if (producte.getProductor().getId().equals(r)) {
						list.add(producte);
					}
				}
				for (Producte n : list) {
					llista.remove(n);
				}
				f = true;
			} else if (b.equals("N")) {
				System.out.println("No s'ha eliminat els productes.");
				f = true;
			} else if (b.equals("0")) {
				f = true;
			} else {
				System.out.println("Les opcions són: Sí (S) o No (N), 0 per tornar al menú.");
			}

		}
	}

	private static void modificarDades() {
		llistarDades();
		boolean j = false;
		while (j == false) {
			System.out.println("Introdueix el ID del producte que vol modificar el preu i/o l'estoc");
			String id = scanner.next();
			for (Producte producte : llista) {
				if (producte.getId().equals(id)) {
					System.out.println("Que vol modificar el preu (p) o l'estoc (e), "
							+ "introdueix (2) per modificar els dos, introdueix 0 per sortir.");
					String modi = scanner.next();
					if (modi.equals("p")) {
						System.out.println("Introdueix nou preu: ");
						double preu = scanner.nextInt();
						producte.setPreu(preu);
						System.out.println("El preu s'ha modificat correctament.");
						j = true;
					} else if (modi.equals("e")) {
						System.out.println("Introdueix nou estoc: ");
						long stock = scanner.nextInt();
						producte.setStock(stock);
						System.out.println("El estoc s'ha modificat correctament.");
						j = true;
					} else if (modi.equals("2")) {
						System.out.println("Introdueix nou estoc: ");
						System.out.println("Introdueix nou preu: ");
						long stock = scanner.nextInt();
						double preu = scanner.nextInt();
						producte.setStock(stock);
						producte.setPreu(preu);
						System.out.println("El preu i l'estoc s'han modifcat correctament.");
						j = true;
					} else if (modi.equals("0")) {
						j = true;
					} else {
						System.out.println("Opcions vàlides, P o E o 2, escriu 0 per tornar al menú");
					}
				}
			}
		}
	}

	private static void afegirCompra() {

		Compra compra = new Compra();

		System.out.println("Quins dels següents productes vols comprar:");

		llistarDades();

		boolean f = false;
		while (f == false) {

			System.out.println("Introdueix el ID:");
			String id = scanner.next();

			Producte producteSeleccionat = getProducteById(id);

			if (producteSeleccionat != null) {

				// producte general
				compra.setProducte(producteSeleccionat);

				// quantitat
				System.out.println("Introdueix la quantitat:");
				long r = scanner.nextInt();
				compra.setQuantitat(r);

				// data
				System.out.println("Introdueix la data de compra (únic format correcte dia/mes/any)");
				String data = scanner.next();

				try {
					compra.setData(sdf.parse(data));
				} catch (ParseException e) {
					System.out.println(
							"Has introduït una data incorrecta, introdueix una data correcta o esciru 0 per tornar al  menú.");
//					TODO: Acabar de fer el bucle.
				}

				// cost
				compra.setTotal(r * compra.getProducte().getPreu());

				guardarCompra(compra);
//				TODO

				f = true;
			} else if (id.equals(0)) {
				f = true;
			} else {
				System.out.println(
						"L'ID sel·leccionada no existeix, sel·lecciona una ID correcta, o introdueix 0 per sortit al menú.");
			}

		}

	}

	private static void guardarCompra(Compra compra) {

		String filepath = "D:\\Code\\roger\\Practica3\\src\\com\\consoleapp\\main\\compres";

		try {
			FileOutputStream fileOut = new FileOutputStream(filepath);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			llistaCompres.add(compra);
			objectOut.writeObject(llistaCompres);
			objectOut.close();
			System.out.println("Compra guardada correctament.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void llistarCompres() {
		carregarCompres();
		mostrarCompres();
	}

	private static void modificarUbicacio() {
		System.out.println("Latitud: ");
		String lat = scanner.next();
		System.out.println("Longitud: ");
		String lon = scanner.next();
		Console.modificarLatitudILongitud(lat, lon);
	}

	private static void consultarProductor() {

	}

	private static void carregarProductors() {
		File f = new File(
				"D:\\Code\\roger\\Practica3\\src\\com\\consoleapp\\main\\productors.csv");

		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			int r = 0;
			String linea = "";
			while ((r = fis.read()) != -1) {
				linea = linea.concat(Character.toString((char) r));
			}
			String[] arr = linea.split(";");
			for (String s : arr) {
				String[] arr2 = s.split(",");
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

	private static void carregarProductes() {
		File f = new File(
				"D:\\Code\\roger\\Practica3\\src\\com\\consoleapp\\main\\productes.csv");
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
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
					pG.setPes(Integer.parseInt(arr2[5]));
					llista.add(pG);
				}
			}
			System.out.println("Fitxer carregat correctament.");
		} catch (FileNotFoundException e) {
			System.out.println("No hem trobat el fitxer");
		} catch (IOException e) {
			System.out.println("Error llegint el fitxer");
		}
	}

	private static void carregarCompres() {
		File f = new File(
				"D:\\Code\\roger\\Practica3\\src\\com\\consoleapp\\main\\compres");
		ObjectInputStream objectinputstream = null;
		try {
		    FileInputStream streamIn = new FileInputStream(f);
		    objectinputstream = new ObjectInputStream(streamIn);
		    List<Compra> llistaCompresRecuperada = (List<Compra>)objectinputstream.readObject();
		    llistaCompres = llistaCompresRecuperada;
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	private static void mostrarCompres() {
		for (Compra c : llistaCompres) {
			toStringCompres(c);
		}
	}

	private static void toStringCompres(Compra compra) {
		String descripcio = "Producte: " + compra.getProducte().getNom() + "(" + compra.getProducte().getId() + ")" + ", Quantitat: " + compra.getQuantitat() + ", Data: " + sdf.format(compra.getData())
				+ ", Total: " + compra.getTotal();
		System.out.println(descripcio);
	}

	private static void toStringProductes(Producte producte) {
		if (producte instanceof ProducteGranel) {
			ProducteGranel p = (ProducteGranel) producte;
			String descripcio = "ID producte: " + p.getId() + " Nom: " + p.getNom() + ", ID productor: "
					+ p.getProductor().getId() + ", Stock: " + p.getStock() + ", Preu: " + p.getPreu() + ", Pes: "
					+ p.getPes() + "Kg.";
			System.out.println(descripcio);
		} else {
			ProducteUnitat p = (ProducteUnitat) producte;
			String descripcio = "ID producte: " + p.getId() + " Nom: " + p.getNom() + ", ID productor: "
					+ p.getProductor().getId() + ", Stock: " + p.getStock() + ", Preu: " + p.getPreu()
					+ ", Apte per celiacs: " + (p.isCeliac() ? "Sí." : "No.");
			System.out.println(descripcio);
		}
	}

	private static Productor getProductorById(String id) {
		for (Productor productor : llistaProductors) {
			if (productor.getId().equals(id)) {
				return productor;
			}
		}
		return null;
	}

	private static Producte getProducteById(String id) {
		for (Producte producte : llista) {
			if (producte.getId().equals(id)) {
				return producte;
			}
		}
		return null;
	}

	private static int getLlistaProductors() {
		int cont = 0;
		for (Productor productor : llistaProductors) {
			toStringProductors(productor);
			cont++;
		}
		return cont;
	}

	private static void toStringProductors(Productor p) {
		String descripcio = "ID productor: " + p.getId() + ", Nom: " + p.getNom() + ", Latitud: " + p.getLatitud()
				+ ", Longitud: " + p.getLongitud();
		System.out.println(descripcio);
	}

}
