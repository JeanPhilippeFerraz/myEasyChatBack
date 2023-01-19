package com.easychat.core.Controller;

import com.easychat.core.Controller.dto.MessageDto;
import com.easychat.core.entity.Channel;
import com.easychat.core.entity.Message;
import com.easychat.core.entity.User;
import com.easychat.core.mapper.MessageMapper;
import com.easychat.core.service.IChannelService;
import com.easychat.core.service.IMessageService;
import com.easychat.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/easychat/message")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IChannelService channelService;

    @Autowired
    MessageMapper mapper;

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto){

        try {
            User user = userService.getUserById(messageDto.getUserId());
            Channel channel = channelService.getChannelById(messageDto.getChannelId());
            Message message = mapper.mapMessageDtoToMessage(messageDto, user, channel);
            return ResponseEntity.ok(mapper.mapMessageToMessageDto(messageService.createMessage(message)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<MessageDto>> getAllMessages(){

        return ResponseEntity.ok(messageService.getAllMessage().stream()
                .map(mapper::mapMessageToMessageDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping(value="/{channelId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<MessageDto>> getAllMessages(@PathVariable Integer channelId){

        return ResponseEntity.ok(messageService.getMessagesByChannel(channelId).stream()
                .map(mapper::mapMessageToMessageDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "messageId/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Integer id) {
        try {
            Message message = messageService.getMessageById(id);
            MessageDto messageDto = mapper.mapMessageToMessageDto(message);
            return ResponseEntity.ok(messageDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MessageDto> updateMessage(@RequestBody MessageDto messageDto){
        try {
            Message message = mapper.mapMessageDtoToMessage(messageDto, null, null);
            Message originalMessage = messageService.getMessageById(message.getId());
            if (messageDto.getUserId() == originalMessage.getUser().getId()){
                message.setUser(originalMessage.getUser());
                message.setChannel(originalMessage.getChannel());
                message.setCreatedAt(originalMessage.getCreatedAt());
                message = messageService.updateMessage(message);
                return ResponseEntity.ok(mapper.mapMessageToMessageDto(message));
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMessage(@RequestBody MessageDto messageDto){
        try {
            Message message = mapper.mapMessageDtoToMessage(messageDto, null, null);
            Message originalMessage = messageService.getMessageById(message.getId());
            if (messageDto.getUserId() == originalMessage.getUser().getId()){
                messageService.deleteMessage(message);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
