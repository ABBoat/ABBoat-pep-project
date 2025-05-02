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

    //Persist message to database, check conditions. Is 
}
