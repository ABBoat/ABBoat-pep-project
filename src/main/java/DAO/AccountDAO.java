package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;

public class AccountDAO {

    //Check database for username existence before insertion
    public boolean doesAccountExist(String username) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            //SQL logic
            String sql = "SELECT * FROM account WHERE username = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //PreparedStatement setters
            ps.setString(1, username);

            //SQL execution and result, *If a matching entry exists, return 'true'*
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } 
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    //Create and insert account into Account table. account_id is auto generated 
    public Account insertAccount(Account newAccount) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            //SQL logic
            String sql = "INSERT INTO account (username, password) VALUES (?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //PreparedStatement setters
            ps.setString(1, newAccount.getUsername());
            ps.setString(2, newAccount.getPassword());
            
            //SQL execution and result, auto generated account_id is returned
            //*If a new account (id) is generated, return along w/ the user and pass,
            //insert into database*
            ps.executeUpdate();
            ResultSet pkeyrs = ps.getGeneratedKeys();
            if(pkeyrs.next()) {
                int generated_account_id = (int) pkeyrs.getLong(1);
                return new Account(generated_account_id, 
                newAccount.getUsername(), newAccount.getPassword());
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Login into account, return info from database w/ generated id
    public Account loginToAccount(Account account) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            //SQL logic
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //PreparedStatement setters
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            //SQL execution and result, *While a matching entry exists, return the 
            //(account) id, user, and pass*
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Account loggedAccount = new Account(rs.getInt("account_id"),
                    rs.getString("username"), 
                    rs.getString("password"));
                return loggedAccount;
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
