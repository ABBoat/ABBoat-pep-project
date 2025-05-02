package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;

public class AccountService {
    public AccountDAO accountDAO;

    //No-args constructor creates AccountDAO
    public AccountService() {
        accountDAO = new AccountDAO();
    }
    
    //Parameter constructor for when AccountDAO is provided
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    //Persist account to database, check conditions. Does username already exist?
    //Any extra whitespace in username? Is password at least 4 characters?
    public Account addAccount(Account newAccount){
        if(!accountDAO.doesAccountExist(newAccount.getUsername())
            && newAccount.getUsername().trim().length() > 0
            && newAccount.getPassword().length() >= 4)
        {
            return accountDAO.insertAccount(newAccount);
        }
        return null;
    }

    public
}
