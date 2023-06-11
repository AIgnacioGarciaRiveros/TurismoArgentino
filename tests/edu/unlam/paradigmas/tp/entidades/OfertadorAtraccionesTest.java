package edu.unlam.paradigmas.tp.entidades;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;

public class OfertadorAtraccionesTest {

	@Test
	public void queDevuelveAtraccion() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("CataratasDelIguazu", 10000, 4, 10, TipoDeAtraccion.PAISAJE);
		atracciones.put(atraccion.getNombre(), atraccion);
		Usuario usuario = new Usuario("Maria", 18500, 9, TipoDeAtraccion.PAISAJE);
		AtraccionIteratorImpl it = new AtraccionIteratorImpl(atracciones, usuario);
		Atraccion atraccionObtenida = it.next();

		Assert.assertEquals(atraccion, atraccionObtenida);
	}

	@Test
	public void queActualizaDisponibilidadAtraccionParaClienteNuevo() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("CataratasDelIguazu", 10000, 4, 10, TipoDeAtraccion.PAISAJE);
		atraccion.setDisponibilidad(false);
		atracciones.put(atraccion.getNombre(), atraccion);
		boolean resultadoEsperado = true;
		SistemaDeSugerencia sistema = new SistemaDeSugerencia();
		sistema.actualizarDisponibilidadAtraccion(atracciones);
		boolean resultadoObtenido = atracciones.get(atraccion.getNombre()).getDisponibilidad();

		Assert.assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void queActualizaTiempoDelUsuario() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("CataratasDelIguazu", 10000, 4, 10, TipoDeAtraccion.PAISAJE);
		atracciones.put(atraccion.getNombre(), atraccion);
		Usuario usuario = new Usuario("Maria", 18500, 9, TipoDeAtraccion.PAISAJE);

		double resultadoEsperado = usuario.getTiempoDisponible() - atraccion.getTiempo();
		SistemaDeSugerencia sistema = new SistemaDeSugerencia();
		sistema.procesarCompraAtraccion(atraccion, usuario, new Itinerario(usuario));
		double resultadoObtenido = usuario.getTiempoDisponible();

		Assert.assertEquals(resultadoEsperado, resultadoObtenido, 0.001);
	}

	@Test
	public void queActualizaDineroDelUsuario() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("CataratasDelIguazu", 10000, 4, 10, TipoDeAtraccion.PAISAJE);
		atracciones.put(atraccion.getNombre(), atraccion);
		Usuario usuario = new Usuario("Maria", 18500, 9, TipoDeAtraccion.PAISAJE);

		double resultadoEsperado = usuario.getPresupuesto() - atraccion.getPrecio();
		SistemaDeSugerencia sistema = new SistemaDeSugerencia();
		sistema.procesarCompraAtraccion(atraccion, usuario, new Itinerario(usuario));
		double resultadoObtenido = usuario.getPresupuesto();

		Assert.assertEquals(resultadoEsperado, resultadoObtenido, 0.001);
	}

	@Test
	public void queActualizaCupoDiarioAtraccion() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("CataratasDelIguazu", 10000, 4, 10, TipoDeAtraccion.PAISAJE);
		atracciones.put(atraccion.getNombre(), atraccion);
		Usuario usuario = new Usuario("Maria", 18500, 9, TipoDeAtraccion.PAISAJE);
		int resultadoEsperado = atraccion.getCupoDiario() - 1;
		SistemaDeSugerencia sistema = new SistemaDeSugerencia();
		sistema.procesarCompraAtraccion(atraccion, usuario, new Itinerario(usuario));
		int resultadoObtenido = atracciones.get(atraccion.getNombre()).getCupoDiario();

		Assert.assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void queNoHayCupoDiario() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("Safari", 1300, 3.5, 0, TipoDeAtraccion.AVENTURA);
		atracciones.put(atraccion.getNombre(), atraccion);
		Usuario usuario = new Usuario("Lucas", 12000, 12, TipoDeAtraccion.AVENTURA);
		AtraccionIteratorImpl it = new AtraccionIteratorImpl(atracciones, usuario);

		Atraccion atraccionObtenida = it.next();

		Assert.assertNotEquals(atraccion, atraccionObtenida);
	}

	@Test
	public void queNoTieneTiempoSuficiente() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("Safari", 1300, 3.5, 0, TipoDeAtraccion.PAISAJE);
		atracciones.put(atraccion.getNombre(), atraccion);
		Usuario usuario = new Usuario("Juan", 11000, 3, TipoDeAtraccion.PAISAJE);
		AtraccionIteratorImpl it = new AtraccionIteratorImpl(atracciones, usuario);

		Atraccion atraccionObtenida = it.next();

		Assert.assertNotEquals(atraccion, atraccionObtenida);
	}

	@Test
	public void queNoTieneDineroSuficiente() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("CataratasDelIguazu", 10000, 4, 10, TipoDeAtraccion.PAISAJE);
		atracciones.put(atraccion.getNombre(), atraccion);
		Usuario usuario = new Usuario("Antonio", 4000, 10, TipoDeAtraccion.PAISAJE);
		AtraccionIteratorImpl it = new AtraccionIteratorImpl(atracciones, usuario);

		Atraccion atraccionObtenida = it.next();

		Assert.assertNotEquals(atraccion, atraccionObtenida);
	}

	@Test
	public void queYaFueComprada() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("CataratasDelIguazu", 10000, 4, 10, TipoDeAtraccion.PAISAJE);
		atraccion.setDisponibilidad(false);
		atracciones.put(atraccion.getNombre(), atraccion);
		Usuario usuario = new Usuario("Maria", 18500, 9, TipoDeAtraccion.PAISAJE);
		AtraccionIteratorImpl it = new AtraccionIteratorImpl(atracciones, usuario);

		Atraccion atraccionObtenida = it.next();

		Assert.assertNotEquals(atraccion, atraccionObtenida);
	}

	@Test
	public void queNoHayAtracciones() {
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Usuario usuario = new Usuario("Maria", 18500, 9, TipoDeAtraccion.PAISAJE);
		boolean resultadoEsperado = false;
		AtraccionIteratorImpl it = new AtraccionIteratorImpl(atracciones, usuario);
		boolean resultadoObtenido = it.hasNext();

		Assert.assertEquals(resultadoEsperado, resultadoObtenido);
	}

}
