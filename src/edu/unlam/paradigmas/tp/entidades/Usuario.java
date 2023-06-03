package edu.unlam.paradigmas.tp.entidades;

import java.util.Objects;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;

public class Usuario {

	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private TipoDeAtraccion atraccionFavorita;

	public Usuario(String nombre, double presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionFavorita) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionFavorita = atraccionFavorita;
	}

	public Usuario() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public TipoDeAtraccion getAtraccionFavorita() {
		return atraccionFavorita;
	}



	public void setAtraccionFavorita(TipoDeAtraccion atraccionFavorita) {
		this.atraccionFavorita = atraccionFavorita;
	}

	@Override
	public String toString() {
		return "Nombre: " + String.format("%-15s", nombre) + "\tPresupuesto: $" + String.format("%-10.2f", presupuesto)
				+ "\tTiempo Disponible: " + String.format("%-10.2f", tiempoDisponible) + "Atraccion Favorita: "
				+ atraccionFavorita;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(atraccionFavorita, nombre, presupuesto, tiempoDisponible);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return atraccionFavorita == other.atraccionFavorita && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(presupuesto) == Double.doubleToLongBits(other.presupuesto)
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible);
	}

}
