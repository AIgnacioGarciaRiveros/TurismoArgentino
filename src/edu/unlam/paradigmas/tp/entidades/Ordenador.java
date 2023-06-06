package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ordenador {

	public List<Promocion> ordenarPromociones(Usuario usuario, List<Promocion> promociones) {

		List<Promocion> promocionesPreferidas = new ArrayList<>();
		List<Promocion> promocionesNoPreferidas = new ArrayList<>();

		String preferencia = usuario.getAtraccionFavorita().toString();

		for (Promocion promocion : promociones) {
			if (promocion.getTipoDeAtraccion().toString().equals(preferencia)) {
				promocionesPreferidas.add(promocion);
			} else {
				promocionesNoPreferidas.add(promocion);
			}
		}

		Collections.sort(promocionesPreferidas, Collections.reverseOrder());
		Collections.sort(promocionesNoPreferidas, Collections.reverseOrder());

		promocionesPreferidas.addAll(promocionesNoPreferidas);

		return promocionesPreferidas;
	}

	public Map<String, Atraccion> ordenarAtraccionesPorPreferencia(Usuario usuario,
			Map<String, Atraccion> atracciones) {

		Map<String, Atraccion> atraccionesPreferidas = new HashMap<>();
		Map<String, Atraccion> atraccionesNoPreferidas = new HashMap<>();

		String preferencia = usuario.getAtraccionFavorita().toString();

		for (Map.Entry<String, Atraccion> entry : atracciones.entrySet()) {

			String nombreAtraccion = entry.getKey();
			Atraccion atraccion = entry.getValue();

			if (atraccion.getTipo().toString().equals(preferencia))
				atraccionesPreferidas.put(nombreAtraccion, atraccion);
			else
				atraccionesNoPreferidas.put(nombreAtraccion, atraccion);

		}
		Map<String, Atraccion> atraccionesOrdenadas = ordenarAtraccionesPorPrecioYTiempo(atraccionesPreferidas);
		atraccionesOrdenadas.putAll(ordenarAtraccionesPorPrecioYTiempo(atraccionesNoPreferidas));
		return atraccionesOrdenadas;
	}

	private Map<String, Atraccion> ordenarAtraccionesPorPrecioYTiempo(Map<String, Atraccion> atracciones) {
		List<Map.Entry<String, Atraccion>> listaAtracciones = new ArrayList<>(atracciones.entrySet());

		Comparator<Map.Entry<String, Atraccion>> comparador = Comparator
				.comparingDouble((Map.Entry<String, Atraccion> entry) -> entry.getValue().getPrecio())
				.thenComparingDouble(entry -> entry.getValue().getTiempo());

		listaAtracciones.sort(comparador.reversed());

		Map<String, Atraccion> atraccionesOrdenadas = new LinkedHashMap<>();
		for (Map.Entry<String, Atraccion> entry : listaAtracciones)
			atraccionesOrdenadas.put(entry.getKey(), entry.getValue());

		return atraccionesOrdenadas;
	}

}
