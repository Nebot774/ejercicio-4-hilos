
public class Main {
    public static void main(String[] args) {
    //array y bloqueador compartido
        int[] array=new int[10];
        Object bloqueo=new Object();

        Thread Productor=new Thread(new Productor(array,bloqueo));
        Thread Consumidor=new Thread(new Consumidor(array,bloqueo));

        Productor.start();
        Consumidor.start();

    }
}