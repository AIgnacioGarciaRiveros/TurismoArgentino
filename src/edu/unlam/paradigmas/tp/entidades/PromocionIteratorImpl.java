package edu.unlam.paradigmas.tp.entidades;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import edu.unlam.paradigmas.tp.interfaces.PromocionIterator;

public class PromocionIteratorImpl implements PromocionIterator {

	private List<Promocion> promociones;
	private Map<String, Atraccion> atracciones;
	private int posicion;
	private Usuario usuario;

	public PromocionIteratorImpl(List<Promocion> promociones, Usuario usuario, Map<String, Atraccion> atracciones) {
		this.promociones = promociones;
		this.posicion = 0;
		this.usuario = usuario;
		this.atracciones = atracciones;
	}

	@Override
	public boolean hasNext() {
		return posicion < promociones.size();
	}

	@Override
	public Promocion next() {
		if (posicion == promociones.size()) {
			throw new NoSuchElementException();
		}
		Promocion promocion = promociones.get(posicion);
		boolean atraccionesDisponibles = atraccionesDisponibles(promocion.getAtracciones());
		while (promocion.getDuracion() > usuario.getTiempoDisponible()
				|| promocion.getPrecioConDescuento() > usuario.getPresupuesto() || !atraccionesDisponibles) {
			posicion++;

			if (posicion == promociones.size())
				throw new NoSuchElementException();

			promocion = promociones.get(posicion);
			atraccionesDisponibles = atraccionesDisponibles(promocion.getAtracciones());
		}
		posicion++;
		return promocion;
	}

	@Override
	public void reset() {
		posicion = 0;
	}

	private boolean atraccionesDisponibles(Atraccion[] atraccionesIncluidas) {
		boolean disponible = true;
		for (int i = 0; i < atraccionesIncluidas.length && disponible; i++) {
			disponible = atracciones.get(atraccionesIncluidas[i].getNombre()).getEstaDisponible();
			if (!disponible)
				atracciones.get(atraccionesIncluidas[i].getNombre()).setEstaDisponible(disponible);
		}
		return disponible;
	}
}
