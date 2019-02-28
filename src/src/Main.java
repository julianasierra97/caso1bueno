package src;

public class Main {


    public static void main(String[] args) {
        int numClientes= 10;
        int numServidores= 10;
        int capacidad=(int) 3;
        Buffer buff = new Buffer(capacidad);
        Servidor servidor= new Servidor(numServidores, buff);

        for (int i = 0; i <numClientes ; i++) {
            int numMensajes= 4;

            Cliente c = new Cliente(numMensajes,buff);
            c.start();
        }
    }



}
