package edu.unlam.paradigmas.tp.entidades;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.unlam.paradigmas.tp.enums.TipoDeAtraccion;
import edu.unlam.paradigmas.tp.enums.TipoDePromocion;

public class FuncionalidadesPaquetesTest {

	private static Atraccion[] atraccionesPreferidas;
	private static Atraccion[] segundoLoteAtraccionesPreferidas;
	private static Atraccion[] tercerLoteAtraccionesPreferidas;
	private static Atraccion[] atraccionesNoPreferidas;

	@BeforeClass
	public static void setup() {
		atraccionesPreferidas = new Atraccion[] { new Atraccion("Safari", 1300, 3.5, 3, TipoDeAtraccion.AVENTURA),
				new Atraccion("Parapente", 5000, 2, 2, TipoDeAtraccion.AVENTURA),
				new Atraccion("PescaDeportiva", 2000, 5.5, 3, TipoDeAtraccion.AVENTURA) };

		segundoLoteAtraccionesPreferidas = new Atraccion[] {
				new Atraccion("Trekking", 1300, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 1300, 1, 2, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };

		tercerLoteAtraccionesPreferidas = new Atraccion[] {
				new Atraccion("Espeleologia", 1300, 5.5, 3, TipoDeAtraccion.AVENTURA),
				new Atraccion("BuceoMarino", 1300, 4.5, 4, TipoDeAtraccion.AVENTURA),
				new Atraccion("ParqueAereo", 1300, 2.5, 8, TipoDeAtraccion.AVENTURA) };

		atraccionesNoPreferidas = new Atraccion[] {
				new Atraccion("CerroCatedral", 5000, 3.5, 20, TipoDeAtraccion.PAISAJE),
				new Atraccion("LosCardones", 3267, 5, 2, TipoDeAtraccion.PAISAJE),
				new Atraccion("RutaSieteLagos", 1450, 6, 4, TipoDeAtraccion.PAISAJE) };
	}

	@Test
	public void queLeeArchivoUsuarios() {
		Archivo archivoUsuarios = new Archivo("UsuariosTest");
		List<Usuario> resultadoObtenido = archivoUsuarios.leerArchivoUsuario(Archivo.RUTA_ARCHIVOS_TESTS_ENTRADA);

		List<Usuario> resultadoEsperado = new ArrayList<>();
		resultadoEsperado.add(new Usuario("Lucas", 22000, 20, TipoDeAtraccion.AVENTURA));
		resultadoEsperado.add(new Usuario("Leandro", 13500, 10, TipoDeAtraccion.DEGUSTACION));
		resultadoEsperado.add(new Usuario("Juan", 11000, 3, TipoDeAtraccion.PAISAJE));

		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void queLeeArchivoAtracciones() {
		Archivo archivoAtracciones = new Archivo("AtraccionesTest");
		Map<String, Atraccion> resultadoObtenido = archivoAtracciones.leerArchivoAtraccion(Archivo.RUTA_ARCHIVOS_TESTS_ENTRADA);
		
		Map<String, Atraccion> resultadoEsperado = new HashMap<>();
		resultadoEsperado.put("Trekking", new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA));
		resultadoEsperado.put("BodegasChandon",new Atraccion("BodegasChandon", 4100, 3, 20, TipoDeAtraccion.DEGUSTACION));
		resultadoEsperado.put("LosCardones", new Atraccion("LosCardones", 3267, 5, 2, TipoDeAtraccion.PAISAJE));

		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void queLeeArchivoPromociones() {
		Map<String, Atraccion> mapaAtracciones = new HashMap<>();
		mapaAtracciones.put("Trekking", new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA));
		mapaAtracciones.put("Rafting", new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA));
		mapaAtracciones.put("Senderismo", new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA));

		Atraccion[] atracciones = { new Atraccion("Trekking", 500, 3, 5, TipoDeAtraccion.AVENTURA),
				new Atraccion("Rafting", 900, 1, 10, TipoDeAtraccion.AVENTURA),
				new Atraccion("Senderismo", 1300, 5.5, 6, TipoDeAtraccion.AVENTURA) };

