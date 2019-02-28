package src;

public class Semaphore {


    private int contador;



    public Semaphore(int contador){

        this.contador=contador;

    }

    public synchronized void   p() {
        contador--;
        if(contador<0){
            try {
                System.out.println("wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void  v() {
        contador++;
        if(contador>=0){
            notify();
        }
    }


}
