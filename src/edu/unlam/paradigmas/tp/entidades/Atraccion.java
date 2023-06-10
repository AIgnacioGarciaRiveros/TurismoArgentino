package edu.unlam.paradigmas.tp.entidades;

import java.util.Objects;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;

public class Atraccion {

	private String nombre;
	private double precio;
	private double tiempo;
	private int cupoDiario;
	private TipoDeAtraccion tipo;
	private boolean disponibilidad;

	public Atraccion() {
	}

	public Atraccion(String nombre, double precio, double tiempo, int cupoDiario, TipoDeAtraccion tipo) {
		this.nombre = nombre;
		this.precio = precio;
		this.tiempo = tiempo;
		this.cupoDiario = cupoDiario;
		this.tipo = tipo;
		this.disponibilidad = true;
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

	public boolean getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public static String formatearTipoAtraccion(TipoDeAtraccion tipoDeAtraccion) {
		String tipoDeAtraccionFormateada = tipoDeAtraccion.toString();
		String primerCaracter = tipoDeAtraccionFormateada.charAt(0) + "";
		String restoDelString = tipoDeAtraccionFormateada.substring(1).toLowerCase();
		return primerCaracter.toUpperCase() + restoDelString;
	}

	@Override
	public String toString() {
		return "Atraccion \n" + String.format("%-11s", "-Tipo:") + formatearTipoAtraccion(tipo) + "\n-Nombre:  "
				+ nombre.replaceAll("(?=[A-Z])", " ") + "\n-Precio:   $" + precio + "\n-Duracion: " + tiempo
				+ " horas\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cupoDiario, nombre, precio, tiempo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return cupoDiario == other.cupoDiario && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo) && tipo == other.tipo;
	}

}
