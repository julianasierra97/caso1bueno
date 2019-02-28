package src;

public class Servidor {


    private int numServidores ;
    private Buffer buff;

    public Servidor(int numServidores, Buffer buff) {
        this.numServidores = numServidores;
        this.buff=buff;
        iniciar(buff);

    }


    private void iniciar( Buffer buff){
        for (int i = 0; i <numServidores ; i++) {
            ThreadServidor t=new ThreadServidor(buff);
            t.start();
        }
    }
}
