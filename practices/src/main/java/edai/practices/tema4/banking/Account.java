package edai.practices.tema4.banking;

import java.util.UUID;

public class Account {

    private final UUID id;
    private double balance;

    public Account(UUID id, double initialBalance){
        this.id = id;
        this.balance = initialBalance;
    }

    public void transaction(double amount){
        if(this.balance+amount < 0){
            throw new InsufficienteBalanceException(this.balance, amount);
        }
        this.balance += amount;
    }

    public UUID getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }
}
