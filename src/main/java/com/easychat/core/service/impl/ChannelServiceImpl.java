package com.easychat.core.service.impl;

import com.easychat.core.entity.Channel;
import com.easychat.core.repository.ChannelRepository;
import com.easychat.core.service.IChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChannelServiceImpl implements IChannelService {

    @Autowired
    private ChannelRepository repo;


    @Override
    public List<Channel> getAllChannels() {
        return repo.findAll();
    }

    @Override
    public Channel getChannelById(Integer id) throws Exception {
        return repo.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Channel createChannel(Channel channel) {
        return repo.save(channel);
    }

    @Override
    public Channel updateChannel(Channel channel) throws Exception {
        Channel channelToUpdate = repo.findById(channel.getId()).orElseThrow(Exception::new);
        return repo.save(channel);
    }

    @Override
    public void deleteChannel(Channel channel) throws Exception {
        repo.findById(channel.getId()).orElseThrow(Exception::new);
        repo.delete(channel);
    }
}
