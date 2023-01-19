package com.easychat.core.service;

import com.easychat.core.entity.Message;

import java.util.List;

public interface IMessageService {

    public Message createMessage(Message message);

    public List<Message> getAllMessage();

    public List<Message> getMessagesByChannel(Integer channelId);

    public Message getMessageById(Integer id) throws Exception;

    public Message updateMessage(Message message) throws Exception;

    public void deleteMessage(Message message) throws Exception;

}
