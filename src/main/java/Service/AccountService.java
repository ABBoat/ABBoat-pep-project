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
    public Account addAccount(Account newAccount) {
        if(!accountDAO.doesAccountExist(newAccount.getUsername())
            && newAccount.getUsername().trim().length() > 0
            && newAccount.getPassword().length() >= 4) 
        {
            return accountDAO.insertAccount(newAccount);
        }
        return null;
    }

    //Check conditions for login. Do username and password match an existing account?
    public Account validateAccount(Account account) {
        if(accountDAO.doesAccountExist(account.getUsername()))
        {
            return accountDAO.loginToAccount(account.getUsername(), account.getPassword());
        }
        return null;
    }
}
