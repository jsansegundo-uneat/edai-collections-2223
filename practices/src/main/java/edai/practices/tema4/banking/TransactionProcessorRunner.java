package edai.practices.tema4.banking;

public class TransactionProcessorRunner implements Runnable{

    final TransactionProcessor processor;
    final Account account;

    TransactionProcessorRunner(TransactionProcessor processor, Account account) {
        this.processor = processor;
        this.account = account;
    }

    public static Thread start(TransactionProcessor processor, Account account){
        var runner = new TransactionProcessorRunner(processor, account);
        var t = new Thread(runner);
        t.start();
        return t;
    }

    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(100);
                System.out.printf(">> Processing one. Balance %2.2f. Remaning transactions %d\n", account.getBalance(), processor.size());
                processor.processOne();
            }

        }catch (InterruptedException e) {}
    }
}
