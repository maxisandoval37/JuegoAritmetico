package negocio;


import java.util.HashMap;


public class Logica {

	
	public static HashMap<Integer,Integer> Sumafilas = new HashMap<Integer,Integer>();//<POSICION,SUMA>
	public static HashMap<Integer,Integer> Sumacolumnas = new HashMap<Integer,Integer>();//<POSICION,SUMA>
	
	
	public static boolean facil = false;
	public static boolean normal = false;
	public static boolean dificil = false;
	
	//GENERA UN N RANDOM, CON DETERMINADAS CONDICIONES
	public int crear_n_Random() {
		return (int) ((Math.random() * 9) + 1);
	}
	
	public static void calcularSumaFila (int numFila, int sumar) {
		int sumaAuxFila = Sumafilas.get(numFila);
		Sumafilas.put(numFila, sumaAuxFila + sumar);
	}

	//SUMA POR COLUMNAS Y AGREGA EL RESULTADO AL MAP
	public static void calcularSumaColu(int numColu, int sumar) {
		if (Sumacolumnas.containsKey(numColu)) {
			int sumaAuxColu = Sumacolumnas.get(numColu);
		Sumacolumnas.put(numColu, sumaAuxColu + sumar);
		} else
			Sumacolumnas.put(numColu, sumar);
	}

	public static HashMap<Integer, Integer> getFilas() {
		return Sumafilas;
	}

	public static void setFilas(HashMap<Integer, Integer> filas) {
		Logica.Sumafilas = filas;
	}

	public static HashMap<Integer, Integer> getColumnas() {
		return Sumacolumnas;
	}

	public static void setColumnas(HashMap<Integer, Integer> columnas) {
		Logica.Sumacolumnas = columnas;
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

