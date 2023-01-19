package com.easychat.core.mapper;

import com.easychat.core.Controller.dto.ChannelDto;
import com.easychat.core.entity.Channel;
import com.easychat.core.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ChannelMapper {

    public ChannelDto mapChannelToChannelDto(Channel channel){
        ChannelDto channelDto = new ChannelDto();
        channelDto.setId(channel.getId());
        channelDto.setDescription(channel.getDescription());
        channelDto.setName(channel.getName());
        channelDto.setOwnerId(channel.getOwner().getId());
        channelDto.setCreated_at(channel.getCreated_at());
        channelDto.setUpdated_at(channel.getUpdated_at());

        return channelDto;
    }

    public Channel mapChannelDtoToChannel(ChannelDto channelDto, User owner){
        Channel channel = new Channel();
        channel.setId(channelDto.getId());
        channel.setName(channelDto.getName());
        channel.setDescription(channelDto.getDescription());
        channel.setOwner(owner);

        return channel;
    }
}
