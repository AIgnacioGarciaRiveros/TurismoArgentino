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

	public void mostrarItinerario() {

		System.out.println(usuario + "\n");

		System.out.println("Promociones\n");
		for (int i = 0; i < promociones.size(); i++)
			System.out.println("Nro " + (i + 1) + ":\n" + promociones.get(i));

		System.out.println("Atracciones\n");
		for (int i = 0; i < atracciones.size(); i++)
			System.out.println("Nro " + (i + 1) + ":\n" + atracciones.get(i));

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
