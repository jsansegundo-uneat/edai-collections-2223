package edai.practices.tema4.banking;

import java.util.UUID;
import java.util.function.Consumer;

public class AtmTransaction {

    private final Atm originAtm;
    private final UUID account;
    private final double amount;
    private final Consumer<Boolean> onCompleted;

    public AtmTransaction(Atm originAtm, UUID account, double amount, Consumer<Boolean> onCompleted){
        this.originAtm = originAtm;
        this.account = account;
        this.amount = amount;
        this.onCompleted = onCompleted;
    }

    public Atm getOriginAtm() {
        return originAtm;
    }

    public UUID getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public void commit() {
        // Al aceptar la trasacción, se llama a la
        // función callback con true
        this.onCompleted.accept(true);
    }

    public void reject() {
        // Al aceptar la trasacción, se llama a la
        // función callback con false
        this.onCompleted.accept(false);
    }
}
