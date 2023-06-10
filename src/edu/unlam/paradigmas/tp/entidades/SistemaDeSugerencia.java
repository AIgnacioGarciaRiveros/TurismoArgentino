package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaDeSugerencia {

	public void iniciar() {

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

		Itinerario itinerario;
		List<Itinerario> itinerarios = new ArrayList<>();

		for (Usuario usuario : usuarios) {
			List<Promocion> promocionesOrdenadasPref = ordenador.ordenarPromociones(usuario, promociones, 1);
			List<Promocion> promocionesOrdenadasNoPref = ordenador.ordenarPromociones(usuario, promociones, 2);
			Map<String, Atraccion> atraccionesOrdenadasPref = ordenador.ordenarAtraccionesPorPreferencia(usuario,atracciones, 1);
			Map<String, Atraccion> atraccionesOrdenadasNoPref = ordenador.ordenarAtraccionesPorPreferencia(usuario,atracciones, 2);

			itinerario = new Itinerario(usuario);
			interfaz.saludarUsuario(usuario.getNombre());
			interfaz.sugerirPromociones(promocionesOrdenadasPref, atraccionesOrdenadasPref, usuario, itinerario);
			interfaz.sugerirAtracciones(atraccionesOrdenadasPref, usuario, itinerario);
			interfaz.sugerirPromociones(promocionesOrdenadasNoPref, atraccionesOrdenadasNoPref, usuario, itinerario);
			interfaz.sugerirAtracciones(atraccionesOrdenadasNoPref, usuario, itinerario);

			actualizarDisponibilidadAtraccion(atraccionesOrdenadasPref);
			actualizarDisponibilidadAtraccion(atraccionesOrdenadasNoPref);

			itinerarios.add(itinerario);

			System.out.println("Resultado de tu compra\n");
			itinerario.mostrar();
		}

		Archivo archivoItinerario = new Archivo("Itinerario");
		archivoItinerario.crearArchivoItinerario(itinerarios);

	}

	public void procesarCompraAtraccion(Atraccion atraccion, Usuario usuario, Itinerario itinerario) {
		usuario.setPresupuesto(usuario.getPresupuesto() - atraccion.getPrecio());
		usuario.setTiempoDisponible(usuario.getTiempoDisponible() - atraccion.getTiempo());
		itinerario.agregarAtraccion(atraccion);
		atraccion.setDisponibilidad(false);
		atraccion.setCupoDiario(atraccion.getCupoDiario() - 1);
	}

	public void procesarCompraPromocion(Usuario usuario, Promocion promocion, Map<String, Atraccion> atracciones,
			Itinerario itinerario) {
		usuario.setPresupuesto(usuario.getPresupuesto() - promocion.getPrecioConDescuento());
		usuario.setTiempoDisponible(usuario.getTiempoDisponible() - promocion.getDuracion());
		itinerario.agregarPromocion(promocion);
		Atraccion[] atraccionesOfertadas = promocion.getAtracciones();
		for (int i = 0; i < atraccionesOfertadas.length; i++) {
			Atraccion atraccionEncontrada = atracciones.get(atraccionesOfertadas[i].getNombre());
			atraccionEncontrada.setDisponibilidad(false);
			atraccionEncontrada.setCupoDiario(atraccionEncontrada.getCupoDiario() - 1);
		}
	}

	public void actualizarDisponibilidadAtraccion(Map<String, Atraccion> atracciones) {
		for (Map.Entry<String, Atraccion> entry : atracciones.entrySet()) {
			Atraccion atraccion = entry.getValue();
			if (atraccion.getCupoDiario() != 0 && atraccion.getDisponibilidad() == false)
				atraccion.setDisponibilidad(true);
		}
	}

}
