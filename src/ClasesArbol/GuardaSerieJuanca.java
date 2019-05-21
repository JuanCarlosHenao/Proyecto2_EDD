package ClasesArbol;


public class GuardaSerieJuanca {
	
	/*
	 * dado un termino de la sucesion, se va a ir calculando cada termino de la serrie fibonnaci
	 * y se va a ir agregando a un nodo, desde el termino 3
	 */
	
	
	
	/*
	 * Esta es la serie fibonacci de forma recursiva, hay que modificarla para que 
	 * cada termino se guarde en un nodo de un ABB
	 * este metodo retorna el termino N de la sucesion
	 *
	*/
	
	public static long fibonacciR (long  n ) {
		if (n==0 || n==1) {
			return n;
		}
		return fibonacciR(n-1)+fibonacciR(n-2);
		
	}
	
	
	
	 //fibonacci recursivo optimizado
	   
	   public static long fibonacciFast(int n ) {
		   int actual=(n==0)?0:1;
		   return fibonacciFastR(n,0,actual);
		   
	   }
	private static long fibonacciFastR(int n, long anterior, long actual) {
		if(n<=1) 
			return actual;
		return fibonacciFastR(n-1,actual,actual+anterior);
	
	}
	
	
	
	public GuardaSerieJuanca(int terminoN) {
		
		imprimirArbol(crearArbol(terminoN));
		
	}

	public static void crearArbol( int terminoN) {
		ABB <Integer> arbol= new ABB<Integer>();
		fibonacciR(terminoN, arbol);
	}
	
	public static Integer fibonacciR(int n,ABB<Integer> arbol) {
		if (n==0 || n==1 ) {
			NodoB<Integer> ultimo= new NodoB(1);
			arbol.insertNodo(ultimo);
			return ultimo.getLlave();
		}
		else {
			NodoB<Integer> termino1=new NodoB<Integer>(fibonacciR(n-1, arbol));
			NodoB<Integer> termino2=new NodoB<Integer>(fibonacciR(n-2, arbol));
			arbol.insertNodo(termino1);
			arbol.insertNodo(termino2);
			return termino1.getLlave()+termino2.getLlave();
		}
		
	}

	public void imprimirArbol (ABB<Integer> arbol) {
		arbol.inOrden();
	}
	
	public static void main(String[] args) {
//		GuardaSerieJuanca serie=new GuardaSerieJuanca(5);
		System.out.println(	fibonacciR(6));
		
		
		
		
	}

}
