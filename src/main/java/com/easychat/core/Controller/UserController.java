package com.easychat.core.Controller;

import com.easychat.core.Controller.dto.UserDto;
import com.easychat.core.entity.User;
import com.easychat.core.mapper.UserMapper;
import com.easychat.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/easychat/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private UserMapper mapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return ResponseEntity.ok( service.getAllUsers().stream()
                .map(mapper::mapUserToUserDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        try{
            UserDto userDto = mapper.mapUserToUserDto(service.getUserById(id));
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User createdUser = service.createUser(mapper.mapUserDtoToUser(userDto));
        return ResponseEntity.ok(mapper.mapUserToUserDto(createdUser));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody UserDto userDto) {
        try {
            service.deleteUser(mapper.mapUserDtoToUser(userDto));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        try {
            User updatedUser = mapper.mapUserDtoToUser(userDto);
            updatedUser = service.updateUser(updatedUser);
            return ResponseEntity.ok(mapper.mapUserToUserDto(updatedUser));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}