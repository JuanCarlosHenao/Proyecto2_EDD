package ClasesArbol;

import java.util.ArrayList;

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





	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NodoB<Integer> n1= new NodoB<Integer>(10);
		NodoB<Integer> n2= new NodoB<Integer>(7);
		NodoB<Integer> n3= new NodoB<Integer>(15);
		NodoB<Integer> n4= new NodoB<Integer>(5);
		NodoB<Integer> n5= new NodoB<Integer>(9);
		NodoB<Integer> n6= new NodoB<Integer>(12);
		n3.setHijoIzq(n6);
		n2.setHijoIzq(n4);
		n2.setHijoDer(n5);
		n1.setHijoIzq(n2);
		n1.setHijoDer(n3);
		ArbolB<Integer> a= new ArbolB<Integer>(n1);
		a.preOrden();
		a.postOrden();
		a.inOrden();
		a.preordenList();
		System.out.println(a.preordenList())
		;
		

	}

}
