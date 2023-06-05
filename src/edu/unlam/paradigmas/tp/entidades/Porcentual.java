package edu.unlam.paradigmas.tp.entidades;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class Porcentual extends Promocion {

	public Porcentual(TipoDeAtraccion tipoDeAtraccion, Atraccion[] atracciones, TipoDePromocion tipoDePromocion) {
		super(tipoDeAtraccion, atracciones, tipoDePromocion);
	}

	@Override
	protected double calcularPrecioConDescuento() {

		double precioTotal = getPrecioOriginal();

		if (precioTotal > 5000)
			return precioTotal - precioTotal * 0.4;
		else if (precioTotal > 4000)
			return precioTotal - precioTotal * 0.3;
		else if (precioTotal > 3000)
			return precioTotal - precioTotal * 0.2;
		else
			return precioTotal - precioTotal * 0.1;
	}

}
