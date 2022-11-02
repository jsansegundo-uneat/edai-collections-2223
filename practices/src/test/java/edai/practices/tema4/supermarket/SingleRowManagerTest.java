package edai.practices.tema4.supermarket;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleRowManagerTest {

    static final Random random = new Random(LocalTime.now().getNano());

    SingleRowManager rowManager;

    private Integer createIdClient() {
        return random.nextInt();
    }

    @BeforeEach
    void setup(){
        rowManager = new SingleRowManager();
    }

    @Test
    void freshSingleRowManager(){

        assertEquals(1, rowManager.getCheckoutPoints().length); // array
        assertTrue(rowManager.getRow().isEmpty());
    }

    @Test
    void addOneClient(){
        Integer clientId = createIdClient();
        rowManager.addClient(clientId);

        CheckoutPoint checkout = rowManager.getCheckoutPoints()[0];

        assertEquals(clientId, checkout.getCurrentClient());
        assertTrue(rowManager.getRow().isEmpty());
    }

    @Test
    void addTwoClients(){
        var clients = new Integer[]{ createIdClient(), createIdClient() };

        Integer expectedClientOnCheckout = clients[0];
        Integer expectedClientOnRow = clients[1];

        rowManager.addClient(expectedClientOnCheckout);
        rowManager.addClient(expectedClientOnRow);

        CheckoutPoint checkoutPoint = rowManager.getCheckoutPoints()[0];

        assertEquals(expectedClientOnCheckout, checkoutPoint.getCurrentClient());
        assertArrayEquals(new Integer[]{ expectedClientOnRow }, rowManager.getRow().listData());
    }

    @Test
    void testNeedMoreCheckoutNotification(){
        AtomicBoolean eventRaised = new AtomicBoolean(false);
        rowManager.setRowThreshold(4);
        rowManager.setEventListener(() -> eventRaised.set(true));

        // Enters 5 new clients
        Integer[] clients = new Integer[]{
                createIdClient(),
                createIdClient(),
                createIdClient(),
                createIdClient(),
                createIdClient(),
        };

        for(Integer client: clients){
            rowManager.addClient(client);
        }

        assertTrue(eventRaised.get());
    }

    @Test
    void addMoreCheckoutPointsToDecreaseRow(){
        Integer[] clients = new Integer[]{
                createIdClient(),
                createIdClient(),
                createIdClient(),
                createIdClient(),
                createIdClient(),
        };

        rowManager.setRowThreshold(clients.length-1);
        rowManager.setEventListener(() -> rowManager.addCheckoutPoint());

        for(Integer client: clients){
            rowManager.addClient(client);
        }

        CheckoutPoint secondCheckoutPoint = rowManager.getCheckoutPoints()[1];

        Integer expectedSecondClientOrCheckout = clients[1];

        assertEquals(expectedSecondClientOrCheckout, secondCheckoutPoint.getCurrentClient());
        assertEquals(3, rowManager.getRow().size());

    }

    @Test
    void checkoutProcessCompletedAndSetNext(){
        Integer[] clients = new Integer[]{
                createIdClient(),
                createIdClient(),
                createIdClient(),
        };

        for(Integer client: clients){
            rowManager.addClient(client);
        }

        CheckoutPoint checkoutPoint = rowManager.getCheckoutPoints()[0];

        rowManager.checkoutCompleted(checkoutPoint);

        Integer expectedNextClient = clients[1];
        Integer expectedClientOnRow = clients[2];

        assertEquals(expectedNextClient, checkoutPoint.getCurrentClient());
        assertArrayEquals(new Integer[]{ expectedClientOnRow }, rowManager.getRow().listData());

    }
}
