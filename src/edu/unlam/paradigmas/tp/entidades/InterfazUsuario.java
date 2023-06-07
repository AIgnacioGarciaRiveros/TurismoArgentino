package edu.unlam.paradigmas.tp.entidades;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InterfazUsuario {

	public void iniciarSistema(String nombreUsuario) {
		System.out.println("======================== Bienvenido a Turismo Argentino ========================\n");
		System.out.println("Nombre visitante: " + nombreUsuario);
	}

	public void sugerirPromociones(List<Promocion> promociones, Map<String, Atraccion> atracciones, Usuario usuario,
			Itinerario itinerario) {
		Scanner scanner = new Scanner(System.in);
		SistemaDeSugerencia sistema= new SistemaDeSugerencia();
		char respuestaUsuario;
		Promocion promocion;
		System.out.println("\nTus recursos:" + "\n-Presupuesto: $" + String.format("%.2f", usuario.getPresupuesto())
				+ "\n-Tiempo: " + String.format("%.2f", usuario.getTiempoDisponible()) + "\n-Tu preferencia: "
				+ Atraccion.formatoTipoDeAtraccion(usuario.getAtraccionFavorita()) + "\n\n");
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
							+ Atraccion.formatoTipoDeAtraccion(usuario.getAtraccionFavorita()) + "\n\n");
				}

			} catch (NoSuchElementException e) {
				System.out.println(" No podes seguir comprando PROMOCIONES, te mostramos atracciones disponibles:");
			}
			System.out.println("\n");
		}

	}

	public void sugerirAtracciones(Map<String, Atraccion> atracciones, Usuario usuario, Itinerario itinerario) {
		Scanner scanner = new Scanner(System.in);
		SistemaDeSugerencia sistema= new SistemaDeSugerencia();
		char respuestaUsuario;
		Atraccion atraccion;
		System.out.println("\nTus recursos:" + "\n-Presupuesto: $" + String.format("%.2f", usuario.getPresupuesto())
				+ "\n-Tiempo: " + String.format("%.2f", usuario.getTiempoDisponible()) + "\n-Tu preferencia: "
				+ Atraccion.formatoTipoDeAtraccion(usuario.getAtraccionFavorita()) + "\n\n");
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
							+ Atraccion.formatoTipoDeAtraccion(usuario.getAtraccionFavorita()) + "\n\n");
				}
			} else
				System.out.println("\nNo hay mas opciones para tus recursos\n");
		}
		System.out.println("========================================================================\n");
	}
}
