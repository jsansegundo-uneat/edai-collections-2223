package edai.practices.tema4.banking;

import edai.collections.Queue;

public class TransactionProcessor {

    final Queue<AtmTransaction> transactionQueue;
    final Bank bank;

    public TransactionProcessor(Bank bank,  Queue<AtmTransaction> transactions){
        this.bank = bank;
        this.transactionQueue = transactions;
    }

    public void processOne(){
        if(transactionQueue.isEmpty()) return;
        AtmTransaction transaction = transactionQueue.dequeue();

        try{
            // Opera con la cuenta
            bank.getCash(transaction.getAccount(), transaction.getAmount());
            transaction.commit(); // ... y valida la transacción
        }catch (AccountNotFoundException ex){
            throw ex;
        }catch (InsufficienteBalanceException ex){
            transaction.reject(); // Si no hay fondos sufientes,
            // Rechaza la transacción
        }
    }

    public int size() {
        return transactionQueue.size();
    }
}
