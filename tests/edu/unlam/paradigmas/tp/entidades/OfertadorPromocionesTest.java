package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class OfertadorPromocionesTest {

	@Test(expected = NoSuchElementException.class)
	public void queNoHayCupoDiario() {
		Atraccion[] atraccionesPromocion = { new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };
		Promocion promocion = new Absoluta(TipoDeAtraccion.AVENTURA, atraccionesPromocion, TipoDePromocion.ABSOLUTA);
		List<Promocion> promociones = new ArrayList<>();
		promociones.add(promocion);
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("Trekking", 500, 3, 0, TipoDeAtraccion.AVENTURA);
		atraccion.setDisponibilidad(false);
		atracciones.put(atraccion.getNombre(), atraccion);
		atracciones.put("Rafting", new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA));
		atracciones.put("Senderismo", new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA));
		Usuario usuario = new Usuario("Maria", 18500, 1, TipoDeAtraccion.AVENTURA);
		PromocionIteratorImpl it = new PromocionIteratorImpl(promociones, usuario, atracciones);

		Promocion promocionObtenida = it.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void queNoTieneTiempoSuficiente() {
		Atraccion[] atraccionesPromocion = { new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };
		Promocion promocion = new Absoluta(TipoDeAtraccion.AVENTURA, atraccionesPromocion, TipoDePromocion.ABSOLUTA);
		List<Promocion> promociones = new ArrayList<>();
		promociones.add(promocion);
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA);
		atracciones.put(atraccion.getNombre(), atraccion);
		atracciones.put("Rafting", new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA));
		atracciones.put("Senderismo", new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA));
		Usuario usuario = new Usuario("Maria", 18500, 1, TipoDeAtraccion.AVENTURA);
		PromocionIteratorImpl it = new PromocionIteratorImpl(promociones, usuario, atracciones);

		Promocion promocionObtenida = it.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void queNoTieneDineroSuficiente() {
		Atraccion[] atraccionesPromocion = { new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };
		Promocion promocion = new Absoluta(TipoDeAtraccion.AVENTURA, atraccionesPromocion, TipoDePromocion.ABSOLUTA);
		List<Promocion> promociones = new ArrayList<>();
		promociones.add(promocion);
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA);
		atracciones.put(atraccion.getNombre(), atraccion);
		atracciones.put("Rafting", new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA));
		atracciones.put("Senderismo", new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA));
		Usuario usuario = new Usuario("Maria", 100, 15, TipoDeAtraccion.AVENTURA);
		PromocionIteratorImpl it = new PromocionIteratorImpl(promociones, usuario, atracciones);

		Promocion promocionObtenida = it.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void queYaFueComprada() {
		Atraccion[] atraccionesPromocion = { new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };
		Promocion promocion = new Absoluta(TipoDeAtraccion.AVENTURA, atraccionesPromocion, TipoDePromocion.ABSOLUTA);
		List<Promocion> promociones = new ArrayList<>();
		promociones.add(promocion);
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Atraccion atraccion = new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA);
		atraccion.setDisponibilidad(false);
		atracciones.put(atraccion.getNombre(), atraccion);
		atracciones.put("Rafting", new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA));
		atracciones.put("Senderismo", new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA));
		Usuario usuario = new Usuario("Maria", 18500, 9, TipoDeAtraccion.AVENTURA);
		PromocionIteratorImpl it = new PromocionIteratorImpl(promociones, usuario, atracciones);

		Promocion promocionObtenida = it.next();

	}

	@Test
	public void queNoHayPromociones() {
		List<Promocion> promociones = new ArrayList<Promocion>();
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		Usuario usuario = new Usuario("Maria", 18500, 9, TipoDeAtraccion.PAISAJE);
		boolean resultadoEsperado = false;

		PromocionIteratorImpl it = new PromocionIteratorImpl(promociones, usuario, atracciones);
		boolean resultadoObtenido = it.hasNext();

		Assert.assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void queCalculaPrecioOriginal() {
		Atraccion[] atracciones = { new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };
		Promocion promocion = new Absoluta(TipoDeAtraccion.AVENTURA, atracciones, TipoDePromocion.ABSOLUTA);
		double resultadoEsperado = 0;

		for (int i = 0; i < atracciones.length; i++)
			resultadoEsperado += promocion.getAtracciones()[i].getPrecio();

		double resultadoObtenido = promocion.getPrecioOriginal();
		Assert.assertEquals(resultadoObtenido, resultadoEsperado, 0.0001);

	}

	@Test
	public void queCalculaPrecioConDescuento() {
		Atraccion[] atracciones = { new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };
		Promocion promocion = new Absoluta(TipoDeAtraccion.AVENTURA, atracciones, TipoDePromocion.ABSOLUTA);
		double resultadoEsperado = 0;
		for (int i = 0; i < atracciones.length; i++)
			resultadoEsperado += promocion.getAtracciones()[i].getPrecio();

		resultadoEsperado -= Absoluta.getPrecioFijo();

		double resultadoObtenido = promocion.getPrecioConDescuento();
		Assert.assertEquals(resultadoObtenido, resultadoEsperado, 0.0001);

	}

	@Test
	public void queCalculaDuracion() {
		Atraccion[] atracciones = { new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };
		Promocion promocion = new Absoluta(TipoDeAtraccion.AVENTURA, atracciones, TipoDePromocion.ABSOLUTA);
		double resultadoEsperado = 0;

		for (int i = 0; i < atracciones.length; i++)
			resultadoEsperado += promocion.getAtracciones()[i].getTiempo();

		double resultadoObtenido = promocion.getDuracion();

		Assert.assertEquals(resultadoObtenido, resultadoEsperado, 0.0001);

	}

}
