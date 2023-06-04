package edu.unlam.paradigmas.tp.entidades;

import java.util.List;
import java.util.NoSuchElementException;

import edu.unlam.paradigmas.tp.interfaces.PromocionIterator;

public class PromocionesIteratorImplementacion implements PromocionIterator{

	private List<Promocion> promociones;
	private int posicion;
	private Usuario usuario;
	
	public PromocionesIteratorImplementacion(List<Promocion> promociones, Usuario usuario) {
		this.promociones = promociones;
		this.posicion = 0;
		this.usuario = usuario;
	}

	@Override
	public boolean hasNext() {
		return posicion < promociones.size();
	}

	@Override
	public Promocion next() {
		if(posicion == promociones.size()) {
			throw new NoSuchElementException();
		}
		Promocion promocion = promociones.get(posicion);
		boolean atraccionesDisponibles = this.atraccionesDisponibles(promocion.getAtracciones());
		while( promocion.getDuracion() > usuario.getTiempoDisponible() || 
			   promocion.getPrecioConDescuento() > usuario.getPresupuesto() ||
			   !atraccionesDisponibles ) {
			posicion ++;
			if(posicion == promociones.size()) {
				throw new NoSuchElementException();
			}
			promocion = promociones.get(posicion);
			atraccionesDisponibles = this.atraccionesDisponibles(promocion.getAtracciones());
		}
		posicion ++;
		return promocion;
	}
	
	@Override
	public void reset() {
		posicion = 0;
	}
	
	private boolean atraccionesDisponibles(Atraccion[] atracciones) {
		boolean disponible = false;
		if(atracciones.length > 0) {
			disponible = atracciones[0].getEstaDisponible();
			for(int i = 0; i < atracciones.length && disponible; i++) {
				disponible = atracciones[i].getEstaDisponible();
			}
		}
		
		return disponible;
	}
}
