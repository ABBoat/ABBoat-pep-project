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
        && newMessage.getPosted_by() >= 0) 
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

    //Message is stored pre deletion and returned to response post deletion
    public Message deleteMessage(int id) {
        Message intendedMessage = getMessage(id);
        messageDAO.deleteMessageById(id);
        return intendedMessage;
    }

    //Check conditions. Does the message we are looking to update exist?
    //Is the  new message blank? Is the new message no more than
    //255 charcaters?
    public Message updateMessage(int id, Message message) {
        Message targetMessage = messageDAO.getMessageById(id);

        boolean isValid = targetMessage != null 
        && !message.getMessage_text().isBlank() 
        && message.getMessage_text().length() <= 255;

        if(!isValid) {
        return null;
        }

        return messageDAO.updateMessageById(id, message);

        /* for the update message service class method i got some advice
         * from  a fellow cohrot. This is what we came up with. I feel like
         * this is checking the target ,essage constraints, THEN updating 
         * said message no? Please correct me if i'm wrong.
         */
    }
}