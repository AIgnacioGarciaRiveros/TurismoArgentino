package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaDeSugerencia {

	public void iniciarSistema() {

		Archivo archivoUsuario = new Archivo("UsuariosTest");
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = archivoUsuario.leerArchivoUsuario();

		Archivo archivoAtraccion = new Archivo("Atracciones");
		Map<String, Atraccion> atracciones = new HashMap<>();
		atracciones = archivoAtraccion.leerArchivoAtraccion();

		Archivo archivoPromocion = new Archivo("Promociones");
		List<Promocion> promociones = new ArrayList<>();
		promociones = archivoPromocion.leerArchivoPromocion(atracciones);

		Ordenador ordenador = new Ordenador();
		InterfazUsuario interfaz = new InterfazUsuario();

		for (Usuario usuario : usuarios) {
			List<Promocion> promocionesOrdenadas = ordenador.ordenarPromociones(usuario, promociones);
			Map<String, Atraccion> atraccionesOrdenadas = ordenador.ordenarAtraccionesPorPreferencia(usuario,
					atracciones);

			interfaz.iniciarSistema(usuario.getNombre());

			// 1- Promociones preferidas
			// 2- Promociones no preferidas
			// 3 - Atracciones preferidas
			// 4 - Atracciones no preferidas

			interfaz.sugerirPromociones(promocionesOrdenadas, atraccionesOrdenadas, usuario);
			interfaz.sugerirAtracciones(atraccionesOrdenadas, usuario);
			interfaz.resetearEstaDisponible(atraccionesOrdenadas);
		}

	}

}
