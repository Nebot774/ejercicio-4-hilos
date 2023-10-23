public class Productor implements Runnable{
    private  int[] array;//array compartido por productor y consumidor
    private Object bloqueo;//objeto
    private int indice=0;

    public Productor(int[] array, Object bloqueo) {
        this.array = array;
        this.bloqueo = bloqueo;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            synchronized (bloqueo) {
                //si el array esta lleno esperamos
                while (indice == array.length) {
                    try {
                        bloqueo.wait();//esperamos a que el consumidor libere espacio
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                //generamos un elemento aleatorio y lo metemos en el array
                int nuevoElemento = (int) (Math.random() * 100);
                array[indice] = nuevoElemento;
                System.out.println("Producido:" + nuevoElemento);
                indice++;
                //notificamos al consumidor que hay un elemento listo
                bloqueo.notify();

            }
        }

    }

}
