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
    
    //Parameter constructor for when BookDao is provided
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
}
