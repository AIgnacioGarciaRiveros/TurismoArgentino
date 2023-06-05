package edu.unlam.paradigmas.tp.entidades;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InterfazUsuario {
	/*
	 * public void interactuarConElUsuario(Usuario usuario) {
	 * iniciarSistema(usuario);
	 * 
	 * }
	 */

	public void iniciarSistema(String nombreUsuario) {
		System.out.println("============ Bienvenido a Turismo Argentino ============\n");
		System.out.println("Nombre visitante: " + nombreUsuario);
	}

	public void sugerirPromociones(List<Promocion> promociones,Map<String,Atraccion> atracciones, Usuario usuario) {
		Scanner scanner = new Scanner(System.in);
		char respuestaUsuario;
		System.out.println("============ ARCHIVO PROMOCION ============");
		Promocion promocion;
		System.out.println("USUARIO ANTES DE COMPRAR " + usuario);
		PromocionIteratorImpl promocionIt = new PromocionIteratorImpl(promociones, usuario,atracciones);
		while (promocionIt.hasNext()) {
			try {
				promocion = promocionIt.next();
				System.out.println(promocion);
				do {
					System.out.println("Aceptas esta sugerencia? Ingrese S o N");
					respuestaUsuario = Character.toUpperCase(scanner.next().charAt(0));
				} while (respuestaUsuario != 'S' && respuestaUsuario != 'N');
				if(respuestaUsuario == 'S') {
					procesarCompraPromocion(usuario,promocion,atracciones);
					System.out.println("USUARIO DESPUES  DE COMPRAR " + usuario);
				}
				
			}catch(NoSuchElementException e) {
				System.out.println(" No podes seguir comprando porque no te alcanza el tiempo");
			}finally {
				for (Map.Entry<String, Atraccion> entry : atracciones.entrySet()) {
					System.out.println(entry);
				}
			}
			
		}
		
		
		System.out.println("====================================");
		System.out.println();
		System.out.println();
	}

	public void procesarCompraPromocion(Usuario usuario,Promocion promocion,Map<String,Atraccion> atracciones) {
		usuario.setPresupuesto(usuario.getPresupuesto()-promocion.getPrecioConDescuento());
		usuario.setTiempoDisponible(usuario.getTiempoDisponible()-promocion.getDuracion());
		Atraccion[] atraccionesOfertadas = promocion.getAtracciones();
		for(int i = 0 ; i< atraccionesOfertadas.length;i++) {
			Atraccion atraccionEncontrada = atracciones.get(atraccionesOfertadas[i].getNombre());
			atraccionEncontrada.setEstaDisponible(false);
			atraccionEncontrada.setCupoDiario(atraccionEncontrada.getCupoDiario()-1);
		}
	}
	
	
}
