package com.easychat.core.service;

import com.easychat.core.entity.Channel;

import java.util.List;

public interface IChannelService {

    List<Channel> getAllChannels();
    Channel getChannelById(Integer id) throws Exception;
    Channel createChannel(Channel channel);
    Channel updateChannel(Channel channel) throws Exception;
    void deleteChannel(Channel channel) throws Exception;
}
