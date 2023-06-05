package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ordenador {
	
	public Map<String,Atraccion> ordenarAtraccionesPorPreferencia(Usuario usuario, Map<String, Atraccion> atracciones) {
		Map<String,Atraccion> atraccionesOrdenadas = new HashMap<>();
		Map<String,Atraccion> atraccionesNoPreferidas = new HashMap<>();

		String preferencia = usuario.getAtraccionFavorita().toString();

		for (Map.Entry<String, Atraccion> entry : atracciones.entrySet()) {
			String nombreAtraccion= entry.getKey();
			Atraccion atraccion = entry.getValue();
			if (atraccion.getTipo().toString().equals(preferencia)) {
				atraccionesOrdenadas.put(nombreAtraccion,atraccion);
			} else {
				atraccionesNoPreferidas.put(nombreAtraccion,atraccion);
			}
		}
		Map<String,Atraccion> ordenadosPorPrecio = ordenarPorPrecio(atraccionesOrdenadas);
		ordenadosPorPrecio.putAll(ordenarPorPrecio(atraccionesNoPreferidas));
		return ordenadosPorPrecio;
	}
	private Map<String, Atraccion> ordenarPorPrecio(Map<String, Atraccion> atracciones) {
	    List<Map.Entry<String, Atraccion>> listaAtracciones = new ArrayList<>(atracciones.entrySet());

	    Comparator<Map.Entry<String, Atraccion>> comparador = Comparator
	            .comparingDouble((Map.Entry<String, Atraccion> entry) -> entry.getValue().getPrecio())
	            .thenComparingDouble(entry -> entry.getValue().getTiempo());

	    listaAtracciones.sort(comparador.reversed());

	    Map<String, Atraccion> atraccionesOrdenadas = new LinkedHashMap<>();
	    for (Map.Entry<String, Atraccion> entry : listaAtracciones) {
	        atraccionesOrdenadas.put(entry.getKey(), entry.getValue());
	    }

	    return atraccionesOrdenadas;
	}
}
