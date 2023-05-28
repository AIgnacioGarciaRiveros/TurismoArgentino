package edu.unlam.paradigmas.tp.entidades;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class Absoluta extends Promocion {

	private double precioFijo;
	
	public Absoluta(TipoDeAtraccion tipoDeAtraccion, Atraccion[] atracciones, TipoDePromocion tipoDePromocion) {
		super(tipoDeAtraccion, atracciones, tipoDePromocion);
	}

	public double getPrecio() {
		return precioFijo;
	}
}
