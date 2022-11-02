package edai.practices.tema4.supermarket;

import edai.collections.List;
import edai.collections.Node;
import edai.collections.Queue;

public class SingleRowManager {

    private final Queue<Integer> row = new Queue<>();
    private final List<CheckoutPoint> checkoutPointList = new List<>();
    private int threshold = 10;
    private RowManagerEventListerner listener;

    public SingleRowManager(){
        // Inicialmente debe tener una caja abierta
        checkoutPointList.insert( new CheckoutPoint(), -1 );
    }

    public CheckoutPoint[] getCheckoutPoints() {
        Object[] temp = checkoutPointList.listData();

        // Debemos devolver un array de CheckoutPoint, no de Object's.
        // Por eso, creamos uno del mismo tamaño que 'temp' y asignamos
        // sus valores a este.
        CheckoutPoint[] output = new CheckoutPoint[temp.length];
        for(int i = 0; i < temp.length; ++i){
            output[i] = (CheckoutPoint)temp[i];
        }

        return output;
    }

    public Queue<Integer> getRow() {
        // Lo ideal sería crear otra instancia de cola
        // para no dar la posibilidad de alterar la
        // que usamos como estado interno.
        return row;
    }

    public void addClient(int clientId) {
        row.enqueue(clientId);

        // Despues de añadir, pruebo a asignar el primer
        // cliente a la primera caja libre.
        trySetClientToCheckoutPoint();

        onRowChanged();
    }

    private void trySetClientToCheckoutPoint() {
        if(row.isEmpty()) return;

        // Obtengo el primer cliente en cola. No lo extraigo.
        Integer clientId = row.head();

        CheckoutPoint firstEmtpyPoint = findFreeCheckoutPoint();
        if (firstEmtpyPoint != null){
            firstEmtpyPoint.setCurrentClient( clientId );

            // Ahora que sí lo he podido asignar a una caja,
            // lo debo extraer de la cola.
            row.dequeue();
        }
    }

    private void onRowChanged() {
        // Si la cola tiene un tamaño igual o mayor al limite
        if(row.size() >= threshold){

            //...notificamos que necesitamos mas cajas
            // siempre que haya "alguien" escuchando.
            if(listener != null)
                this.listener.needMoreCheckoutPoints();
        }
    }

    private CheckoutPoint findFreeCheckoutPoint() {
        Node<CheckoutPoint> current = checkoutPointList.getFirst();

        // Itero por los elementos de la lista...
        while(current != null){
            // ... la primera caja que este libre, la devolvemos
            if(current.getData().isFree()) return current.getData();
            current = current.getNext();
        }

        // No hay cajas libres
        return null;
    }

    public void setRowThreshold(int value) {
        threshold = value;
    }

    public void setEventListener(RowManagerEventListerner listener){
        this.listener = listener;
    }

    public void addCheckoutPoint() {
        checkoutPointList.insert( new CheckoutPoint(), -1 );
        // Despues de añadir una caja nueva, intento asignarle un cliente.
        trySetClientToCheckoutPoint();
    }

    public void checkoutCompleted(CheckoutPoint checkoutPoint) {
        checkoutPoint.complete();

        // Despues de completar una caja y estar libre,
        // intento asignarle un nuevo cliente.
        trySetClientToCheckoutPoint();
    }
}
