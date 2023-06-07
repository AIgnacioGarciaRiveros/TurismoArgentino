package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ordenador {

	public List<Promocion> ordenarPromociones(Usuario usuario, List<Promocion> promociones, int opcion) {

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
		if (opcion == 1)
			return promocionesPreferidas;
		else
			return promocionesNoPreferidas;
	}

	public Map<String, Atraccion> ordenarAtraccionesPorPreferencia(Usuario usuario, Map<String, Atraccion> atracciones,
			int opcion) {

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
		Map<String, Atraccion> atraccionesOrdenadas;
		if (opcion == 1) {
			atraccionesOrdenadas = ordenarAtraccionesPorPrecioYTiempo(atraccionesPreferidas);
		} else {
			atraccionesOrdenadas = ordenarAtraccionesPorPrecioYTiempo(atraccionesNoPreferidas);
		}

		return atraccionesOrdenadas;
	}

	private Map<String, Atraccion> ordenarAtraccionesPorPrecioYTiempo(Map<String, Atraccion> atracciones) {
		List<Map.Entry<String, Atraccion>> listaAtracciones = new ArrayList<>(atracciones.entrySet());

		Comparator<Map.Entry<String, Atraccion>> comparador = new Comparator<Map.Entry<String, Atraccion>>() {
			@Override
			public int compare(Map.Entry<String, Atraccion> entry1, Map.Entry<String, Atraccion> entry2) {
				double precio1 = entry1.getValue().getPrecio();
				double precio2 = entry2.getValue().getPrecio();
				int resultadoPrecio = Double.compare(precio1, precio2);

				if (resultadoPrecio != 0) {
					return resultadoPrecio;
				} else {
					double tiempo1 = entry1.getValue().getTiempo();
					double tiempo2 = entry2.getValue().getTiempo();
					return Double.compare(tiempo1, tiempo2);
				}
			}
		};

		Collections.sort(listaAtracciones, Collections.reverseOrder(comparador));

		Map<String, Atraccion> atraccionesOrdenadas = new LinkedHashMap<>();
		for (Map.Entry<String, Atraccion> entry : listaAtracciones) {
			atraccionesOrdenadas.put(entry.getKey(), entry.getValue());
		}

		return atraccionesOrdenadas;
	}

}
