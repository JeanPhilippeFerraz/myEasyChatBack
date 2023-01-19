package com.easychat.core.service.impl;

import com.easychat.core.entity.Message;
import com.easychat.core.repository.MessageRepository;
import com.easychat.core.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements IMessageService {

    @Autowired
    MessageRepository repository;

    @Override
    public Message createMessage(Message message) {
        return repository.save(message);
    }

    @Override
    public List<Message> getAllMessage() {
        return repository.findAll();
    }

    @Override
    public List<Message> getMessagesByChannel(Integer channelId) {
        return repository.findAllByChannelId(channelId);
    }

    @Override
    public Message getMessageById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Message updateMessage(Message message) throws Exception {

        Message messageToUpdate = repository.findById(message.getId()).orElseThrow(Exception::new);

        return repository.save(message);
    }

    @Override
    public void deleteMessage(Message message) throws Exception {
        Message messageToDelete = repository.findById(message.getId()).orElseThrow(Exception::new);
        repository.delete(messageToDelete);
    }
}
