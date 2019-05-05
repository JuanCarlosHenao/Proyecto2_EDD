
public class Queen {

	private static int N = 8;  
	private static int k = 1; 

	static void imprimir(int tablero[][]) {  
		System.out.println(" Solución " + k++); //Imprime cual solucion esta mostrando
		System.out.println(""); 
		for (int i = 0; i < N; i++) {  //Imprime el tablero
			for (int j = 0; j < N; j++)  
				System.out.print(" " + tablero[i][j]);  
			System.out.println(" ");  
		}  
		System.out.println(" ");  
	}  

	public static boolean esSeguro(int tablero[][], int fila, int columna) { //Revisa si la posicion de la reina es valida 
	
		for (int i = 0; i < columna; i++) //Revisa la fila
			if (tablero[fila][i] == 1)  
				return false;  

		for (int i = fila, j = columna; i >= 0 && j >= 0; i--, j--)  //Revisa la mitad superior de la diagonal
			if (tablero[i][j] == 1)  
				return false;  

		for (int i = fila, j = columna; j >= 0 && i < N; i++, j--) //Revisa la mitad inferior de la diagonal
			if (tablero[i][j] == 1)  
				return false;  

		return true;  
	}  

	public static boolean solucion(int tablero[][], int columna) {//Busca la solucion   
		boolean respuesta = false;  
		if (columna == N) {  //Reviso todas las columnas y todas las reinas 
			imprimir(tablero);  
			return true;  
		} else {
			for (int i = 0; i < N; i++)  {

				if (esSeguro(tablero, i, columna) == true) {  

					tablero[i][columna] = 1; 
					respuesta = (solucion(tablero, columna + 1) || respuesta);  
					tablero[i][columna] = 0; // BACKTRACK  
				}  
			}  
			return respuesta;
		} 
	}  

	public static void NReinas() {  
		
		int tablero[][] = new int[N][N];  

		if (solucion(tablero, 0) == false) {  //Busca soluciones posibles hasta que no encuentre alguna
			System.out.println("No existe una solución");  
			return ;
		} else {
			System.out.println("Estas son todas las posibles soluciones"); 
			return ;
		}
	}  

	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		NReinas(); 
		long end = System.currentTimeMillis();
		
		System.out.print(" Tiempo: " + (end - start));
	}

}
