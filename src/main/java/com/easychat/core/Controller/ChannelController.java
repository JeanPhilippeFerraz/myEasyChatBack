package com.easychat.core.Controller;

import com.easychat.core.Controller.dto.ChannelDto;
import com.easychat.core.entity.Channel;
import com.easychat.core.entity.User;
import com.easychat.core.mapper.ChannelMapper;
import com.easychat.core.service.IChannelService;
import com.easychat.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/easychat/channel")
@CrossOrigin(origins = "http://localhost:4200")
public class ChannelController {

    @Autowired
    private IChannelService channelService;

    @Value("${generalChannelId}")
    private Integer generalChannelId;

    @Autowired
    private IUserService userService;

    @Autowired
    protected ChannelMapper mapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ChannelDto>> getAllChannels() {

        return ResponseEntity.ok( channelService.getAllChannels().stream()
                                  .map(mapper::mapChannelToChannelDto)
                                  .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ChannelDto> getChannelById(@PathVariable Integer id) {
        try{
            ChannelDto channelDto = mapper.mapChannelToChannelDto(channelService.getChannelById(id));
            return ResponseEntity.ok(channelDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ChannelDto> createChannel(@RequestBody ChannelDto channelDto) {

        try{
            User owner = userService.getUserById(channelDto.getOwnerId());
            Channel createdChannel = channelService.createChannel(mapper.mapChannelDtoToChannel(channelDto, owner));
            return ResponseEntity.ok(mapper.mapChannelToChannelDto(createdChannel));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChannel(@RequestBody ChannelDto channelDto) {
        try {
            Channel channelToDelete = channelService.getChannelById(channelDto.getId());

            if (channelDto.getOwnerId() == channelToDelete.getOwner().getId()) {
                Channel channel = mapper.mapChannelDtoToChannel(channelDto, null);
                if (channel.getId() != generalChannelId) {
                    channelService.deleteChannel(channel);
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.unprocessableEntity().build();
                }
            }else{
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ChannelDto> updateChannel(@RequestBody ChannelDto channelDto) {
        try {
            Channel channelToUpdate = channelService.getChannelById(channelDto.getId());
            if(channelToUpdate.getOwner().getId() == channelDto.getOwnerId() && channelToUpdate.getId() != generalChannelId) {
                Channel channelUpdated = mapper.mapChannelDtoToChannel(channelDto, channelToUpdate.getOwner());
                channelUpdated.setCreated_at(channelToUpdate.getCreated_at());
                return ResponseEntity.ok(mapper.mapChannelToChannelDto(channelService.updateChannel(channelUpdated)));
            }else{
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
