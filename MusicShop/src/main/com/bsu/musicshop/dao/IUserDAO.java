package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.User;

import java.util.Collection;
import java.util.List;

public interface IUserDAO {
    void addUser(User user);
    boolean deleteUser(int id);
    List<User> getAllUsers();
    User findUserByLoginAndPassword(String login, String password);
    boolean isLoginFree(String login);
}
