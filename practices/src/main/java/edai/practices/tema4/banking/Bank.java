package edai.practices.tema4.banking;

import java.util.ArrayList;
import java.util.UUID;

public class Bank {

    ArrayList<Account> accounts = new ArrayList<>();

    public void getCash(UUID accountId, double amount){
        var account = getAccount(accountId);

        account.transaction(amount);
    }

    public double queryCash(UUID accountId){
        return getAccount(accountId).getBalance();
    }

    private Account getAccount(UUID accountId) {
        for(Account account : this.accounts){
            if(account.getId().equals(accountId))
                return account;
        }

        throw new AccountNotFoundException();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
