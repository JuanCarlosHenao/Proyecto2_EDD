package ClasesArbol;

import java.util.ArrayList;
import java.util.Queue;

public class ABB <E extends Comparable <E> > extends ArbolB <E>  {

	public ABB(NodoB<E> raiz) {
		super(raiz);
		// TODO Auto-generated constructor stub
	}
	
	public ABB() {
		raiz=null;
	}
	
	public NodoB <E> buscar (E info) {
		return buscarR (info,raiz);
	}

	public NodoB<E> buscarR(E info , NodoB<E> raiz) {
		if (raiz==null) {
			System.out.println("el nodo no se encunetra en el arbol ");
		} else {
			if (info.compareTo(raiz.getLlave())<0)
				return (buscarR(info,raiz.getHijoIzq()));
			if (info.compareTo(raiz.getLlave())>0)
				return (buscarR(info,raiz.getHijoDer()));
		
		}
		return raiz;
		
		
	}
	
	
	public void insertNodo (NodoB <E> n ) {
		raiz=insertNodoR (n,raiz);
		
		
	}

	
	
	
	
	private NodoB<E> insertNodoR(NodoB<E> n, NodoB<E> raiz) {
		
		// TODO Auto-generated method stub
		
		if (raiz==null) {
			raiz=n;
		}else {
			if (n.getLlave().compareTo(raiz.getLlave())<0)
				raiz.setHijoIzq(insertNodoR(n,raiz.getHijoIzq()));
			if (n.getLlave().compareTo(raiz.getLlave())>0)
				raiz.setHijoDer(insertNodoR(n,raiz.getHijoDer()));
			if (n.getLlave().compareTo(raiz.getLlave())==0)	
				System.out.println("el nodo esta repetido");
			
				
		}
		return raiz;
	}

	public NodoB <E> buscarAntecesor (NodoB <E> raiz){
		if (raiz.getHijoIzq()==null)
			System.out.println("np hay arbol ");
	return buscarMax (raiz.getHijoIzq());
		
		
	}
	
	
	private NodoB<E> buscarMax(NodoB<E> hijoIzq) {
		// TODO Auto-generated method stub
		if (hijoIzq.getHijoDer()==null)
			return hijoIzq;
		else 
			return buscarMax (hijoIzq  .getHijoDer());
		
	}

	
	
	public void deleteNodo (E info ) {
		deleteNodoR (info,raiz);
		
	}
	
	public NodoB <E> deleteNodoR(E info, NodoB <E> raiz) {
		
		if (raiz==null)
			System.out.println("no se encuentra el nodo ");
		else {
			if (info.compareTo (raiz.getLlave())<0) 
				raiz.setHijoIzq(deleteNodoR(info,raiz.getHijoIzq()));
			else
				if (info.compareTo(raiz.getLlave())>0)
					raiz.setHijoDer(deleteNodoR(info,raiz.getHijoDer()));
				else {
					// caso 3
					if (raiz.getHijoIzq()!= null && raiz.getHijoDer()!=null) {
						NodoB <E> antecesor = buscarAntecesor (raiz);
						raiz.setLlave(antecesor.getLlave());
						raiz.setHijoIzq(deleteNodoR(antecesor.getLlave(),raiz.getHijoIzq()));
					}else {
						// caso 2 o 1 
						if (raiz.getHijoDer()!=null) {
							raiz=raiz.getHijoDer();
							
						}else 
							raiz=raiz.getHijoIzq();
					}
				}
		}
		return raiz;	
	}
	
	
//	public int contarHojas() {
//		return contarHojasR (); 
//		
//	}
//	
//	public int contarHojasR () {
//		
//		
//		
//		return null;
//		
//	} 
	

	
	
//	
//	public void altura () {
//		
//		
//	}
//	
//	
//	recorrido a lo ancho --------------------------------------------------------
	
//	public void amplitud(NodoB a) //SE RECIBE LA RAIZ DEL ARBOL
//	{
//	Queue cola, colaAux; //DEFINICIÓN DE 2 VARIABLES DE TIPO COLA
//	NodoB aux; //DEFINICIÓN AUX DE TIPO NODOARBOL
//
//	if (a != null) //SI EL ÁRBOL CONTIENE NODOS...
//	{
//	cola=new Queue(); //SE INSTANCIA EL OBJETO COLA
//	colaAux=new Queue(); //SE INSTANCIA EL OBJETO COLAAUX
//	cola.push(a); //SE INSERTA EL NODOARBOL "A" (RAIZ) COMO PRIMER NODO EN LA COLA
//	while (cola.colavacia()!=1) //MIENTRAS HAYAN ELEMENTOS EN LA COLA...
//	{
//	colaAux.push(aux=cola.pop()); /*EL ELEMENTO EXTRAIDO DE LA COLA PRINCIPAL ES ASIGNADO 
//	A AUX Y A SU VEZ INSERTADO EN LA COLA AUXILIAR*/
//	if (aux.izq != null) //SI EL HIJO IZQUIERDO DEL NODO ACTUAL EXISTE
//	{
//	cola.push(aux.izq); //SE INSERTA ESE HIJO COMO ELEMENTO SIGUIENTE EN LA COLA
//	}
//	if (aux.der!= null) //SI EL HIJO DERECHO DEL NODO ACTUAL EXISTE
//	{
//	cola.push(aux.der); //SE INSERTA ESE HIJO COMO ELEMENTO SIGUIENTE EN LA COLA
//	}
//	}
//	colaAux.print(); //POR ÚLTIMO SE IMPRIME LA COLA AUXILIAR
//	}
//	}

	
	public void recorridoAncho() {
		if(raiz != null) {
			Queue<NodoB<E>> cola = (Queue<NodoB<E>>) new ArrayList<NodoB<E>>(); 
			cola.add(raiz);
			while(!cola.isEmpty()) {
			NodoB<E> n = cola.peek();
			System.out.println( n.getLlave()+" ");
			if(n.hijoIzq!=null)
				cola.add(n.hijoIzq);
			if(n.hijoDer!=null)
				cola.add(n.hijoDer);
			cola.poll();
			
			}
			
		}
	}

	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NodoB<Integer> n1= new NodoB<Integer>(1);
		NodoB<Integer> n2= new NodoB<Integer>(2);
		NodoB<Integer> n3= new NodoB<Integer>(3);
		NodoB<Integer> n4= new NodoB<Integer>(4);
		NodoB<Integer> n5= new NodoB<Integer>(5);
		NodoB<Integer> n6= new NodoB<Integer>(6);
		NodoB<Integer> n7= new NodoB<Integer>(7);
		NodoB<Integer> n8= new NodoB<Integer>(8);
		NodoB<Integer> n9= new NodoB<Integer>(9);
		NodoB<Integer> n10= new NodoB<Integer>(10);
		ABB<Integer> a = new ABB <Integer>(n5);
		a.insertNodo(n2);
		a.insertNodo(n3);
		a.insertNodo(n1);
		a.insertNodo(n9);
		a.insertNodo(n7);
		a.insertNodo(n8);
		a.insertNodo(n6);
		a.insertNodo(n4);
		a.insertNodo(n10);
//		System.out.println(a.buscar(2));
		
		
		a.preOrden();
		
		
		System.out.println(a.buscar(6));
		
		
		a.deleteNodo(9);
		
		
		a.preOrden();

	}

}
