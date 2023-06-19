package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.unlam.paradigmas.tp.interfaces.AtraccionIterator;

public class AtraccionIteratorImpl implements AtraccionIterator {

	private Map<String, Atraccion> atracciones;
	private int posicion;
	private Usuario usuario;

	public AtraccionIteratorImpl(Map<String, Atraccion> atracciones, Usuario usuario) {
		this.atracciones = atracciones;
		this.posicion = 0;
		this.usuario = usuario;
	}

	@Override
	public boolean hasNext() {
		List<Atraccion> atraccionesList = new ArrayList<>(atracciones.values());
		return posicion < atraccionesList.size();
	}

	@Override
	public Atraccion next() {
		List<Atraccion> atraccionesList = new ArrayList<>(atracciones.values());
		Atraccion atraccion = null;
		while (posicion < atraccionesList.size()) {
			atraccion = atraccionesList.get(posicion);
			if (atraccion.getCupoDiario() == 0 || atraccion.getPrecio() > usuario.getPresupuesto()
					|| atraccion.getTiempo() > usuario.getTiempoDisponible() || !atraccion.getDisponibilidad()) {
				posicion++;
			} else {
				posicion++;
				return atraccion;
			}
		}
		return null;
	}

	@Override
	public void reset() {
		posicion = 0;
	}

}
