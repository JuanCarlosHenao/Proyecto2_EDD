package Clases;

public class Queen {

	static int N = 8;  
	static int k = 1; 

	static void imprimir(int board[][]) {  
	    System.out.printf("%d-\n", k++);  
	    for (int i = 0; i < N; i++) {  
	        for (int j = 0; j < N; j++)  
	            System.out.printf(" %d ", board[i][j]);  
	        System.out.printf("\n");  
	    }  
	    System.out.printf("\n");  
	}  
	
	 public static boolean esSeguro(int board[][], int row, int col) {  
	    int i, j;  
	
	    for (i = 0; i < col; i++)  
	        if (board[row][i] == 1)  
	            return false;  
	  
	    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)  
	        if (board[i][j] == 1)  
	            return false;  
	    
	    for (i = row, j = col; j >= 0 && i < N; i++, j--)  
	        if (board[i][j] == 1)  
	            return false;  
	  
	    return true;  
	}  
	
	public static boolean solucion(int board[][], int col) {  
	    if (col == N) {  
	    	imprimir(board);  
	        return true;  
	    }  
	    
	    boolean res = false;  
	    
	    for (int i = 0; i < N; i++)  {
	    	
	        if (esSeguro(board, i, col) ) {  
	        	
	            board[i][col] = 1; 
	            res = solucion(board, col + 1) || res;  
	            board[i][col] = 0; // BACKTRACK  
	        }  
	    }  
	 
	    return res;  
	}  
	  
	public static void NReinas() {  
	    int board[][] = new int[N][N];  
	  
	    if (solucion(board, 0) == false) {  
	        System.out.printf("No existe una solución");  
	        return ;  
	    }  
	  
	    return ;  
	}  
	
	public static void main(String[] args) {
		
		NReinas(); 
	}

}