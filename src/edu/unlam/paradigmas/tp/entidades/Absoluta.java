package edu.unlam.paradigmas.tp.entidades;

import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class Absoluta extends Promocion {

	public Absoluta(Atraccion[] atracciones, TipoDePromocion tipo) {
		super(atracciones, tipo);
	}

	private double precioFijo;

	public double getPrecio() {
		return precioFijo;
	}
}
