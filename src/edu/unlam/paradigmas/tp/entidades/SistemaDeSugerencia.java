package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaDeSugerencia {

	public void iniciarSistema() {

		Archivo archivo = new Archivo("Usuarios");
		List<Usuario> usuarios = new ArrayList<>();

		usuarios = archivo.leerArchivoUsuario();

		System.out.println("============ ARCHIVO USUARIOS ============");
		for (Usuario usuario : usuarios)
			System.out.println(usuario);
		System.out.println("====================================");

		Archivo archivoAtraccion = new Archivo("Atracciones");
		Map<String, Atraccion> atracciones = new HashMap<>();

		atracciones = archivoAtraccion.leerArchivoAtraccion();

		System.out.println("============ ARCHIVO ATRACCIONES ============");
		for (Map.Entry<String, Atraccion> entry : atracciones.entrySet())
			System.out.println(entry.getValue());
		System.out.println("====================================");
		Archivo archivoPromocion = new Archivo("Promociones");
		List<Promocion> promociones = new ArrayList<>();
		promociones = archivoPromocion.leerArchivoPromocion(atracciones);
		System.out.println("============ ARCHIVO PROMOCION ============");
		for (Promocion promocion : promociones) {
			System.out.println(promocion);
		}
		System.out.println("====================================");

		List<Atraccion> atracciones2 = ordenarAtraccionesPorPreferencia(usuarios.get(0), atracciones);
		for (Atraccion atraccion : atracciones2) {
			System.out.println(atraccion);
		}
	}

	public List<Atraccion> ordenarAtraccionesPorPreferencia(Usuario usuario, Map<String, Atraccion> atracciones) {
		List<Atraccion> atraccionesOrdenadas = new ArrayList<>();
		List<Atraccion> atraccionesNoPreferidas = new ArrayList<>();

		String preferencia = usuario.getAtraccionFavorita().toString();

		for (Map.Entry<String, Atraccion> entry : atracciones.entrySet()) {
			Atraccion atraccion = entry.getValue();
			if (atraccion.getTipo().toString().equals(preferencia)) {
				atraccionesOrdenadas.add(atraccion);
			} else {
				atraccionesNoPreferidas.add(atraccion);
			}
		}
		List<Atraccion> ordenadosPorPrecio = ordenarPorPrecio(atraccionesOrdenadas);
		ordenadosPorPrecio.addAll(ordenarPorPrecio(atraccionesNoPreferidas));
		return ordenadosPorPrecio;
	}

	private List<Atraccion> ordenarPorPrecio(List<Atraccion> atracciones) {
		Comparator<Atraccion> comparador = Comparator.comparingDouble(Atraccion::getPrecio)
				.thenComparingDouble(Atraccion::getTiempo);
		Collections.sort(atracciones, comparador.reversed());
		return atracciones;
	}

}
