package Service;

import Model.Message;
import DAO.MessageDAO;
//import DAO.AccountDAO;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;
    //public AccountDAO accountDAO;

    //No-args constructor creates MessageDAO
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    //Parameter constructor for when MessageDAO is provided
    public MessageService (MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    //Persist message to database, check conditions. Is the message blank?
    //Is the message no more than 255 charcaters? Is it attached to an existing user?
    public Message addMessage(Message newMessage) {
        if(!newMessage.getMessage_text().isEmpty() 
        && newMessage.getMessage_text().length() <= 255
        /*&& newMessage.getPosted_by() != null*/) 
        {
            return messageDAO.createMessage(newMessage);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public List<Message> getAllByAccount(int id) {
        return messageDAO.getAllByAccount(id);
    }

    public Message getMessage(int id) {
        return messageDAO.getMessageById(id);
    }

    public Message deleteMessage(int id) {
        Message intendedMessage = getMessage(id);
        messageDAO.deleteMessageById(id);
        return intendedMessage;
    }

    public Message updateMessage(int id, Message message) {
        Message targetMessage = messageDAO.getMessageById(id);

        boolean isValid = targetMessage != null 
        && !message.getMessage_text().isBlank() 
        && message.getMessage_text().length() <= 255;

        if(!isValid) {
        return null;
        }

        return messageDAO.updateMessageById(id, message);
    }
}