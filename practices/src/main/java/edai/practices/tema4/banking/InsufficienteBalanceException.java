package edai.practices.tema4.banking;

public class InsufficienteBalanceException extends RuntimeException {
    private final double balance;
    private final double amount;

    public InsufficienteBalanceException(double balance, double amount) {
        this.balance = balance;
        this.amount = amount;
    }

    @Override
    public String
    toString() {
        return "InsufficienteBalanceException{" +
                "balance=" + balance +
                ", amount=" + amount +
                '}';
    }
}
