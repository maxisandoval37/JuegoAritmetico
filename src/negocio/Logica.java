package negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import javax.print.DocFlavor.URL;

import presentacion.Interfaz;

public class Logica {

	
	public static HashMap<Integer,Integer> filas = new HashMap<Integer,Integer>();//<POSICION,SUMA>
	public static HashMap<Integer,Integer> columnas = new HashMap<Integer,Integer>();//<POSICION,SUMA>
	private static String rutaDelFichero;
	private static String tiempoRecord;
	
	//GENERA UN N RANDOM, CON DETERMINADAS CONDICIONES
	public int crear_n_Random() {
		return (int) ((Math.random() * 9) + 1);
	}
	
	public static void calcularSumaFila (int numFila, int sumar) {
		int sumaAuxFila = filas.get(numFila);
		filas.put(numFila, sumaAuxFila + sumar);
	}

	//SUMA POR COLUMNAS Y AGREGA EL RESULTADO AL MAP
	public static void calcularSumaColu(int numColu, int sumar) {// deberia retornar la suma, despues corregir
		if (columnas.containsKey(numColu)) {
			int sumaAuxColu = columnas.get(numColu);
		columnas.put(numColu, sumaAuxColu + sumar);
		} else
			columnas.put(numColu, sumar);
	}

	public static HashMap<Integer, Integer> getFilas() {
		return filas;
	}

	public static void setFilas(HashMap<Integer, Integer> filas) {
		Logica.filas = filas;
	}

	public static HashMap<Integer, Integer> getColumnas() {
		return columnas;
	}

	public static void setColumnas(HashMap<Integer, Integer> columnas) {
		Logica.columnas = columnas;
	}
	
	
	
	public static boolean equalsNumeros (int a, int b) {
		return a==b;
	}
	
	
	
	public static int sumaNumeros (int a, int b) {
		return a+b;
	}
	
	
	
	//ABRIR Y GUARDAR MEJOR TIEMPO (FALTA MOSTRARLO EN PANTALLA)
	
	public static void abrirArchivoRecord() {
		
		java.net.URL url =Logica.class.getResource("/negocio/listaRecords.txt"); 
		File file = new File(url.getPath()); 
		
		//rutaDelFichero = "/negocio/listaRecords.txt";
		
		//File records = new File(rutaDelFichero);
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				tiempoRecord = linea;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void guardarRecords(String recordnuevo) {
		try {
			//BufferedWriter bw = new BufferedWriter(new FileWriter(rutaDelFichero));
			java.net.URL url = Logica.class.getResource("/negocio/listaRecords.txt");
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(url.toString()));
			
			bw.write("");
			bw.close();
			File archivo = new File(url.getPath());
			FileWriter escribir = new FileWriter(archivo, true);
			escribir.write(recordnuevo);
			escribir.write("\r\n");
			escribir.close();
			tiempoRecord = recordnuevo;

		} catch (Exception e2) {
			System.out.println("Error al copiar el record");
		}
	}
	
	public static void compararRecords (int tiempoActual) {
		if (Integer.parseInt(tiempoRecord) > (tiempoActual-1))
			guardarRecords(Integer.toString(tiempoActual-1));

	}
	
	
}

