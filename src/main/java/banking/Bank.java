package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;
    private ArrayList<String> listOfAccountId;


    Bank() {
        accounts = new HashMap<>();
        listOfAccountId = new ArrayList<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public ArrayList<String> getListOfAccountId() {
        return listOfAccountId;
    }

    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
        listOfAccountId.add(account.getId());
    }

    public boolean checkIdExists(String accountId) {
        return (accounts.containsKey(accountId));
    }

    public Account getAccounts(String id) {
        Account account = accounts.get(id);
        return account;
    }

    public int getIdAccountIndex(String id) {
        int idIndex = 0;
        for (int i = 0; i < listOfAccountId.size(); i++) {
            if (id.equals(listOfAccountId.get(i))) {
                idIndex = i;
            }
        }
        return idIndex;
    }

    public boolean closeAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            accounts.remove(accountId);
            int removeIndex = getIdAccountIndex(accountId);
            listOfAccountId.remove(removeIndex);
            return true;
        } else {
            return false;
        }
    }


}
