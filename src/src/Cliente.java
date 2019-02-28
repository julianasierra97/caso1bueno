package src;

public class Cliente extends Thread {


    private int numConsultas;

    private Buffer buffer;


    public Cliente(int numConsultas, Buffer buffer) {
        this.numConsultas = numConsultas;
        this.buffer=buffer;

    }
    public void run(){
        System.out.println("Comenzo cliente");
        for (int i = 0; i < numConsultas; i++) {

            buffer.depositarMensaje(new Mensaje(1));
        }
        System.out.println("Acabe :)");
    }
}
