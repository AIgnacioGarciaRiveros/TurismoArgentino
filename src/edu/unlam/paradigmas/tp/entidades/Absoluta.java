package edu.unlam.paradigmas.tp.entidades;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class Absoluta extends Promocion {

	private static final double PRECIO_FIJO = 400;

	public Absoluta(TipoDeAtraccion tipoDeAtraccion, Atraccion[] atracciones, TipoDePromocion tipoDePromocion) {
		super(tipoDeAtraccion, atracciones, tipoDePromocion);
	}

	@Override
	protected double calcularPrecioConDescuento() {
		return getPrecioOriginal() - PRECIO_FIJO;
	}

	public static double getPrecioFijo() {
		return PRECIO_FIJO;
	}

}
