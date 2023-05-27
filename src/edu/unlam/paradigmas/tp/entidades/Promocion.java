package edu.unlam.paradigmas.tp.entidades;

import java.util.Arrays;

import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public abstract class Promocion {
	private Atraccion[] atracciones;
	private double precioOriginal;
	private double precioConDescuento;
	private double duracion;
	private TipoDePromocion tipo;

	public Promocion(Atraccion[] atracciones, TipoDePromocion tipo) {
		this.atracciones = atracciones;
		// this.precioOriginal = precioOriginal;
		// this.precioConDescuento = precioConDescuento;
		// this.duracion = duracion;
		this.setTipo(tipo);
	}

	public Atraccion[] getAtracciones() {
		return atracciones;
	}

	public double getPrecioOriginal() {
		return precioOriginal;
	}

	public double getPrecioConDescuento() {
		return precioConDescuento;
	}

	public double getDuracion() {
		return duracion;
	}

	public double getPrecio() {
		return precioConDescuento;
	}

	public TipoDePromocion getTipo() {
		return tipo;
	}

	public void setTipo(TipoDePromocion tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Promocion [atracciones=" + Arrays.toString(atracciones) + ", precioOriginal=" + precioOriginal
				+ ", precioConDescuento=" + precioConDescuento + ", duracion=" + duracion + "]";
	}

}
