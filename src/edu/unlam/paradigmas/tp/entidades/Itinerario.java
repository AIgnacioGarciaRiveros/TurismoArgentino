package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {

	private Usuario usuario;
	private List<Promocion> promociones;
	private List<Atraccion> atracciones;

	public Itinerario(Usuario usuario) {
		this.usuario = usuario;
		promociones = new ArrayList<>();
		atracciones = new ArrayList<>();
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

	public void agregarAtraccion(Atraccion atraccion) {
		atracciones.add(atraccion);
	}

	public void agregarPromocion(Promocion promocion) {
		promociones.add(promocion);
	}

	public double obtenerPrecioAtracciones() {
		double precioTotal = 0;
		for (Atraccion atraccion : atracciones)
			precioTotal += atraccion.getPrecio();
		return precioTotal;
	}

	public double obtenerPrecioPromociones() {
		double precioTotal = 0;
		for (Promocion promocion : promociones)
			precioTotal += promocion.getPrecioConDescuento();
		return precioTotal;
	}

	public double obtenerDuracionAtracciones() {
		double duracionTotal = 0;
		for (Atraccion atraccion : atracciones)
			duracionTotal += atraccion.getTiempo();
		return duracionTotal;
	}

	public double obtenerDuracionPromociones() {
		double duracionTotal = 0;
		for (Promocion promocion : promociones)
			duracionTotal += promocion.getDuracion();
		return duracionTotal;
	}

}
