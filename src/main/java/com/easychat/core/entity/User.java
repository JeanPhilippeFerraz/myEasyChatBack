package com.easychat.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name="users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(length=50, nullable = false)
    private String username;
    @Column(length=100, nullable = false)
    private String email;
    @Column(length=50, nullable = false)
    private String password;

    @OneToMany(mappedBy="owner")
    @JsonIgnore
    private Set<Channel> channelsOwned;


    @OneToMany(mappedBy="user")
    @JsonIgnore
    private Set<Message> messages;

    @ManyToMany
    @JoinTable(
            name = "channel_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id"))
    private Set<Channel> channels;

    public User() {
    }

    public User(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
