package edu.unlam.paradigmas.tp.entidades;

import java.util.Arrays;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public abstract class Promocion {

	private TipoDeAtraccion tipoDeAtraccion;
	private Atraccion[] atracciones;
	private TipoDePromocion tipoDePromocion;

	private double precioOriginal;
	private double precioConDescuento;
	private double duracion;

	public Promocion(TipoDeAtraccion tipoDeAtraccion, Atraccion[] atracciones, TipoDePromocion tipoDePromocion) {

		this.tipoDeAtraccion = tipoDeAtraccion;
		this.atracciones = atracciones;
		this.tipoDePromocion = tipoDePromocion;

		this.precioOriginal = calcularPrecioOriginalTotal(); // PREGUNTAR
		this.precioConDescuento = calcularPrecioConDescuento(); // PREGUNTAR
		this.duracion = calcularDuracionTotal(); // PREGUNTAR
	}

	protected double getPrecioOriginal() {
		return this.precioOriginal;
	}

	protected abstract double calcularPrecioConDescuento();

	protected double calcularPrecioOriginalTotal() {
		double precioOriginalTotal = 0;
		for (Atraccion atraccion : atracciones)
			precioOriginalTotal += atraccion.getPrecio();
		return precioOriginalTotal;
	}

	private double calcularDuracionTotal() {
		double duracionTotal = 0;
		for (Atraccion atraccion : atracciones)
			duracionTotal += atraccion.getTiempo();
		return duracionTotal;
	}

	public Atraccion[] getAtracciones() {
		return atracciones;
	}

	@Override
	public String toString() {
		return "Tipo de atraccion: " + tipoDeAtraccion + "\t\tAtracciones: " + Arrays.toString(atracciones)
				+ "\t\tTipo de promocion: " + tipoDePromocion + "\tPrecio orignal: " + precioOriginal
				+ "\tPrecio con descuento: " + precioConDescuento + "\tDuracion: " + duracion;
	}

}
