package com.consoleapp.menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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

//	private static final String PATH_GENERIC = "..\\main\\";
	private static final String PATH_COMPRES = ".\\src\\com\\consoleapp\\main\\compres.txt";
	private static final String PATH_PRODUCTES = ".\\src\\com\\consoleapp\\main\\productes.txt";
	private static final String PATH_PRODUCTORS = ".\\src\\com\\consoleapp\\main\\productors.txt";

	private static final String UNZERO = "0";
	private static final String DOSZERO = "00";
	private static final String TRESZERO = "000";

	private static List<Producte> llista = new ArrayList<Producte>();
	private static List<Productor> llistaProductors = new ArrayList<Productor>();
	private static List<Compra> llistaCompres = new ArrayList<Compra>();
	private static Scanner scanner = new Scanner(System.in);

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static void opcioMenu(int opcio) {

		switch (opcio) {
		case 1:
			if (llista.isEmpty() && llistaProductors.isEmpty()) {
				carregarDades();
			} else {
				System.out.println("Les dades han estat carregades anteriorment.");
			}
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
			guardar();
			break;
		}
	}

	private static void carregarDades() {
		carregarProductors();
		carregarProductes();
		carregarCompres();
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

				// Calcular ID Producte nou.
				String idNova = ProducteGranel.PREFIX
						+ (llista.size() < 10 ? TRESZERO
								: llista.size() < 100 ? DOSZERO : llista.size() < 1000 ? UNZERO : "")
						+ (llista.size() + 1);
				producte.setId(idNova);

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
						System.out.println(
								"El productor sel·leccionat no existeix," + " selecciona un productor v�lid");
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
				String idNova = ProducteUnitat.PREFIX
						+ (llista.size() < 10 ? TRESZERO
								: llista.size() < 100 ? DOSZERO : llista.size() < 1000 ? UNZERO : "")
						+ (llista.size() + 1);
				producte.setId(idNova);

//				Nom producte
				System.out.println("Nom producte: ");
				String nom = scanner.next();
				producte.setNom(nom);

//				Productor
				System.out.println("Quin dels seg�ents productors:");
				boolean p = false;
				while (p == false) {
					int cont = getLlistaProductors();
					System.out.println("Escriu el ID del productor:");
					String idProd = scanner.next();
					if (Integer.parseInt(idProd) > cont || Integer.parseInt(idProd) < 0) {
						System.out.println(
								"El productor sel�leccionat no existeix," + " selecciona un productor v�lid");
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
				System.out.println("�s cel�ac: ( Opcions v�lides (S) o (N) )");
				String celiac = scanner.next();
				producte.setCeliac(celiac.toUpperCase().equals("S") ? true : false);
				j = true;
				llista.add(producte);

			} else if (r.equals("0")) {
				j = true;
			} else {
				System.out.println("Opcions v�lides, G o U, escriu 0 per tornar al men�");
			}
		}
	}

	private static void esborrarProducte() {
		boolean t = false;
		String r = "";
		while (t == false) {
			System.out.println("Escolleix dels seg�ents productors el que vols eliminar tots els productes.");
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
						"El id del productor es incorrecte, escull un ID correcte o escriu 0 per sortir al men�.");
			} else {
				t = true;
			}
		}

		System.out.println("Est�s segur que vols esborrar els seg�ents productes?");
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

		System.out.println("S� (S) o No (N), 0 per tornar al men�.");
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
				System.out.println("Les opcions s�n: S� (S) o No (N), 0 per tornar al men�.");
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
						System.out.println("Opcions v�lides, P o E o 2, escriu 0 per tornar al men�");
					}
				}
			}
		}
	}

	private static void afegirCompra() {

		// TODO: acabar les verificacions de quan es compra alguna cosa, comprobar stock
		// i tal.

		Compra compra = new Compra();

		System.out.println("Quins dels seg�ents productes vols comprar:");

		llistarDades();

		boolean f = false;
		while (f == false) {

			System.out.println("Introdueix el ID:");
			String id = scanner.next();

			Producte producteSeleccionat = getProducteById(id);

			if (producteSeleccionat != null) {

				// producte general
				compra.setProducte(producteSeleccionat);

				// stock
				boolean rep = false;
				while (rep == false) {
					// quantitat
					System.out.println("Introdueix la quantitat:");
					long r = scanner.nextInt();
					if (producteSeleccionat.getStock() < r) {
						System.out.println(
								"No hi han estoc, actualment en queden " + producteSeleccionat.getStock() + "unitats");
					} else {
						compra.setQuantitat(r);
						for (Producte p : llista) {
							if (p.getId().equals(producteSeleccionat.getId())) {
								p.setStock(p.getStock() - r);
							}
						}

						rep = true;
					}
				}

				// data
				System.out.println("Introdueix la data de compra (�nic format correcte dia/mes/any)");
				String data = scanner.next();

				try {
					compra.setData(sdf.parse(data));
				} catch (ParseException e) {
					System.out.println(
							"Has introdu�t una data incorrecta, introdueix una data correcta o escriu 0 per tornar al  men�.");
				}

				// cost
				compra.setTotal(compra.getQuantitat() * compra.getProducte().getPreu());

				guardarCompra(compra);

				f = true;
			} else if (id.equals(0)) {
				f = true;
			} else {
				System.out.println(
						"L'ID sel�leccionada no existeix, sel�lecciona una ID correcta, o introdueix 0 per sortit al men�.");
			}

		}

	}

	private static void guardarCompra(Compra compra) {
		llistaCompres.add(compra);
		System.out.println("Compra guardada correctament.");
	}

	private static void llistarCompres() {
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
		System.out.println("En quanta dist�ncia vols consultar els productors i productes? (Introdu�r KMS)");
		double kmsSolicitats = scanner.nextDouble();

		double latR = Math.toRadians(Double.parseDouble(Console.getLatitud()));
		double lonR = Math.toRadians(Double.parseDouble(Console.getLongitud()));

		for (Productor p : llistaProductors) {

			double radiTerra = 6378.137;

			double latProdR = Math.toRadians(Double.parseDouble(p.getLatitud()));
			double lonProdR = Math.toRadians(Double.parseDouble(p.getLongitud()));

			double dLat = (latProdR - latR);
			double dLon = (lonProdR - lonR);

			double sinLat = Math.sin(dLat / 2);
			double sinLon = Math.sin(dLon / 2);

			double a = (sinLat * sinLat) + Math.cos(latR) * Math.cos(latProdR) * (sinLon * sinLon);
			double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

			double distanciaEnKms = radiTerra * c;

			double distanciaEnKmsRounded = (Math.round(distanciaEnKms * 100.0) / 100.0);

			if (kmsSolicitats > distanciaEnKms) {
				System.out.println("El seg�ent productor est� a " + distanciaEnKmsRounded + "Kms.");

				System.out.println("Productor: ");
				toStringProductors(p);

				System.out.println("Productes del productor: ");
				for (Producte producte : llista) {
					if (producte.getProductor().getId().equals(p.getId())) {
						toStringProductes(producte);
					}
				}
			}

		}

	}

	public static List<Productor> carregarProductors() {
		FileInputStream fis;
		try {
			llistaProductors.clear();
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
		return llistaProductors;

	}

	public static List<Producte> carregarProductes() {
		FileInputStream fis;
		try {
			llista.clear();
			fis = new FileInputStream(new File(PATH_PRODUCTES));
			int r = 0;
			String lineaGeneral = "";
			while ((r = fis.read()) != -1) {
				lineaGeneral = lineaGeneral.concat(Character.toString((char) r));
			}
			
			String linea = lineaGeneral.replace("\r", "");
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

	private static void carregarCompres() {
		ObjectInputStream objectinputstream = null;
		try {
			FileInputStream streamIn = new FileInputStream(new File(PATH_COMPRES));
			objectinputstream = new ObjectInputStream(streamIn);
			List<Compra> llistaCompresRecuperada = (List<Compra>) objectinputstream.readObject();
			llistaCompres = llistaCompresRecuperada;
		} catch (Exception e) {
		}
	}

	private static void mostrarCompres() {
		for (Compra c : llistaCompres) {
			toStringCompres(c);
		}
	}

	private static void toStringCompres(Compra compra) {
		String descripcio = "Producte: " + compra.getProducte().getNom() + "(" + compra.getProducte().getId() + ")"
				+ ", Quantitat: " + compra.getQuantitat() + ", Data: " + sdf.format(compra.getData()) + ", Total: "
				+ compra.getTotal();
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

	private static void guardar() {
		System.out.println("Vols guardar la informació de les classes als fitxers? (S->sí)(N->no)");
		String resp = scanner.next();
		if (resp.toUpperCase().equals("S")) {
			try {

				// Fitxer compres.
				FileOutputStream fileOut = new FileOutputStream(new File(PATH_COMPRES));
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
				objectOut.writeObject(llistaCompres);
				objectOut.close();

//				// Fitxer productes.
				String totsElsPAGuardar = new String();
				for (Producte producte : llista) {
					if (producte instanceof ProducteGranel) {
						ProducteGranel p = (ProducteGranel) producte;
						String productes = p.getId() + ";" + p.getNom() + ";" + p.getProductor().getId() + ";"
								+ p.getStock() + ";" + p.getPreu() + ";" + p.getPes() + "\n";
						totsElsPAGuardar = totsElsPAGuardar.concat(productes);
					} else {
						ProducteUnitat p = (ProducteUnitat) producte;
						String productes = p.getId() + ";" + p.getNom() + ";" + p.getProductor().getId() + ";"
								+ p.getStock() + ";" + p.getPreu() + ";" + (p.isCeliac() ? 1 : 0) + "\n";
						totsElsPAGuardar = totsElsPAGuardar.concat(productes);
					}
				}

				Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PATH_PRODUCTES), "UTF-8"));
				writer.write(totsElsPAGuardar);
				writer.close();

				System.out.println("Fitxers guardats correctament.");
				System.out.println("PROGRAMA FINALITZAT");
				System.exit(0);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("PROGRAMA FINALITZAT");
			System.exit(0);
		}

	}

}
