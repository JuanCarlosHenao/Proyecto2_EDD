package Clases;


public class OchoReinas {

	/**
	 * @param args
	 */
	
	final int N=8;
	boolean solucion;
	private int [][] tablero;
	
	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}
	
	public void imprimirTablero(){
		for(int i=0;i<tablero.length;i++){
			for (int j=0;j<tablero.length;j++)
				System.out.print(tablero[i][j]+ "\t");
			System.out.println();
		}
			
	}
	
	public OchoReinas(){
		tablero=new int[N][N];
	}
	
	public void solucionTodas(){
		solucionT(0);
	}
	
	public void solucionT(int col){
		int fila= 0; // cantidad de posiciones posibles (8 filas como máximo)
		while (fila<N){
			if (col>=N){
			}
			else{
				if (valida(fila, col)){// Verificar que la fila sea válida
					tablero[fila][col]=col+1;
					solucionT(col+1);
					tablero[fila][col]=0;// regreso atrás siempre
				}
			}
			fila++;// nueva fila
		}
		if (col>=N){
			imprimirTablero();
			System.out.println();
		}
	}
	
	public void solucion(){
		solucion(0);// cantidad de reinas
	}
	
	//cReinas- número de reinas
	public void solucion(int col){
		solucion=false; // bandera para saber si ya se encontró la solución
		int fila= 0; // cantidad de posiciones posibles (8 filas como máximo)
		while (fila<N && !solucion){
			if (col>=N)
				solucion=true;
			else{
				if (valida(fila, col)){// Verificar que la fila sea válida
					tablero[fila][col]=col+1;
					solucion(col+1);
					if (!solucion) // regreso atrás
						tablero[fila][col]=0;
					}
			}
			fila++;// nueva fila
		}
	}
	
	
	// Verifica si una posición es válida
	public boolean valida(int x){
		return (x>=0 && x<N);
	}
	
	public boolean valida(int fila, int col){
		// la misma fila y diagonal
		//tablero[fila][j]!=0;//hay una en la misma fila
		//tablero [fila-col] y tablero[fila+col] != 0 para que no haya reina en diagonal
		int j=0;
		while (j<col && tablero[fila][j]==0 && 
				((valida (fila-col+j) && tablero [fila-col+j][j]==0)||!(valida (fila-col+j))) && 
				((valida (fila+col-j) &&(tablero[fila+col-j][j] == 0))||!(valida (fila+col-j)))
				)
			j++;
		return (j<col)? false: true;
	}
	
	//Devuelve los ocho movimientos posibles

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OchoReinas r= new OchoReinas();
		r.solucion();
		r.imprimirTablero();
		System.out.println();
		//r.solucionTodas();

	}

}