package negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;


public class Logica {

	
	public static HashMap<Integer,Integer> filas = new HashMap<Integer,Integer>();//<POSICION,SUMA>
	public static HashMap<Integer,Integer> columnas = new HashMap<Integer,Integer>();//<POSICION,SUMA>
	
	public static String tiempoRecord;
	
	//GENERA UN N RANDOM, CON DETERMINADAS CONDICIONES
	public int crear_n_Random() {
		return (int) ((Math.random() * 9) + 1);
	}
	
	public static void calcularSumaFila (int numFila, int sumar) {
		int sumaAuxFila = filas.get(numFila);
		filas.put(numFila, sumaAuxFila + sumar);
	}

	//SUMA POR COLUMNAS Y AGREGA EL RESULTADO AL MAP
	public static void calcularSumaColu(int numColu, int sumar) {
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
		java.io.InputStream entrada = Logica.class.getResourceAsStream("listaRecords.txt");

		try {
			Reader lectura = new InputStreamReader(entrada, "utf-8");
			BufferedReader leer = new BufferedReader(lectura);

			String linea;
			while ((linea = leer.readLine()) != null) {
				tiempoRecord = linea;
			}
			lectura.close();
			entrada.close();
			leer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void guardarRecords(String recordnuevo) {
		File archivotxt = new File("src/negocio/listaRecords.txt");

		try {

			FileWriter archivoEscritura = new FileWriter(archivotxt);
			BufferedWriter escritura = new BufferedWriter(archivoEscritura);
			escritura.write(recordnuevo);
			escritura.close();
			archivoEscritura.close();

			tiempoRecord = recordnuevo;
			System.out.println(tiempoRecord);

		} catch (Exception e2) {
			throw new RuntimeException("Error al guardar el record");
		}
	}
	
	public static void compararRecords (int tiempoActual) {
		if (Integer.parseInt(tiempoRecord) > (tiempoActual-1))
			guardarRecords(Integer.toString(tiempoActual-1));
	}
	
}

