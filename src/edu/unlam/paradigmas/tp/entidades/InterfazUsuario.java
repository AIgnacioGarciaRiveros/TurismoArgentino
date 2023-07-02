package edu.unlam.paradigmas.tp.entidades;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InterfazUsuario {

	public void saludarUsuario(String nombreUsuario) {
		System.out.println("======================== Bienvenido a Turismo Argentino ========================\n");
		System.out.println("Nombre visitante: " + nombreUsuario);
	}

	public void sugerirPromociones(List<Promocion> promociones, Map<String, Atraccion> atracciones, Usuario usuario,
			Itinerario itinerario) {
		Scanner scanner = new Scanner(System.in);
		SistemaDeSugerencia sistema = new SistemaDeSugerencia();
		char respuestaUsuario;
		Promocion promocion;
		System.out.println("\nTus recursos:" + "\n-Presupuesto: $" + String.format("%.2f", usuario.getPresupuesto())
				+ "\n-Tiempo: " + String.format("%.2f", usuario.getTiempoDisponible()) + "\n-Tu preferencia: "
				+ Atraccion.formatearTipoAtraccion(usuario.getAtraccionFavorita()) + "\n\n");
		PromocionIteratorImpl promocionIt = new PromocionIteratorImpl(promociones, usuario, atracciones);
		while (promocionIt.hasNext()) {
			try {
				promocion = promocionIt.next();
				System.out.println(promocion);
				do {
					System.out.println("Aceptas esta sugerencia? Ingrese S o N");
					respuestaUsuario = Character.toUpperCase(scanner.next().charAt(0));
				} while (respuestaUsuario != 'S' && respuestaUsuario != 'N');
				if (respuestaUsuario == 'S') {
					sistema.procesarCompraPromocion(usuario, promocion, atracciones, itinerario);
					System.out.println("\nTus recursos:" + "\n-Presupuesto: $"
							+ String.format("%.2f", usuario.getPresupuesto()) + "\n-Tiempo: "
							+ String.format("%.2f", usuario.getTiempoDisponible()) + "\n-Tu preferencia: "
							+ Atraccion.formatearTipoAtraccion(usuario.getAtraccionFavorita()) + "\n\n");
				}

			} catch (NoSuchElementException e) {
				System.out.println(" No podes seguir comprando PROMOCIONES, te mostramos atracciones disponibles:");
			}
			System.out.println("\n");
		}

	}

	public void sugerirAtracciones(Map<String, Atraccion> atracciones, Usuario usuario, Itinerario itinerario) {
		Scanner scanner = new Scanner(System.in);
		SistemaDeSugerencia sistema = new SistemaDeSugerencia();
		char respuestaUsuario;
		Atraccion atraccion;
		System.out.println("\nTus recursos:" + "\n-Presupuesto: $" + String.format("%.2f", usuario.getPresupuesto())
				+ "\n-Tiempo: " + String.format("%.2f", usuario.getTiempoDisponible()) + "\n-Tu preferencia: "
				+ Atraccion.formatearTipoAtraccion(usuario.getAtraccionFavorita()) + "\n\n");
		AtraccionIteratorImpl atraccionIt = new AtraccionIteratorImpl(atracciones, usuario);
		while (atraccionIt.hasNext()) {

			atraccion = atraccionIt.next();
			if (atraccion != null) {
				System.out.println(atraccion);
				do {
					System.out.println("Aceptas esta sugerencia? Ingrese S o N");
					respuestaUsuario = Character.toUpperCase(scanner.next().charAt(0));
				} while (respuestaUsuario != 'S' && respuestaUsuario != 'N');
				if (respuestaUsuario == 'S') {
					sistema.procesarCompraAtraccion(atraccion, usuario, itinerario);
					System.out.println("\nTus recursos:" + "\n-Presupuesto: $"
							+ String.format("%.2f", usuario.getPresupuesto()) + "\n-Tiempo: "
							+ String.format("%.2f", usuario.getTiempoDisponible()) + "\n-Tu preferencia: "
							+ Atraccion.formatearTipoAtraccion(usuario.getAtraccionFavorita()) + "\n\n");
				}
			} else
				System.out.println("\nNo hay mas opciones para tus recursos\n");
		}
		System.out.println("========================================================================\n");
	}
	
	public void mostrarItinerario(Itinerario itinerario) {
		System.out.println("Resultado de tu compra\n");
		System.out.println("Usuario: " + itinerario.getUsuario().getNombre() + "\n");
		System.out.println("======================== Promociones adquiridas ========================\n");

		int numeroDePromocion = 1;
		List<Promocion> promociones = itinerario.getPromociones();
		if (promociones.size() > 0) {
			for (Promocion promocion : promociones) {
				System.out.println("     Promocion Nro " + numeroDePromocion++ + "\n");
				System.out.println("     " + String.format("%-29s", "Nombre") + String.format("%-30s", "Duracion")
						+ String.format("%-30s", "Precio") + "\n");
				for (Atraccion atraccion : promocion.getAtracciones()) {

					System.out
							.println("    " + String.format("%-30s", atraccion.getNombre().replaceAll("(?=[A-Z])", " "))
									+ String.format("%-30s", atraccion.getTiempo() + " horas") + "$"
									+ String.format("%-30s", atraccion.getPrecio()));
				}
				System.out.println("\n");
			}
			System.out.println(String.format("%-34s", "- Total de promociones") + itinerario.obtenerDuracionPromociones()
					+ " horas" + String.format("%28s", "$" + itinerario.obtenerPrecioPromociones()));

		} else {
			System.out.println("El usuario no adquirio ninguna promocion");
		}

		System.out.println("\n======================== Atracciones adquiridas ========================\n");
		List<Atraccion> atracciones = itinerario.getAtracciones();
		if (atracciones.size() > 0) {
			System.out.println("     " + String.format("%-29s", "Nombre") + String.format("%-30s", "Duracion")
					+ String.format("%-30s", "Precio") + "\n");
			for (Atraccion atraccion : atracciones) {
				System.out.println("    " + String.format("%-30s", atraccion.getNombre().replaceAll("(?=[A-Z])", " "))
						+ String.format("%-30s", atraccion.getTiempo() + " horas") + "$"
						+ String.format("%-30s", atraccion.getPrecio()));
			}
			System.out.println("\n" + String.format("%-34s", "- Total de atracciones") + itinerario.obtenerDuracionAtracciones()
					+ " horas" + String.format("%28s", "$" + itinerario.obtenerPrecioAtracciones()));
			System.out.println("\n========================================================================\n");
		} else {
			System.out.println("El usuario no adquirio ninguna atraccion");
		}
	}
}
