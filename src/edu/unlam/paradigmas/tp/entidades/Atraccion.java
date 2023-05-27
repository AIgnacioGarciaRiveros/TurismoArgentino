package edu.unlam.paradigmas.tp.entidades;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;

public class Atraccion {
	private String nombre;
	private double precio;
	private double tiempo;
	private int cupoDiario;
	private TipoDeAtraccion tipo;

	public Atraccion(String nombre, double precio, double tiempo, int cupoDiario, TipoDeAtraccion tipo) {
		this.nombre = nombre;
		this.precio = precio;
		this.tiempo = tiempo;
		this.cupoDiario = cupoDiario;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public int getCupoDiario() {
		return cupoDiario;
	}

	public void setCupoDiario(int cupoDiario) {
		this.cupoDiario = cupoDiario;
	}

	public TipoDeAtraccion getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeAtraccion tipo) {
		this.tipo = tipo;
	}

}
