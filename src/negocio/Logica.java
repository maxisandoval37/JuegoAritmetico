package negocio;


import java.util.HashMap;


public class Logica {

	
	public static HashMap<Integer,Integer> filas = new HashMap<Integer,Integer>();//<POSICION,SUMA>
	public static HashMap<Integer,Integer> columnas = new HashMap<Integer,Integer>();//<POSICION,SUMA>
	
	
	public static boolean facil = false;
	public static boolean normal = false;
	public static boolean dificil = false;
	
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
	
	
	

	
	public static boolean esUnNumuero(char c) {
		return    ((c < '1') || (c > '9')) && (c != '\b');
	}
	
	

	
	
}

