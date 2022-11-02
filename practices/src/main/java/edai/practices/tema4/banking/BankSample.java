package edai.practices.tema4.banking;

import edai.collections.Queue;

import java.util.UUID;

public class BankSample implements Runnable {

    @Override
    public void run(){

        // Creamos una cuenta y la añadirmos al banco
        Account account = new Account(UUID.randomUUID(), 1000.0d);
        Bank bank = new Bank();
        bank.addAccount(account);

        // Creamos una cola de transacciones.
        // Esta cola se compartirá tanto con los cajeros como
        // con la unidad de procesamiento.
        final Queue<AtmTransaction> transactions = new Queue<>();

        // Unidad de procesado de transacciones
        TransactionProcessor processor = new TransactionProcessor(bank, transactions);

        // Hilo donde se ejecuta la unidad de procesado
        Thread tprocessor = TransactionProcessorRunner.start(processor, account);

        // Hilo donde funciona el cajero 'New York'
        // Operaciones de ingreso de 2€ a la cuenta
        Thread t1 = AtmThreadRunner.start(
                new Atm("New York"),
                transactions,
                account,
                2);

        // HIlo donde funciona el cajero 'Beijing'
        // Operaciones de retirada de 12€ a la cuenta
        Thread t2 = AtmThreadRunner.start(
                new Atm("Beijing"),
                transactions,
                account,
                -12);

        try {
            // El metodo 'join' de la clase Thread, "congela" este hilo
            // hasta que el hilo correspondiente a la llamada termina.
            t1.join();
            t2.join();
            tprocessor.join();
        }catch (InterruptedException ex){}
    }

    public static void main(String[] args){
        new BankSample().run();
    }

}
