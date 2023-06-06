package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {

	Usuario usuario;
	List<Promocion> promociones;
	List<Atraccion> atracciones;

	public Itinerario(Usuario usuario) {
		this.usuario = usuario;
		promociones = new ArrayList<>();
		atracciones = new ArrayList<>();
	}

	public void agregarAtraccion(Atraccion atraccion) {
		atracciones.add(atraccion);
	}

	public void agregarPromocion(Promocion promocion) {
		promociones.add(promocion);
	}

	public double obtenerPrecioDeAtracciones() {
		double precioTotal = 0;
		for (Atraccion atraccion : atracciones)
			precioTotal += atraccion.getPrecio();
		return precioTotal;
	}

	public double obtenerPrecioDePromociones() {
		double precioTotal = 0;
		for (Promocion promocion : promociones)
			precioTotal += promocion.getPrecioConDescuento();
		return precioTotal;
	}

	public double obtenerDuracionDeAtracciones() {
		double duracionTotal = 0;
		for (Atraccion atraccion : atracciones)
			duracionTotal += atraccion.getTiempo();
		return duracionTotal;
	}

	public double obtenerDuracionDePromociones() {
		double duracionTotal = 0;
		for (Promocion promocion : promociones)
			duracionTotal += promocion.getDuracion();
		return duracionTotal;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void mostrarItinerario() {

		System.out.println("Usuario: " + usuario.getNombre() + "\n");
		System.out.println("======================== Promociones adquiridas ========================\n");

		int numeroDePromocion = 1;
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
			System.out.println(String.format("%-34s", "- Total de promociones") + obtenerDuracionDePromociones()
					+ " horas" + String.format("%28s", "$" + obtenerPrecioDePromociones()));

		} else {
			System.out.println("El usuario no adquirio ninguna promocion");
		}

		System.out.println("\n======================== Atracciones adquiridas ========================\n");
		if (atracciones.size() > 0) {
			System.out.println("     " + String.format("%-29s", "Nombre") + String.format("%-30s", "Duracion")
					+ String.format("%-30s", "Precio") + "\n");
			for (Atraccion atraccion : atracciones) {
				System.out.println("    " + String.format("%-30s", atraccion.getNombre().replaceAll("(?=[A-Z])", " "))
						+ String.format("%-30s", atraccion.getTiempo() + " horas") + "$"
						+ String.format("%-30s", atraccion.getPrecio()));
			}
			System.out.println("\n" + String.format("%-34s", "- Total de atracciones") + obtenerDuracionDeAtracciones()
					+ " horas" + String.format("%28s", "$" + obtenerPrecioDeAtracciones()));
			System.out.println("\n========================================================================\n");
		} else {
			System.out.println("El usuario no adquirio ninguna atraccion");
		}
	}

	@Override
	public String toString() {

		String formatoItinerario = usuario.toString() + "\n\n";

		formatoItinerario += "Promociones\n\n";

		for (int i = 0; i < promociones.size(); i++)
			formatoItinerario += "Nro " + (i + 1) + ":\n" + promociones.get(i) + "\n";

		formatoItinerario += "\nAtracciones\n\n";
		for (int i = 0; i < atracciones.size(); i++)
			formatoItinerario += "Nro " + (i + 1) + ":\n" + atracciones.get(i) + "\n";

		return formatoItinerario;
	}

}
