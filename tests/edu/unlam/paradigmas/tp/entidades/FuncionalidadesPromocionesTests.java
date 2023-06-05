package edu.unlam.paradigmas.tp.entidades;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class FuncionalidadesPromocionesTests {

	@Test
	public void usuarios() {
		Archivo archivoUsuarios = new Archivo("UsuariosTest");
		List<Usuario> resultadoObtenido = archivoUsuarios.leerArchivoUsuario();

		List<Usuario> resultadoEsperado = new ArrayList<>();
		resultadoEsperado.add(new Usuario("Lucas", 12000, 12, TipoDeAtraccion.AVENTURA));
		resultadoEsperado.add(new Usuario("Leandro", 20000, 4, TipoDeAtraccion.DEGUSTACION));
		resultadoEsperado.add(new Usuario("Juan", 11000, 3, TipoDeAtraccion.PAISAJE));

		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void atracciones() {
		Archivo archivoAtracciones = new Archivo("AtraccionesTest");
		Map<String, Atraccion> resultadoObtenido = archivoAtracciones.leerArchivoAtraccion();

		Map<String, Atraccion> resultadoEsperado = new HashMap<>();
		resultadoEsperado.put("Trekking", new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA));
		resultadoEsperado.put("BodegasChandon",
				new Atraccion("BodegasChandon", 4100, 3, 20, TipoDeAtraccion.DEGUSTACION));
		resultadoEsperado.put("LosCardones", new Atraccion("LosCardones", 3267, 5, 2, TipoDeAtraccion.PAISAJE));

		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void promociones() {

		Map<String, Atraccion> mapaAtracciones = new HashMap<>();
		mapaAtracciones.put("Trekking", new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA));
		mapaAtracciones.put("Rafting", new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA));
		mapaAtracciones.put("Senderismo", new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA));

		Atraccion[] atracciones = { new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };

		Archivo archivoPromociones = new Archivo("PromocionesTest");
		List<Promocion> resultadoObtenido = archivoPromociones.leerArchivoPromocion(mapaAtracciones);

		List<Promocion> resultadoEsperado = new ArrayList<>();
		resultadoEsperado.add(new Absoluta(TipoDeAtraccion.AVENTURA, atracciones, TipoDePromocion.ABSOLUTA));

		assertEquals(resultadoEsperado, resultadoObtenido);
	}
}
