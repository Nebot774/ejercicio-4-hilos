public class Consumidor implements Runnable {
    private  int[] array;//array compartido por productor y consumidor
    private Object bloqueo;//objeto
    private int indice=0;

    public Consumidor(int[] array, Object bloqueo) {
        this.array = array;
        this.bloqueo = bloqueo;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
           synchronized (bloqueo) {
               //esperemos hasta que el array este vacio
               while (indice == 0) {
                   try {
                       bloqueo.wait();//esperamos a que el consumidor libere espacio
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }

               }
               // Leer y consumir el elemento del array
               int elementoConsumido = array[indice - 1];
               System.out.println("Consumido: " + elementoConsumido);
               array[indice - 1] = 0; // Marcar el elemento como consumido (0)
               indice--;
// Notificar al productor que hay espacio disponible
               bloqueo.notify();


           }
        }

    }
}
