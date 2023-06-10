package edu.unlam.paradigmas.tp.entidades;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class Bonificada extends Promocion {

	public Bonificada(TipoDeAtraccion tipoDeAtraccion, Atraccion[] atracciones, TipoDePromocion tipoDePromocion) {
		super(tipoDeAtraccion, atracciones, tipoDePromocion);
	}

	@Override
	protected double calcularPrecioConDescuento() {

		Atraccion[] atracciones = getAtracciones();
		double precioMinimo = atracciones[0].getPrecio();

		for (int i = 1; i < atracciones.length; i++) {
			if (atracciones[i].getPrecio() < precioMinimo)
				precioMinimo = atracciones[i].getPrecio();
		}

		return getPrecioOriginal() - precioMinimo;
	}
}
