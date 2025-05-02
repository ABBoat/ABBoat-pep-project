package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    //No-args constructor creates MessageDAO
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    //Parameter constructor for when AccountDAO is provided
    public MessageService (MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    //Persist message to database, check conditions. Is the message blank?
    //Is the message no more than 255 charcaters? Is it attached to an existing
    //user?
    public Message addMessage(Message newMessage) {
        if(!newMessage.getMessage_text().isEmpty()) {
            return messageDAO.createMessage(newMessage);
        }
        return null;
    }
}
