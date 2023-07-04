package edu.unlam.paradigmas.tp.entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class Archivo {

	public static final String RUTA_ARCHIVOS_ENTRADA = "archivos/in/";
	public static final String RUTA_ARCHIVOS_TESTS_ENTRADA = "archivos/test/in/";
	public static final String RUTA_ARCHIVOS_SALIDA = "archivos/out/";
	public static final String RUTA_ARCHIVOS_TESTS_SALIDA = "archivos/test/out/";

	private String nombre;

	public Archivo(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> leerArchivoUsuario(String rutaArchivo) {

		Scanner scanner = null;
		List<Usuario> usuarios = new ArrayList<>();

		try {
			File file = new File(rutaArchivo + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			scanner.nextLine();

			while (scanner.hasNext())
				usuarios.add(new Usuario(scanner.next(), scanner.nextDouble(), scanner.nextDouble(),
						TipoDeAtraccion.valueOf(scanner.next())));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return usuarios;
	}

	public Map<String, Atraccion> leerArchivoAtraccion(String rutaArchivo) {

		Scanner scanner = null;
		Map<String, Atraccion> atracciones = new HashMap<>();

		try {
			File file = new File(rutaArchivo + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);
			scanner.nextLine();

			while (scanner.hasNext()) {
				String nombreAtraccion = scanner.next();
				atracciones.put(nombreAtraccion, new Atraccion(nombreAtraccion, scanner.nextDouble(),
						scanner.nextDouble(), scanner.nextInt(), TipoDeAtraccion.valueOf(scanner.next())));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return atracciones;
	}

	public List<Promocion> leerArchivoPromocion(Map<String, Atraccion> atracciones, String rutaArchivo) {

		Scanner scanner = null;
		List<Promocion> promociones = new ArrayList<>();

		try {
			File file = new File(rutaArchivo + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			scanner.nextLine();

			while (scanner.hasNext()) {

				TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.valueOf(scanner.next());
				String[] nombresAtracciones = scanner.next().split(",");

				Atraccion[] atraccionesPromocion = new Atraccion[nombresAtracciones.length];

				for (int i = 0; i < nombresAtracciones.length; i++) {
					Atraccion atraccionObtenida = atracciones.get(nombresAtracciones[i]);
					atraccionesPromocion[i] = atraccionObtenida;
				}

				TipoDePromocion tipoDePromocion = TipoDePromocion.valueOf(scanner.next());

				switch (tipoDePromocion) {
				case ABSOLUTA:
					promociones.add(new Absoluta(tipoDeAtraccion, atraccionesPromocion, tipoDePromocion));
					break;

				case BONIFICADA:
					promociones.add(new Bonificada(tipoDeAtraccion, atraccionesPromocion, tipoDePromocion));
					break;

				case PORCENTUAL:
					promociones.add(new Porcentual(tipoDeAtraccion, atraccionesPromocion, tipoDePromocion));
					break;

				default:
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return promociones;
	}

	public void crearArchivoItinerario(List<Itinerario> itinerario, String rutaArchivo) {
		FileWriter file = null;
		PrintWriter printWriter = null;
		try {
			file = new FileWriter(rutaArchivo + this.nombre + ".out");
			printWriter = new PrintWriter(file);

			printWriter.println("==================== Bienvenido a Turismo Argentino ====================\n");

			for (Itinerario itinerarioPorUsuario : itinerario) {
				printWriter.println("Usuario: " + itinerarioPorUsuario.getUsuario().getNombre() + "\n");
				printWriter.println("======================== Promociones adquiridas ========================\n");

				int numeroDePromocion = 1;
				if (itinerarioPorUsuario.getPromociones().size() > 0) {
					for (Promocion promocion : itinerarioPorUsuario.getPromociones()) {
						printWriter.println("     Promocion Nro " + numeroDePromocion++ + "\n");
						printWriter.println("     " + String.format("%-29s", "Nombre")
								+ String.format("%-30s", "Duracion") + String.format("%-30s", "Precio") + "\n");
						for (Atraccion atraccion : promocion.getAtracciones()) {
							printWriter.println(
									"    " + String.format("%-30s", atraccion.getNombre().replaceAll("(?=[A-Z])", " "))
											+ String.format("%-30s", atraccion.getTiempo() + " horas") + "$"
											+ String.format("%-30s", atraccion.getPrecio()));
						}
						printWriter.println("\n");
					}
					printWriter.println(String.format("%-34s", "- Total de promociones")
							+ itinerarioPorUsuario.obtenerDuracionPromociones() + " horas"
							+ String.format("%28s", "$" + itinerarioPorUsuario.obtenerPrecioPromociones()));

				} else {
					printWriter.println("El usuario no adquirio ninguna promocion");
				}

				printWriter.println("\n======================== Atracciones adquiridas ========================\n");
				if (itinerarioPorUsuario.getAtracciones().size() > 0) {
					printWriter.println("     " + String.format("%-29s", "Nombre") + String.format("%-30s", "Duracion")
							+ String.format("%-30s", "Precio") + "\n");
					for (Atraccion atraccion : itinerarioPorUsuario.getAtracciones()) {
						printWriter.println(
								"    " + String.format("%-30s", atraccion.getNombre().replaceAll("(?=[A-Z])", " "))
										+ String.format("%-30s", atraccion.getTiempo() + " horas") + "$"
										+ String.format("%-30s", atraccion.getPrecio()));
					}
					printWriter.println("\n" + String.format("%-34s", "- Total de atracciones")
							+ itinerarioPorUsuario.obtenerDuracionAtracciones() + " horas"
							+ String.format("%28s", "$" + itinerarioPorUsuario.obtenerPrecioAtracciones()));
				} else {
					printWriter.println("El usuario no adquirio ninguna atraccion");
				}

				printWriter.println("\n" + String.format("%-34s", "- Total por usuario")
						+ (itinerarioPorUsuario.obtenerDuracionAtracciones()
								+ itinerarioPorUsuario.obtenerDuracionPromociones())
						+ " horas" + String.format("%28s", "$" + (itinerarioPorUsuario.obtenerPrecioAtracciones()
								+ itinerarioPorUsuario.obtenerPrecioPromociones())));

				printWriter.println("\n========================================================================\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
