package edu.unlam.paradigmas.tp.entidades;

import java.util.Arrays;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public abstract class Promocion {
	
	private TipoDeAtraccion tipoDeAtraccion;
	private Atraccion[] atracciones;
	private TipoDePromocion tipoDePromocion;
	
	//private double precioOriginal;
	//private double precioConDescuento;
	//private double duracion;

	public Promocion(TipoDeAtraccion tipoDeAtraccion, Atraccion[] atracciones, TipoDePromocion tipoDePromocion) {
		
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.atracciones = atracciones;
		this.tipoDePromocion = tipoDePromocion;
		
		// this.precioOriginal = precioOriginal;
		// this.precioConDescuento = precioConDescuento;
		// this.duracion = duracion;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public Atraccion[] getAtracciones() {
		return atracciones;
	}

	public TipoDePromocion getTipoDePromocion() {
		return tipoDePromocion;
	}

	public void setTipoDeAtraccion(TipoDeAtraccion tipoDeAtraccion) {
		this.tipoDeAtraccion = tipoDeAtraccion;
	}

	public void setAtracciones(Atraccion[] atracciones) {
		this.atracciones = atracciones;
	}

	@Override
	public String toString() {
		return "Promocion [tipoDeAtraccion=" + tipoDeAtraccion + ", atracciones=" + Arrays.toString(atracciones)
				+ ", tipoDePromocion=" + tipoDePromocion + "]\n";
	}

	public void setTipoDePromocion(TipoDePromocion tipoDePromocion) {
		this.tipoDePromocion = tipoDePromocion;
	}



}
