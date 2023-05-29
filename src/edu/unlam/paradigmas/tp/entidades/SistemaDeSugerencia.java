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
		
		System.out.println("============ ARCHIVO USUARIOS ============");
		for (Usuario usuario : usuarios)
			System.out.println(usuario);
		System.out.println("====================================");
		
		Archivo archivoAtraccion = new Archivo("Atracciones");
		Map<String, Atraccion> atracciones = new HashMap<>();
		
		atracciones = archivoAtraccion.leerArchivoAtraccion();
		
		System.out.println("============ ARCHIVO ATRACCIONES ============");
		for( Map.Entry<String, Atraccion> entry : atracciones.entrySet() )
		    System.out.println(entry.getValue());
		System.out.println("====================================");
		
		Archivo archivoPromocion = new Archivo("Promociones");
		List<Promocion> promociones = new ArrayList<>();
		
		promociones = archivoPromocion.leerArchivoPromocion(atracciones);
		
		System.out.println("============ ARCHIVO PROMOCION ============");
		for (Promocion promocion : promociones) {
			System.out.println(promocion);
		}
		System.out.println("====================================");
		
		
		
	}
	
}
