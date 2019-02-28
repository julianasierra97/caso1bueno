package src;

import java.util.ArrayList;
import java.util.Queue;

public class Buffer {

	private int capacidad;
	private ArrayList<Mensaje> cola;
	private Object em;
	private Object em2;
	private Object em3;
	private Boolean lleno;
	private Boolean vacio;
	private static Boolean acabo;


	public Buffer(int capacidad) {
		this.capacidad = capacidad;
		cola = new ArrayList<>();
		em = new Object();
		em2 = new Object();
		lleno = false;
		vacio = true;
		acabo = false;
	}

	public  synchronized void depositarMensaje(Mensaje mensaje) {

		if (lleno) {
			System.out.println("entro lleno");
			try {

				System.out.println("espero por lleno");
				this.wait();
				System.out.println("Continuo por que hay espacio"+ lleno);

				System.out.println(capacidad + "Lleno" + cola.size());
				synchronized (cola) {
					cola.add(mensaje);
					System.out.println("Agrego mensaje en lleno");
					if (cola.size() == capacidad) {

						lleno = true;

					}

				}
				synchronized (vacio) {
					vacio = false;
				}


				synchronized (mensaje) {
					System.out.println("Espero sobre el mensaje en lleno");
					mensaje.wait();
				}
				System.out.println("Termine bien de agregar mensaje sin espacio");


			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {

			System.out.println("Entro espacio");

			synchronized (cola) {
				System.out.println("Agrego mensaje con espacio");
				cola.add(mensaje);

				synchronized (vacio) {


					vacio = false;
				}

				if (cola.size() > capacidad) {
					synchronized (lleno) {
						System.out.println("Ahora esta lleno"+ lleno);

						lleno = true;
					}
				}
				System.out.println("Termine bien de agregar mensaje con espacio");


			}


			try {

				synchronized (mensaje) {
					mensaje.wait();
					System.out.println("Me desperto el mensaje");
				}



			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



	public void retirarMensaje() {


		while (vacio) {
			//System.out.println("loop");
			Thread.yield();
			System.out.println(Main.contadorClientes);
			if(Main.contadorClientes==10)
            	break;
		}


		synchronized (cola) {
			if(!vacio){
				Mensaje mensaje = cola.remove(0);

				System.out.println("removio el mensaje, no esta vacio "+vacio);

				mensaje.utilizar();
				synchronized (mensaje) {
					mensaje.notify();
					System.out.println("Desperto porque saco mensaje");

				}
			}
			synchronized (vacio) {
				System.out.println("vacio");

				if (cola.size() == 0) {
					System.out.println("La cola quedo vacia"+vacio);

					vacio = true;
				}
			}

		}
	}
}








