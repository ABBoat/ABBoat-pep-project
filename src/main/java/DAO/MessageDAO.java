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
            //SQL logic
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //PreparedStatement setters
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            
            //SQL execution and result, auto generated message_id is returned
            //*If a new message (id) is generated, return along w/ the user, message 
            //content, and timestamp, insert into database*
            ps.executeUpdate();
            ResultSet pkeyrs = ps.getGeneratedKeys();
            if(pkeyrs.next()) {
                int generated_message_id = (int) pkeyrs.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), 
                message.getMessage_text(), message.getTime_posted_epoch());
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Retrieve all messages, return as list 
    public List<Message> getAllMessages() {
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //SQL logic
            String sql = "SELECT * FROM message;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //SQL execution and result, *While a matching entry exists, return the 
            //(message) id, user, message content, and timestamp,
            //add to list*
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"), 
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
    
    //Retrieve all messages by account_id, return as list
    public List<Message> getAllByAccount(int id) {
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //SQL logic
            String sql = "SELECT * FROM message WHERE posted_by = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //PreparedStatement setters
            ps.setInt(1, id);

            //SQL execution and result, *While a matching entry exists, return the 
            //(message) id, user, message content, and timestamp,
            //add to list*
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"), 
                rs.getString("message_text"),
                rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    //Retrieve message by message_id
    public Message getMessageById(int id) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            //SQL logic
            String sql = "SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //PreparedStatement setters
            ps.setInt(1, id);

            //SQL execution and result, *While a matching entry exists, return the 
            //(message) id, user, message content, and timestamp,
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message idMessage = new Message(rs.getInt("message_id"), 
                rs.getInt("posted_by"),
                rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
                return idMessage;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Delete message by message_id
    public void deleteMessageById(int id) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            //SQL logic
            String sql = "DELETE * FROM message WHERE message_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            //PreparedStatement setters
            ps.setInt(1, id);

            //SQL execution, message is deleted from database
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Update message by message_id
    public Message updateMessageById(int id, Message newMessage) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            //SQL logic
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newMessage.getMessage_text());
            ps.setInt(2, id);

            ps.executeUpdate();

        }    
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getMessageById(id);
    }
}
