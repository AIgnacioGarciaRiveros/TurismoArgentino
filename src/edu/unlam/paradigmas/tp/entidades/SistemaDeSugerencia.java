package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class SistemaDeSugerencia {

	public void iniciarSistema() {

		Archivo archivo = new Archivo("UsuariosTest");
		List<Usuario> usuarios = new ArrayList<>();

		usuarios = archivo.leerArchivoUsuario();

		System.out.println("============ ARCHIVO USUARIOS ============");
		for (Usuario usuario : usuarios)
			System.out.println(usuario);
		System.out.println("====================================");
		System.out.println();
		System.out.println();

		Archivo archivoAtraccion = new Archivo("Atracciones");
		Map<String, Atraccion> atracciones = new HashMap<>();
		atracciones = archivoAtraccion.leerArchivoAtraccion();
		System.out.println("============ ARCHIVO ATRACCIONES ============");
		for (Map.Entry<String, Atraccion> entry : atracciones.entrySet())
			System.out.println(entry.getValue());
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		
		Archivo archivoPromocion = new Archivo("Promociones");
		List<Promocion> promociones = new ArrayList<>();
		promociones = archivoPromocion.leerArchivoPromocion(atracciones);
		System.out.println("============ ARCHIVO PROMOCION ============");
		for (Promocion promocion : promociones) 
			System.out.println(promocion);
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		Ordenador ordenador = new Ordenador();
		Map<String,Atraccion> atracciones2 = ordenador.ordenarAtraccionesPorPreferencia(usuarios.get(0), atracciones);
		for (Map.Entry<String, Atraccion> entry : atracciones2.entrySet()) {
			System.out.println(entry);
		}
		System.out.println();
		System.out.println();
		
		
		
		AtraccionIteratorImpl iteradorAtracciones = new AtraccionIteratorImpl(atracciones2, usuarios.get(2));
		System.out.println("============ ITERADOR ATRACCIONES ============");
		while(iteradorAtracciones.hasNext()) {
			Atraccion atraccion = iteradorAtracciones.next();
			if(atraccion == null) {
				System.out.println("No hay atracciones que sean validas para la compra");
			}
			else {
				System.out.println(atraccion.toString());
			}
		}
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		
		PromocionIteratorImpl iteradorPromociones = new PromocionIteratorImpl(promociones, usuarios.get(1),atracciones2);
		System.out.println("============ ITERADOR PROMOCIONES ============");
		while(iteradorPromociones.hasNext()) {
			try {
				System.out.println(iteradorPromociones.next().toString());
			}
			catch(NoSuchElementException e) {
				System.err.println("No se encontró una promocion que sea valida para la compra");
			}
		}
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		InterfazUsuario interfaz= new InterfazUsuario();
		interfaz.iniciarSistema(usuarios.get(1).getNombre());
		interfaz.sugerirPromociones(promociones,atracciones,usuarios.get(1));
		PromocionIteratorImpl iteradorPromociones2 = new PromocionIteratorImpl(promociones, usuarios.get(1),atracciones2);
		System.out.println("============ ITERADOR PROMOCIONES ============");
		while(iteradorPromociones2.hasNext()) {
			try {
				System.out.println(iteradorPromociones2.next().toString());
			}
			catch(NoSuchElementException e) {
				System.err.println("No se encontró una promocion que sea valida para la compra");
			}
		}
		System.out.println("====================================");
		System.out.println();
		System.out.println();
	}

	
	

}
