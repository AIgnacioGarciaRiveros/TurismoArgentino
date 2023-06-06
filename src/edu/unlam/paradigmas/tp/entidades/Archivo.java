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

	private String nombre;

	public Archivo(String nombre) {
		this.nombre = nombre;
	}

	public void grabarItinerarioEnArchivo(String nombreArchivo) {
		try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo))) {

		} catch (IOException e) {
			System.err.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}

	public void crearArchivoItinerario(List<Itinerario> itinerario) {
		FileWriter file = null;
		PrintWriter printWriter = null;
		try {
			file = new FileWriter("archivos/" + this.nombre + ".out");
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
							+ itinerarioPorUsuario.obtenerDuracionDePromociones() + " horas"
							+ String.format("%28s", "$" + itinerarioPorUsuario.obtenerPrecioDePromociones()));

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
							+ itinerarioPorUsuario.obtenerDuracionDeAtracciones() + " horas"
							+ String.format("%28s", "$" + itinerarioPorUsuario.obtenerPrecioDeAtracciones()));
				} else {
					printWriter.println("El usuario no adquirio ninguna atraccion");
				}

				printWriter.println("\n" + String.format("%-34s", "- Total por usuario")
						+ (itinerarioPorUsuario.obtenerDuracionDeAtracciones()
								+ itinerarioPorUsuario.obtenerDuracionDePromociones())
						+ " horas" + String.format("%28s", "$" + (itinerarioPorUsuario.obtenerPrecioDeAtracciones()
								+ itinerarioPorUsuario.obtenerPrecioDePromociones())));

				printWriter.println("\n========================================================================\n");
			}

		} catch (

		Exception e) {
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

	public Map<String, Atraccion> leerArchivoAtraccion() {

		Scanner scanner = null;
		Map<String, Atraccion> atracciones = new HashMap<>();

		try {
			File file = new File("archivos/" + this.nombre + ".in");

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

	public List<Promocion> leerArchivoPromocion(Map<String, Atraccion> atracciones) {

		Scanner scanner = null;
		List<Promocion> promociones = new ArrayList<>();

		try {
			File file = new File("archivos/" + this.nombre + ".in");

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

	public List<Usuario> leerArchivoUsuario() {

		Scanner scanner = null;
		List<Usuario> usuarios = new ArrayList<>();

		try {
			File file = new File("archivos/" + this.nombre + ".in");

			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			scanner.nextLine(); // Saco la linea de titulos

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

}
