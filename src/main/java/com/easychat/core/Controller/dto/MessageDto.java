package com.easychat.core.Controller.dto;

import com.easychat.core.entity.Channel;
import com.easychat.core.entity.User;
import lombok.Data;

import java.util.Date;


@Data
public class MessageDto {

    private Integer id;
    private String text;
    private Integer channelId;
    private Integer userId;
    private Date createdAt;
    private Date updatedAt;

    public MessageDto() {
    }

    public MessageDto(Integer id, String text, Integer channelId, Integer userId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.text = text;
        this.channelId = channelId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
