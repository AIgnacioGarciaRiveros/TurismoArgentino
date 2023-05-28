package edu.unlam.paradigmas.tp.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaDeSugerencia {

	public void iniciarSistema() {
		
		Archivo archivo = new Archivo("Usuarios");
		List<Usuario> usuarios = new ArrayList<>();
		
		usuarios = archivo.leerArchivoUsuario();
		
		System.out.println("\n\n============ ARCHIVO USUARIOS ============\n\n");
		System.out.println(usuarios);
		System.out.println("\n\n====================================\n\n");
		
		Archivo archivoAtraccion = new Archivo("Atracciones");
		Map<String, Atraccion> atracciones = new HashMap<>();
		
		atracciones = archivoAtraccion.leerArchivoAtraccion();
		
		System.out.println("\n\n============ ARCHIVO ATRACCIONES ============\n\n");
		System.out.println(atracciones);
		System.out.println("\n\n====================================\n\n");
		
		Archivo archivoPromocion = new Archivo("Promociones");
		List<Promocion> promociones = new ArrayList<>();
		
		promociones = archivoPromocion.leerArchivoPromocion(atracciones);
		
		System.out.println("\n\n============ ARCHIVO PROMOCION ============\n\n");
		System.out.println(promociones);
		System.out.println("\n\n====================================\n\n");
	}
	
}
