package main.com.bsu.musicshop.service;

import main.com.bsu.musicshop.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    boolean addUser(User user);
    User findByLoginAndPassword(String name, String password);

}
