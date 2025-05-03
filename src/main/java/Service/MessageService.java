package Service;

import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;

import static org.mockito.Mockito.inOrder;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;
    public AccountDAO accountDAO;

    //No-args constructor creates MessageDAO
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    //Parameter constructor for when MessageDAO is provided
    public MessageService (MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    //Persist message to database, check conditions. Is the message blank?
    //Is the message no more than 255 charcaters? Is it attached to an existing
    //user?
    public Message addMessage(Message newMessage) {
        if(!newMessage.getMessage_text().isEmpty() 
        && newMessage.getMessage_text().length() <= 255) 
        {
            return messageDAO.createMessage(newMessage);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public Message deleteMessage(int id) {
        Message intendedMessage = getMessageById(id);
        messageDAO.deleteMessageById(id);
        return intendedMessage;
    }
}
