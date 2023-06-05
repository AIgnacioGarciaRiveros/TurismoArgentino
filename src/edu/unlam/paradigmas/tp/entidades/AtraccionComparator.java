package edu.unlam.paradigmas.tp.entidades;

import java.util.Comparator;

public class AtraccionComparator {

	static Comparator<Atraccion> compareByPrecio = new Comparator<Atraccion>() {
		@Override
		public int compare(Atraccion o1, Atraccion o2) {
			return o1.getPrecio() > o2.getPrecio() ? -1 : 1;
		}
	};

	static Comparator<Atraccion> compareByDuracion = new Comparator<Atraccion>() {
		@Override
		public int compare(Atraccion o1, Atraccion o2) {
			return o1.getTiempo() > o2.getTiempo() ? -1 : 1;
		}
	};

	static Comparator<Atraccion> compareByTipo = new Comparator<Atraccion>() {
		@Override
		public int compare(Atraccion o1, Atraccion o2) {

			return o1.getTipo().equals(o2.getTipo()) ? -1 : 1;
		}
	};
}
