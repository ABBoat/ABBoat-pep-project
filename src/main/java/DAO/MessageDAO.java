package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    
    //Create and insert message into Message table. message_id is auto generated 
    public Message createMessage(Message message) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            
            //SQL execution and result, auto generated account_id is returned
            ps.executeUpdate();
            ResultSet pkeyrs = ps.getGeneratedKeys();
            if(pkeyrs.next()) {
                int generated_message_id = (int) pkeyrs.getLong(1);
                return new Message(generated_message_id, 
                message.getPosted_by(), message.getMessage_text(), 
                message.getTime_posted_epoch());
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
