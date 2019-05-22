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
	
	
	/*
	 * decodificar(), recibe un mensaje en morse y lo pasa a letras
	 */
	
	public String decodificar(String mensaje) {
		//Guarda cada palabra del mensaje en una posicion del vector, separa por espacios
	    String[] mensajeArray = mensaje.split(" "); 
	    String mensajeDecodificado = ""; // ´para iniciar el mensaje en blanco 

	    for (int k = 0; k < mensajeArray.length; k++) {//Recorre el mensaje palabra por palabra
	      NodoB<E> nodoActual = this.raiz;
	      String palabra = mensajeArray[k]; // la primera palabra en simbolos 

	      if (palabra.equals("")) { // si la primera palabra es vacia
	    	  mensajeDecodificado += " ";
	      }

	      for (int l = 0; l < palabra.length(); l++) {//Recorre cada palabra caracter por caracter
	        char caracter = palabra.charAt(l); // coge el primer caracter

	        if (nodoActual.getHijoIzq() == null && nodoActual.getHijoDer() == null) {
	          break;
	        } else if (caracter == '.') { // si es un punto va la izquierda
	        	nodoActual = nodoActual.getHijoIzq();
	        } else { //Si no es punto va a la derecha 
	        	nodoActual = nodoActual.getHijoDer();
	        }

	        if (l == palabra.length() - 1) { //Agrega la letra deseada al mensaje final cuando llega al ultimo simbolo de la palabra en morse
	        	mensajeDecodificado += nodoActual.getLlave();
	        }
	      }
	    }
	    return mensajeDecodificado;
	  }
	

   /*
    * codificar() recibe un mensaje normal y lo pasa a morse 
    * Le borra los espacios al principio y al final y lo pasa a mayusculas 
    * Guarda los nodos en posOrden en una cola que 
    * Guarda el equivalente en Morse de cada letra en un array de String 
    * Recorre cada letra de la oracion, lo pasa a ascci para obtener su numero y lo busca 
    * 	en el array de Simbolos Morse 
    * 
    */
    public String codificar(String mensaje) {
    	
        mensaje = mensaje.trim().toUpperCase(); // borra los espacios al principio y al final del mensaje y pasa todo a mayusculas
    	//	para evitar confusion con los separados
        Queue<NodoB<E>> nodosEnPostOrden = postOrdenNodos();// guarda el postOrden de los nodos
        String[] simbolosMorse = generarArrayMorse(nodosEnPostOrden);// guarda las equivalencias
        String mensajeCodificado = "";// para guardar el mensaje
        for (int k = 0; k < mensaje.length(); k++) {// recorre cada letra del mensaje 
          if (mensaje.charAt(k) == ' ') { // si es un espacio, guarda un espacio codificado
            mensajeCodificado.trim();
            mensajeCodificado += " ";
          } else {// si es una letra 
            if (Character.isLetter(mensaje.charAt(k))) {
              int ascii = (int) mensaje.charAt(k);// guarda el ascci de esa letra 
              // va al equivalente en morse del Array, coge su simbolo y le suma un espacio
              mensajeCodificado += simbolosMorse[ascii - 65] + " ";
            }
          }
        }
        return mensajeCodificado.trim(); // devuelve el mensaje codificado y le quita los espacios al principio y al final 
      }

    
    /*
     * generarArrayMorse() devuelve un array con el equivalente de cada letra en Morse
     * Recorre la cola del posOrden, coge el primer nodo e inicia una cola pila para guardar
     * 	cada codigo morse
     * Con ese nodo,va al padre, luego al hijo izquierdo, y tiene que ser el mismo nodo para
     * 	poder ascender de nivel, si asciende por la izquierda es un punto, si es por la 
     * 		derecha es un -
     * Si llega a la raiz, coge el nodo en el que va el foreach, lo pasa a ascii, y luego 
     * 	vacia la pilaSimboloMorse, para guardarla en la posicion ascci del arraySimbolosMorse
     */
    private String[] generarArrayMorse(Queue<NodoB<E>> nodosEnPostOrden) {
    	// Array para guardar el equivalente en Morse de cada letraa
    	String[] arraySimbolosMorse = new String[26];
      for (NodoB<E> nodo : nodosEnPostOrden ) {// cojo cada nodo para ver su equivalente 
    	  /*Inicio pila que guarda puntos y guiones hasta la raiz por cada letra */
    	  Stack<Character> pilaSimbolosMorse = new Stack<Character>();
          NodoB<E> nodoActual =nodo;// nodoActual es un nodo auxiliar para buscar su padre 
          boolean raizEncontrada = false; // me indica si llego hasta la raiz
          Character v=' '; // para comparar el espacio
          if (nodoActual.getLlave().compareTo((E) v )==0) {
            raizEncontrada = true; // si el nodoActual es la raiz no entra al while 
          }
          while (!raizEncontrada) {// si no encuentro la raiz 
            if (!(nodoActual.getLlave().compareTo((E)v )==0)) { 
            	/*Si nodoActual es != de vacio, cojo su padre,luego el hijo izquierdo del padre
            	 * 	y si llego al mismo nodoActual subo por la izquierda y guardo un punto 
            	 * 		en la pilaSimbolosMorse, sino, guardo un - , cojo el padre y repito 
            	 * 			hasta llegar a la raiz */

	              if (nodoActual.getPadre().getHijoIzq()!=null && nodoActual.getPadre().getHijoIzq().getLlave().compareTo((E) nodoActual.getLlave())==0) {
	                pilaSimbolosMorse.push('.');
	                nodoActual = nodoActual.getPadre();
	              } else {
	                pilaSimbolosMorse.push('-');
	                nodoActual = nodoActual.getPadre();
	              }          
            } else {// si llegue a la raiz
              String simboloMorse = "";// String para guardar lo que devuelva la pila 
              char alfa = (char) nodo.getLlave();// para guardar el nodoPostOrden y pasarlo a ascci
              raizEncontrada = true;
              while (!pilaSimbolosMorse.isEmpty()) {// vacio la pila y la guardo en un string 
            	  // me devuelve el simboloMorse de la letra 
                simboloMorse += pilaSimbolosMorse.pop();
              }
              int ascii = (int) alfa;
              /*Paso el nodoPostOrden a ascci que me permite tener una posicion en el 
               * 	arraySimboloMorse
               *  la letra H es la 65 en codigo ascci, y la primera del postOrden
               */
              arraySimbolosMorse[ascii - 65] = simboloMorse;
            }
          } 
      }
      return arraySimbolosMorse; // retorna el array de equivalencias letra-Morse
    }

    /*
     * postOrdenNodos() hace el postOrden del arbol y guarda los nodos en una cola 
     * 	para luego poderlos coger uno a uno 
     * Guardo los nodos en vez de las llaves porque en generarArrayMorse() necesito el padre
     * 	y el hijo izquierdo de cada nodo
     */
    private Queue<NodoB<E>> postOrdenNodos() {
      Queue<NodoB<E>> nodosEnPostOrden = new LinkedList<>();
      nodosEnPostOrden = postOrdenNodosR(raiz.getHijoIzq(),nodosEnPostOrden);
      nodosEnPostOrden = postOrdenNodosR(raiz.getHijoDer(),nodosEnPostOrden);
      nodosEnPostOrden.add(raiz);
      return nodosEnPostOrden;
    }

    private Queue<NodoB<E>> postOrdenNodosR(NodoB<E> nodo,Queue<NodoB<E>> nodosEnPostOrden) {
      if (nodo != null) {
        nodosEnPostOrden = postOrdenNodosR(nodo.getHijoIzq(),nodosEnPostOrden);
        nodosEnPostOrden = postOrdenNodosR(nodo.getHijoDer(), nodosEnPostOrden);
        nodosEnPostOrden.add(nodo);
      }
      return nodosEnPostOrden;
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
		System.out.println("El postorden es "+a.postOrdenNodos());
		String mensaje = "...- .- -- --- ...  .-  ... .- -.-. .- .-.  -.-. .. -. -.-. ---" ;
		System.out.println("De morse a normal= "+a.decodificar(mensaje));
		System.out.println("V de normal a morse= "+a.codificar("V"));
		System.out.println("De normal a morse= "+a.codificar("VAMOS A SACAR CINCO"));
		System.out.println();
	
		
		

	}

}
