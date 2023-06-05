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
		char respuestaUsuario;
		Promocion promocion;
		System.out.println("\nTus recursos:" + "\n\tPresupuesto: $" + String.format("%.2f", usuario.getPresupuesto())
				+ "\n\tTiempo: " + String.format("%.2f", usuario.getTiempoDisponible()) + "\n\tTu preferencia: "
				+ usuario.getAtraccionFavorita());
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
					procesarCompraPromocion(usuario, promocion, atracciones, itinerario);
					System.out.println(
							"\nTus recursos:" + "\n\tPresupuesto: $" + String.format("%.2f", usuario.getPresupuesto())
									+ "\n\tTiempo: " + String.format("%.2f", usuario.getTiempoDisponible())
									+ "\n\tTu preferencia: " + usuario.getAtraccionFavorita());
				}

			} catch (NoSuchElementException e) {
				System.out.println(" No podes seguir comprando PROMOCIONES, te mostramos atracciones disponibles:");
			}

			// System.out.println(atracciones);

			System.out.println("\n");
		}

	}

	public void sugerirAtracciones(Map<String, Atraccion> atracciones, Usuario usuario, Itinerario itinerario) {
		Scanner scanner = new Scanner(System.in);
		char respuestaUsuario;
		Atraccion atraccion;
		System.out.println("\nTus recursos:" + "\n\tPresupuesto: $" + String.format("%.2f", usuario.getPresupuesto())
				+ "\n\tTiempo: " + String.format("%.2f", usuario.getTiempoDisponible()) + "\n\tTu preferencia: "
				+ usuario.getAtraccionFavorita());
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
					procesarCompraAtraccion(atraccion, usuario, itinerario);
					System.out.println(
							"\nTus recursos:" + "\n\tPresupuesto: $" + String.format("%.2f", usuario.getPresupuesto())
									+ "\n\tTiempo: " + String.format("%.2f", usuario.getTiempoDisponible())
									+ "\n\tTu preferencia: " + usuario.getAtraccionFavorita());
				}
			} else
				System.out.println("No hay mas opciones para tus recursos");

			// System.out.println(atracciones);

		}
		System.out.println("========================================================================\n\n");
	}

	public void procesarCompraAtraccion(Atraccion atraccion, Usuario usuario, Itinerario itinerario) {
		usuario.setPresupuesto(usuario.getPresupuesto() - atraccion.getPrecio());
		usuario.setTiempoDisponible(usuario.getTiempoDisponible() - atraccion.getTiempo());
		itinerario.agregarAtraccion(atraccion);
		atraccion.setEstaDisponible(false);
		atraccion.setCupoDiario(atraccion.getCupoDiario() - 1);
	}

	public void procesarCompraPromocion(Usuario usuario, Promocion promocion, Map<String, Atraccion> atracciones,
			Itinerario itinerario) {
		usuario.setPresupuesto(usuario.getPresupuesto() - promocion.getPrecioConDescuento());
		usuario.setTiempoDisponible(usuario.getTiempoDisponible() - promocion.getDuracion());
		itinerario.agregarPromocion(promocion);
		Atraccion[] atraccionesOfertadas = promocion.getAtracciones();
		for (int i = 0; i < atraccionesOfertadas.length; i++) {
			Atraccion atraccionEncontrada = atracciones.get(atraccionesOfertadas[i].getNombre());
			atraccionEncontrada.setEstaDisponible(false);
			atraccionEncontrada.setCupoDiario(atraccionEncontrada.getCupoDiario() - 1);
		}
	}

	public void resetearEstaDisponible(Map<String, Atraccion> atracciones) {
		for (Map.Entry<String, Atraccion> entry : atracciones.entrySet()) {
			Atraccion atraccion = entry.getValue();
			if (atraccion.getCupoDiario() != 0 && atraccion.getEstaDisponible() == false)
				atraccion.setEstaDisponible(true);
		}
	}
}
