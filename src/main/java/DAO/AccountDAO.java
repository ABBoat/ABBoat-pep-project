package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    //Insert account into Account table. account_id is auto generated 
    public Account insertAccount(Account account) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            //SQL logic
            String sql = "INSERT INTO account (username, password) VALUES (?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //PreparedStatement setters
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            //SQL execution, result, and method rules
            ps.executeUpdate();
            ResultSet pkeyResultSet = ps.getGeneratedKeys();
        }
        catch(SQLException e) {

        }
    }

}
