package edai.practices.tema4.banking;

import edai.collections.Queue;

public class AtmThreadRunner implements Runnable {
    final Atm atm;
    final Queue<AtmTransaction> transactions;
    private final double transactionAmount;
    private final Account account;

    AtmThreadRunner(Atm atm, Queue<AtmTransaction> transactions, Account account, double transactionAmount) {
        this.atm = atm;
        this.transactions = transactions;
        this.account = account;
        this.transactionAmount = transactionAmount;
    }

    public static Thread start(Atm atm, Queue<AtmTransaction> transactions, Account account, double transactionAmount){
        var runner = new AtmThreadRunner(atm, transactions, account, transactionAmount);
        var t = new Thread(runner);
        t.start();
        return t;
    }

    @Override
    public void run() {
        System.out.println("ATM " + atm.toString() + " ready");

        for(int i = 0; i < 501; ++i){
            try{
                // Duerme el hilo un tiempo aleatorio entre 0 y 100 ms.
                Thread.sleep(randomInt(100));

                // Crea la transacción para la cuenta y la cantidad
                // Incluye el callback con un mensaje para saber si fue completada correctamente
                var transaction = new AtmTransaction(atm, account.getId(), transactionAmount,
                        ok -> System.out.printf("Transaction from %s completed %s\n", atm, ok));

                // Encola la transacción para ser procesada
                transactions.enqueue(transaction);
            }catch (InterruptedException e){}
        }
        System.out.println(atm.toString()+" completed");
    }

    static int randomInt(int range) {
        return (int) Math.random()*range;
    }
}
