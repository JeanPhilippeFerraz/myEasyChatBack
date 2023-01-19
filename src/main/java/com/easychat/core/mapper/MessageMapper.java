package com.easychat.core.mapper;

import com.easychat.core.Controller.dto.ChannelDto;
import com.easychat.core.Controller.dto.MessageDto;
import com.easychat.core.entity.Channel;
import com.easychat.core.entity.Message;
import com.easychat.core.entity.User;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class MessageMapper {

    public MessageDto mapMessageToMessageDto(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setText(message.getText());
        messageDto.setUserId(message.getUser().getId());
        messageDto.setChannelId(message.getChannel().getId());
        messageDto.setCreatedAt(message.getCreatedAt());
        messageDto.setUpdatedAt(message.getUpdatedAt());

        return messageDto;
    }

    public Message mapMessageDtoToMessage(MessageDto messageDto, User user, Channel channel){
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setText(messageDto.getText());
        message.setUser(user);
        message.setChannel(channel);

        return message;
    }
}