		Archivo archivoPromociones = new Archivo("PromocionesTest");
		List<Promocion> resultadoObtenido = archivoPromociones.leerArchivoPromocion(mapaAtracciones,Archivo.RUTA_ARCHIVOS_TESTS_ENTRADA);

		List<Promocion> resultadoEsperado = new ArrayList<>();
		resultadoEsperado.add(new Absoluta(TipoDeAtraccion.AVENTURA, atracciones, TipoDePromocion.ABSOLUTA));

		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void ordenarPromociones() {
		Usuario usuario = new Usuario("Carlos", 30000, 20, TipoDeAtraccion.AVENTURA);
		List<Promocion> promociones = new ArrayList<Promocion>();
		Ordenador ordenador = new Ordenador();
		int opcion = 1;
		Promocion promocionPreferida = new Porcentual(TipoDeAtraccion.AVENTURA, atraccionesPreferidas,
				TipoDePromocion.PORCENTUAL);
		Promocion segundaPromocionPreferida = new Porcentual(TipoDeAtraccion.AVENTURA, segundoLoteAtraccionesPreferidas,
				TipoDePromocion.PORCENTUAL);
		Promocion tercerPromocionPreferida = new Porcentual(TipoDeAtraccion.AVENTURA, tercerLoteAtraccionesPreferidas,
				TipoDePromocion.PORCENTUAL);
		Promocion promocionNoPreferida = new Bonificada(TipoDeAtraccion.PAISAJE, atraccionesNoPreferidas,
				TipoDePromocion.BONIFICADA);

		promociones.add(promocionPreferida);
		promociones.add(segundaPromocionPreferida);
		promociones.add(tercerPromocionPreferida);
		promociones.add(promocionNoPreferida);
		List<Promocion> promocionesEsperadas = new ArrayList<Promocion>();
		promocionesEsperadas.add(promocionPreferida);
		promocionesEsperadas.add(tercerPromocionPreferida);
		promocionesEsperadas.add(segundaPromocionPreferida);

		List<Promocion> promocionesObtenidas = ordenador.ordenarPromociones(usuario, promociones, opcion);
		Assert.assertEquals(promocionesEsperadas, promocionesObtenidas);
	}

	@Test
	public void ordenarAtracciones() {
		Usuario usuario = new Usuario("Carlos", 15000, 12, TipoDeAtraccion.AVENTURA);
		Ordenador ordenador = new Ordenador();
		Map<String, Atraccion> atracciones = new LinkedHashMap<>();
		int j = 0;
		for (int i = 0; i < 6; i++) {
			if (i < 3) {
				atracciones.put(atraccionesPreferidas[i].getNombre(), atraccionesPreferidas[i]);
			} else {
				atracciones.put(segundoLoteAtraccionesPreferidas[j].getNombre(), segundoLoteAtraccionesPreferidas[j]);
				j++;
			}
		}
		int opcion = 1;
		Map<String, Atraccion> atraccionesEsperadas = new LinkedHashMap<>();
		atraccionesEsperadas.put(atraccionesPreferidas[1].getNombre(), atraccionesPreferidas[1]);
		atraccionesEsperadas.put(atraccionesPreferidas[2].getNombre(), atraccionesPreferidas[2]);
		atraccionesEsperadas.put(segundoLoteAtraccionesPreferidas[2].getNombre(), segundoLoteAtraccionesPreferidas[2]);
		atraccionesEsperadas.put(atraccionesPreferidas[0].getNombre(), atraccionesPreferidas[0]);
		atraccionesEsperadas.put(segundoLoteAtraccionesPreferidas[0].getNombre(), segundoLoteAtraccionesPreferidas[0]);
		atraccionesEsperadas.put(segundoLoteAtraccionesPreferidas[1].getNombre(), segundoLoteAtraccionesPreferidas[1]);
		Map<String, Atraccion> atraccionesObtenidas = ordenador.ordenarAtraccionesPorPreferencia(usuario, atracciones,
				opcion);
		Assert.assertEquals(atraccionesEsperadas, atraccionesObtenidas);

	}

}
