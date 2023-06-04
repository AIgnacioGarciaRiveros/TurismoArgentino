package edu.unlam.paradigmas.tp.entidades;

import java.util.List;
import java.util.NoSuchElementException;

import edu.unlam.paradigmas.tp.interfaces.AtraccionIterator;

public class AtraccionesIteratorImplementacion implements AtraccionIterator{
	
	private List<Atraccion> atracciones;
	private int posicion;
	private Usuario usuario;
	
	public AtraccionesIteratorImplementacion(List<Atraccion> atracciones, Usuario usuario) {
		this.atracciones = atracciones;
		this.posicion = 0;
		this.usuario = usuario;
	}

	@Override
	public boolean hasNext() {
		return posicion < atracciones.size();
	}

	@Override
	public Atraccion next() {

		Atraccion atraccion = null;
		if(posicion < atracciones.size()) {
			atraccion = atracciones.get(posicion);
		}
		while( atraccion.getCupoDiario() == 0 || 
			   atraccion.getPrecio() > usuario.getPresupuesto() ||
			   atraccion.getTiempo() > usuario.getTiempoDisponible()||
			   !atraccion.getEstaDisponible()) {
			posicion ++;
			if(posicion < atracciones.size()) {
				atraccion = atracciones.get(posicion);
			}
		}
		if(posicion == atracciones.size() && atraccion != null) {
			atraccion = null;
		}
		else if(atraccion != null){
			posicion ++;			
		}
		return atraccion;
	}
	
	@Override
	public void reset() {
		posicion = 0;
	}

	
}
