package ClasesArbol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Stack;





public class ArbolB <E extends Comparable <E>> {
	protected NodoB<E> raiz;
	
	
	public ArbolB() {
		raiz=null;
	}
	public ArbolB(NodoB<E> raiz) {
		super();
		this.raiz = raiz;
	}

	public NodoB<E> getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoB<E> raiz) {
		this.raiz = raiz;
	}
	
	
	public void preOrden () {
		preOrdenR(raiz);
		System.out.println();
	}
	private void preOrdenR(NodoB<E> r) {
		if (r!=null) {
			System.out.print(r.getLlave()+" ");
			preOrdenR(r.getHijoIzq());
			preOrdenR(r.getHijoDer());
		}
	}
	
	
	public void postOrden () {
		postOrdenR(raiz);
		System.out.println();
	}
	private void postOrdenR(NodoB<E> r) {
		if (r!=null) {
			
			postOrdenR(r.getHijoIzq());
			postOrdenR(r.getHijoDer());
			System.out.print(r.getLlave()+" ");
		}	
	}
	
	
	public void inOrden () {
		inOrdenR(raiz);
		System.out.println();
	}
	private void inOrdenR(NodoB<E> r) {
		if (r!=null) {
			inOrdenR(r.getHijoIzq());
			System.out.print(r.getLlave()+" ");
			inOrdenR(r.getHijoDer());
		}
	}
	
	
	public ArrayList <E> preordenList(){
		ArrayList<E> list= new ArrayList<E>();
		preordenListR(raiz,list);
		return list;
	}
	private void preordenListR(NodoB<E> r, ArrayList<E> list) {
		// TODO Auto-generated method stub
		if (r!=null) {
			list.add(r.getLlave());
			preordenListR(r.getHijoIzq(),list);
			preordenListR(r.getHijoDer(),list);
			
		}
	}
	
	public String decode(String message) {
	    String[] morseSymbols = message.split(" "); // separa el mensaje por palabras de signos 
	    String decodedMessage = ""; // ´para iniciar el mensaje en blanco 

	    for (int k = 0; k < morseSymbols.length; k++) {// la cantidad de palabras 
	      NodoB<E> currentNode = this.raiz;
	      String morseSymbol = morseSymbols[k];// la primera palabra de signos 

	      if (morseSymbol.equals("")) {// si la primera palabra es vacia
	        decodedMessage += " ";
	      }

	      for (int l = 0; l < morseSymbol.length(); l++) {// para recorrer la palabra de signos 
	        char singleChar = morseSymbol.charAt(l);// coge el primer caracter

	        if (currentNode.getHijoIzq() == null && currentNode.getHijoDer() == null) {
	          break;
	        } else if (singleChar == '.') { // si es un punto va la izquierda
	          currentNode = currentNode.getHijoIzq();
	        } else {
	          currentNode = currentNode.getHijoDer(); // va a la derecha 
	        }

	        if (l == morseSymbol.length() - 1) { // si recorrió toda la palabra de signos devuelve la letra
	          decodedMessage += currentNode.getLlave();
	        }
	      }
	    }
	    return decodedMessage;
	  }
	
	 /**
     * decodes a String of English to morse code
     * @param str String of English
     * @return result String of morse code
     * current = actual 
     */

   
    public String codificar(String mensaje) {
        mensaje = mensaje.trim().toUpperCase();
        /*
         *  El giro postal comienza desde la parte inferior de las ramas de la izquierda hasta el hijo de
         raíz, luego lo mismo en las ramas de la derecha, luego el propio nodo. Este es
         el mejor orden para construir nuestros símbolos de código morse basados en el
         ubicación de cada nodo
         */
        Queue<NodoB<E>> nodosTransversales = postOrdenTransversal();
        String[] simbolosMorse = generarArrayMorse(nodosTransversales);
        String mensajeCodificado = "";

        for (int k = 0; k < mensaje.length(); k++) {
          if (mensaje.charAt(k) == ' ') {
            mensajeCodificado.trim();
            mensajeCodificado += " ";
          } else {
            if (Character.isLetter(mensaje.charAt(k))) {
              int ascii = (int) mensaje.charAt(k);
              mensajeCodificado += simbolosMorse[ascii - 65] + " ";
            }
          }
        }
        return mensajeCodificado.trim();
      }
    
    /**

     *  Crea la matriz de código morse basada en una cola de
     * {@link MorseCodeNode}s. Crea los símbolos de código morese arrastrándose hacia arriba por la ventana de diálogo
     *	hasta que encuentre la raíz.
     * @param nodosTransversales
     * @return
     */
    
  
    private String[] generarArrayMorse(Queue<NodoB<E>> nodosTransversales) {
    	String[] arraySimbolosMorse = new String[26];
      Iterator<NodoB<E>> cola=nodosTransversales.iterator();
      for (NodoB<E> nodo : nodosTransversales ) {
    	  Stack<Character> pilaSimbolosMorse = new Stack<Character>();
          NodoB<E> nodoActual =nodo;
          boolean raizEncontrada = false;
          Character v=' ';
          if (nodoActual.getLlave().compareTo((E) v )==0) {
            raizEncontrada = true;
          }
          while (!raizEncontrada) {
            if (!(nodoActual.getLlave().compareTo((E)v )==0)) {// nodoActual.getLlave().equals("")      nodoActual.getLlave()==""     nodoActual.getPadre()==null    nodoActual.getLlave().compareTo((E) "")==0
	              if (nodoActual.getPadre().getHijoIzq()!=null && nodoActual.getPadre().getHijoIzq().getLlave().compareTo((E) nodoActual.getLlave())==0) {
	                pilaSimbolosMorse.push('.');
	                nodoActual = nodoActual.getPadre();
	              } else {
	                pilaSimbolosMorse.push('-');
	                nodoActual = nodoActual.getPadre();
	              }          
            } else {
              String simboloMorse = "";
              char alfa = (char) nodo.getLlave();
              raizEncontrada = true;
              while (!pilaSimbolosMorse.isEmpty()) {
                simboloMorse += pilaSimbolosMorse.pop();
              }
              int ascii = (int) alfa;
              arraySimbolosMorse[ascii - 65] = simboloMorse;
            }
          } 
      }
      return arraySimbolosMorse;
    }

    /**
     * Crea una cola de nodos que han estado en cola "post-order". Llamadas
     * {@link this#postOrderTraversal(MorseCodeNode, Queue)} para continuar con el comando
     * ordenar de forma recursiva.
     * @return traversedNodes the nodes post-ordered in a queue.
     */
    private Queue<NodoB<E>> postOrdenTransversal() {
      Queue<NodoB<E>> nodosTransversales = new LinkedList<>();
      nodosTransversales = postOrdenTransversalR(raiz.getHijoIzq(),nodosTransversales);
      nodosTransversales = postOrdenTransversalR(raiz.getHijoDer(),nodosTransversales);
      nodosTransversales.add(raiz);
      return nodosTransversales;
    }

    /**
     * Crea una cola de nodos que han sido encolados "post-order". Convocado por
     * {@link this#postOrderTraversal()} y a sí mismo recursivamente. 
     * @param nodo El nodo actual que se está atravesando
     * @param @return traversedNodes La cola actual de los nodos atravesados
     */
    private Queue<NodoB<E>> postOrdenTransversalR(NodoB<E> nodo,Queue<NodoB<E>> nodosTransversales) {
      if (nodo != null) {
        nodosTransversales = postOrdenTransversalR(nodo.getHijoIzq(),nodosTransversales);
        nodosTransversales = postOrdenTransversalR(nodo.getHijoDer(), nodosTransversales);
        nodosTransversales.add(nodo);
      }
      return nodosTransversales;
    }
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		NodoB<Integer> n1= new NodoB<Integer>(10);
//		NodoB<Integer> n2= new NodoB<Integer>(7);
//		NodoB<Integer> n3= new NodoB<Integer>(15);
//		NodoB<Integer> n4= new NodoB<Integer>(5);
//		NodoB<Integer> n5= new NodoB<Integer>(9);
//		NodoB<Integer> n6= new NodoB<Integer>(12);
//		n3.setHijoIzq(n6);
//		n2.setHijoIzq(n4);
//		n2.setHijoDer(n5);
//		n1.setHijoIzq(n2);
//		n1.setHijoDer(n3);
//		ArbolB<Integer> a= new ArbolB<Integer>(n1);
//		a.preOrden();
//		a.postOrden();
//		a.inOrden();
//		a.preordenList();
//		System.out.println(a.preordenList());
		
		NodoB<Character> n0 = new NodoB<Character>(' ');
		
		NodoB<Character> n1 = new NodoB<Character>('E');
		NodoB<Character> n2 = new NodoB<Character>('I');
		NodoB<Character> n3 = new NodoB<Character>('A');
		NodoB<Character> n4 = new NodoB<Character>('S');
		NodoB<Character> n5 = new NodoB<Character>('U');
		NodoB<Character> n6 = new NodoB<Character>('R');
		NodoB<Character> n7 = new NodoB<Character>('W');
		NodoB<Character> n8 = new NodoB<Character>('H');
		NodoB<Character> n9 = new NodoB<Character>('V');
		NodoB<Character> n10 = new NodoB<Character>('F');
		NodoB<Character> n11 = new NodoB<Character>('L');
		NodoB<Character> n12 = new NodoB<Character>('P');
		NodoB<Character> n13 = new NodoB<Character>('J');
		
		NodoB<Character> n14 = new NodoB<Character>('T');
		NodoB<Character> n15 = new NodoB<Character>('N');
		NodoB<Character> n16 = new NodoB<Character>('M');
		NodoB<Character> n17 = new NodoB<Character>('D');
		NodoB<Character> n18 = new NodoB<Character>('K');
		NodoB<Character> n19 = new NodoB<Character>('G');
		NodoB<Character> n20 = new NodoB<Character>('O');
		NodoB<Character> n21 = new NodoB<Character>('B');
		NodoB<Character> n22 = new NodoB<Character>('X');
		NodoB<Character> n23 = new NodoB<Character>('C');
		NodoB<Character> n24 = new NodoB<Character>('Y');
		NodoB<Character> n25 = new NodoB<Character>('Z');
		NodoB<Character> n26 = new NodoB<Character>('Q');
		
		n0.setHijoIzq(n1);
		n1.setHijoIzq(n2);
		n1.setHijoDer(n3);
		n2.setHijoIzq(n4);
		n2.setHijoDer(n5);
		n3.setHijoIzq(n6);
		n3.setHijoDer(n7);
 		n4.setHijoIzq(n8);
 		n4.setHijoDer(n9);
 		n5.setHijoIzq(n10);
 		n6.setHijoIzq(n11);
 		n7.setHijoIzq(n12);
 		n7.setHijoDer(n13);
 		
 		n0.setHijoDer(n14);
 		n14.setHijoIzq(n15);
 		n14.setHijoDer(n16);
 		n15.setHijoIzq(n17);
 		n15.setHijoDer(n18);
 		n16.setHijoIzq(n19);
 		n16.setHijoDer(n20);
 		n17.setHijoIzq(n21);
 		n17.setHijoDer(n22);
 		n18.setHijoIzq(n23);
 		n18.setHijoDer(n24);
 		n19.setHijoIzq(n25);
 		n19.setHijoDer(n26);
 		
		ArbolB<Character> a = new ArbolB<Character>(n0);
		String mensaje = "...- .- -- --- ...  .-  ... .- -.-. .- .-.  -.-. .. -. -.-. ---" ;
		System.out.println("de morse a normal= "+a.decode(mensaje));
		System.out.println(a.postOrdenTransversal());
		System.out.println("de normal a morse= "+a.codificar("V"));
		System.out.println("de normal a morse2= "+a.codificar("VAMOS A SACAR CINCO"));
		System.out.println();
	
		
		

	}

}
