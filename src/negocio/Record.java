package negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class Record {
	private static String tiempoRecord;
	
	
	public static void compararTiempo (int tiempoActual) {
		String directorio = comprobarDificultad(Logica.facil,Logica.normal,Logica.dificil);
		if (Integer.parseInt(tiempoRecord) > (tiempoActual))
			guardarNuevoRecord(directorio,Integer.toString(tiempoActual-1));
	}
	
	
	public static void abrirArchivoYCopiar (String directorio) {
		File archivotxt = new File(directorio);
		try {
			FileReader lectura = new FileReader (archivotxt);
			BufferedReader leer = new BufferedReader(lectura);
			String linea;
			while ((linea = leer.readLine()) != null) {
				tiempoRecord = linea;
			}
			lectura.close();
			leer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static String getTiempoRecord() {
		return tiempoRecord;
	}
	
	private static String comprobarDificultad (boolean facil, boolean normal,boolean dificil) {
		String directorio = "";
		if (facil == true) {
			directorio = "src/negocio/RecordFacil.txt";
			return directorio;
		}
		if (normal == true) {
			directorio ="src/negocio/RecordNormal.txt";
			return directorio;
		}
		if (dificil = true) {
			directorio = "src/negocio/RecordDificil.txt";
			return directorio;
		}
		return directorio;
			
	}

	private static void guardarNuevoRecord (String directorio,String tiempoNuevo) {
		File archivotxt = new File (directorio);
		try {

			FileWriter archivoEscritura = new FileWriter(archivotxt);
			BufferedWriter escritura = new BufferedWriter(archivoEscritura);
			escritura.write(tiempoNuevo);
			escritura.close();
			archivoEscritura.close();
			tiempoRecord = tiempoNuevo;

		} catch (Exception e2) {
			throw new RuntimeException("Error al guardar el record");
		}
		
	}
	
	
	
}