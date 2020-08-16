package com.customroutes.camelroutes.service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private final Map<Integer, User> users = new TreeMap<>();

    public UserService() {
        users.put(1, new User(1, "John Coltrane"));
        users.put(2, new User(2, "Miles Davis"));
        users.put(3, new User(3, "Sonny Rollins"));
    }

    public User findUser(Integer id) {
        return users.get(id);
    }

    public Collection<User> findUsers() {
        return users.values();
    }

    public void updateUser(User user) {
        users.put(user.getId(), user);
    }

}