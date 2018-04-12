package ua.com.owu.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.MessageDAO;
import ua.com.owu.entity.Message;
import ua.com.owu.service.MessageServise;


import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageServise {

    @Autowired
    private MessageDAO messageDAO;

    public static final Logger LOGGER = Logger.getLogger(MessageServiceImpl.class);

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public Message findOne(int id) {
        return messageDAO.findOne(id);
    }

    @Override
    public List<Message> findAll() {
        return messageDAO.findAll();
    }

    public void save(Message message) {
        if (message != null && !message.getMessage().isEmpty()) {

            messageDAO.save(message);
            LOGGER.info("object " + message + " was saved");
        }
    }

    @Override
    public List<Message> findMessageOfCurrentUser(int userID) {
        return messageDAO.messageWithUser(userID);
    }


    @Override
    public void delete(int id) {
        messageDAO.delete(id);
    }
}
