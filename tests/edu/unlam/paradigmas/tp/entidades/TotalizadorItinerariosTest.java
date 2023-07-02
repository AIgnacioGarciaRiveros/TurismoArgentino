package edu.unlam.paradigmas.tp.entidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class TotalizadorItinerariosTest {

	@Test
	public void queCalculaPrecioAtracciones() {
		Usuario usuario = new Usuario("Lucas", 22000, 20, TipoDeAtraccion.AVENTURA);
		Itinerario itinerario = new Itinerario(usuario);
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Safari", 1300, 3.5, 3, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Parapente", 5000, 2, 2, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("PescaDeportiva", 2000, 5.5, 3, TipoDeAtraccion.AVENTURA));
		itinerario.agregarAtraccion(atracciones.get(0));
		itinerario.agregarAtraccion(atracciones.get(1));
		itinerario.agregarAtraccion(atracciones.get(2));
		double precioTotalEsperado = 0;
		for (Atraccion atraccion : atracciones)
			precioTotalEsperado += atraccion.getPrecio();

		double precioTotalResultado = itinerario.obtenerPrecioAtracciones();

		Assert.assertEquals(precioTotalEsperado, precioTotalResultado, 0.01);
	}

	@Test
	public void queCalculaDuracionAtracciones() {
		Usuario usuario = new Usuario("Lucas", 22000, 20, TipoDeAtraccion.AVENTURA);
		Itinerario itinerario = new Itinerario(usuario);
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Safari", 1300, 3.5, 3, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("Parapente", 5000, 2, 2, TipoDeAtraccion.AVENTURA));
		atracciones.add(new Atraccion("PescaDeportiva", 2000, 5.5, 3, TipoDeAtraccion.AVENTURA));
		itinerario.agregarAtraccion(atracciones.get(0));
		itinerario.agregarAtraccion(atracciones.get(1));
		itinerario.agregarAtraccion(atracciones.get(2));
		double duracionTotalEsperado = 0;
		for (Atraccion atraccion : atracciones)
			duracionTotalEsperado += atraccion.getTiempo();

		double duracionTotalResultado = itinerario.obtenerDuracionAtracciones();

		Assert.assertEquals(duracionTotalEsperado, duracionTotalResultado, 0.01);
	}

	@Test
	public void queCalculaPrecioPromociones() {
		Usuario usuario = new Usuario("Lucas", 22000, 20, TipoDeAtraccion.AVENTURA);
		Itinerario itinerario = new Itinerario(usuario);
		List<Promocion> promociones = new ArrayList<Promocion>();
		Atraccion[] atracciones = { new Atraccion("Safari", 1300, 3.5, 3, TipoDeAtraccion.AVENTURA),
				new Atraccion("Parapente", 5000, 2, 2, TipoDeAtraccion.AVENTURA),
				new Atraccion("PescaDeportiva", 2000, 5.5, 3, TipoDeAtraccion.AVENTURA) };
		Promocion promocionPorcentual = new Porcentual(TipoDeAtraccion.AVENTURA, atracciones,
				TipoDePromocion.PORCENTUAL);
		promociones.add(promocionPorcentual);
		promociones.add(promocionPorcentual);
		itinerario.agregarPromocion(promocionPorcentual);
		itinerario.agregarPromocion(promocionPorcentual);

		double precioTotalEsperado = 0;
		for (Promocion promocion : promociones)
			precioTotalEsperado += promocion.getPrecioConDescuento();

		double precioTotalResultado = itinerario.obtenerPrecioPromociones();

		Assert.assertEquals(precioTotalEsperado, precioTotalResultado, 0.01);

	}

	@Test
	public void queCalculaDuracionPromociones() {
		Usuario usuario = new Usuario("Lucas", 22000, 20, TipoDeAtraccion.AVENTURA);
		Itinerario itinerario = new Itinerario(usuario);
		List<Promocion> promociones = new ArrayList<Promocion>();
		Atraccion[] atracciones = { new Atraccion("Safari", 1300, 3.5, 3, TipoDeAtraccion.AVENTURA),
				new Atraccion("Parapente", 5000, 2, 2, TipoDeAtraccion.AVENTURA),
				new Atraccion("PescaDeportiva", 2000, 5.5, 3, TipoDeAtraccion.AVENTURA) };
		Promocion promocionPorcentual = new Porcentual(TipoDeAtraccion.AVENTURA, atracciones,
				TipoDePromocion.PORCENTUAL);
		promociones.add(promocionPorcentual);
		promociones.add(promocionPorcentual);
		itinerario.agregarPromocion(promocionPorcentual);
		itinerario.agregarPromocion(promocionPorcentual);

		double precioTotalEsperado = 0;
		for (Promocion promocion : promociones)
			precioTotalEsperado += promocion.getDuracion();

		double precioTotalResultado = itinerario.obtenerDuracionPromociones();

		Assert.assertEquals(precioTotalEsperado, precioTotalResultado, 0.01);
	}

	@Test
	public void queCreeArchivoItinerario() {
		Usuario usuario = new Usuario("Makelele", 4500, 5, TipoDeAtraccion.AVENTURA);
		Itinerario itinerario = new Itinerario(usuario);
		Atraccion[] atracciones = { new Atraccion("Safari", 1300, 3.5, 3, TipoDeAtraccion.AVENTURA),
				new Atraccion("Parapente", 5000, 2, 2, TipoDeAtraccion.AVENTURA),
				new Atraccion("PescaDeportiva", 2000, 5.5, 3, TipoDeAtraccion.AVENTURA) };
		Promocion promocion = new Porcentual(TipoDeAtraccion.AVENTURA, atracciones, TipoDePromocion.PORCENTUAL);
		itinerario.agregarPromocion(promocion);
		List<Itinerario> itinerarios = new ArrayList<>();
		itinerarios.add(itinerario);
		Archivo archivo = new Archivo("itinerarioGeneradoTest");
		archivo.crearArchivoItinerario(itinerarios, Archivo.RUTA_ARCHIVOS_TESTS_SALIDA);
		try {
			byte[] archivoGeneradoTest = Files
					.readAllBytes(Paths.get(Archivo.RUTA_ARCHIVOS_TESTS_SALIDA + "itinerarioGeneradoTest.out"));
			byte[] archivoTest = Files
					.readAllBytes(Paths.get(Archivo.RUTA_ARCHIVOS_TESTS_SALIDA + "itinerarioTest.out"));
			Assert.assertArrayEquals(archivoTest, archivoGeneradoTest);
		} catch (IOException e) {
			Assert.fail();
		}
	}
}
