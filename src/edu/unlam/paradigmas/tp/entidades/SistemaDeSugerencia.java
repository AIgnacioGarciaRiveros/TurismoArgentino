package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaDeSugerencia {

	public void iniciarSistema() {

		Archivo archivo = new Archivo("UsuariosTest");
		List<Usuario> usuarios = new ArrayList<>();

		usuarios = archivo.leerArchivoUsuario();
		Archivo archivoAtraccion = new Archivo("Atracciones");
		Map<String, Atraccion> atracciones = new HashMap<>();
		atracciones = archivoAtraccion.leerArchivoAtraccion();
		Archivo archivoPromocion = new Archivo("Promociones");
		List<Promocion> promociones = new ArrayList<>();
		promociones = archivoPromocion.leerArchivoPromocion(atracciones);
		Ordenador ordenador = new Ordenador();
		Map<String, Atraccion> atraccionesOrdenada = ordenador.ordenarAtraccionesPorPreferencia(usuarios.get(0),
				atracciones);
		InterfazUsuario interfaz = new InterfazUsuario();
		for (Usuario usuario : usuarios) {
			interfaz.iniciarSistema(usuario.getNombre());
			interfaz.sugerirPromociones(promociones, atraccionesOrdenada, usuario);
			interfaz.sugerirAtracciones(atraccionesOrdenada, usuario);
		}
	}

}
