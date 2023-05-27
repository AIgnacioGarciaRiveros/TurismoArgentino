package edu.unlam.paradigmas.tp.entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class Archivo {
	private String nombre;

	public Archivo(String nombre) {
		this.nombre = nombre;
	}

	public void crearArchivo(int[] datos) {
		FileWriter file = null;
		PrintWriter printWriter = null;
		try {
			file = new FileWriter(
					"casos de prueba/edu.unlam.paradigmas.entradasalida.ej01/out esperado/" + this.nombre + ".out");
			printWriter = new PrintWriter(file);
			for (int i = 0; i < datos.length; i++) {
				printWriter.println(datos[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int[] leerArchivo() {
		Scanner scanner = null;
		int[] datos = null;
		try {
			File file = new File("casos de prueba/edu.unlam.paradigmas.entradasalida.ej01/in/" + this.nombre + ".in");
			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);
			int cant = scanner.nextInt();
			datos = new int[cant];
			for (int i = 0; i < cant; i++) {
				int n = scanner.nextInt();
				datos[i] = n;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return datos;
	}

	public void guardarArchivo(int[] datos) {
		FileWriter file = null;
		PrintWriter printWriter = null;
		try {
			file = new FileWriter(
					"casos de prueba/edu.unlam.paradigmas.entradasalida.ej01/out/" + this.nombre + ".out");
			printWriter = new PrintWriter(file);
			printWriter.println(datos.length);
			for (int i = 0; i < datos.length; i++) {
				printWriter.println(datos[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
