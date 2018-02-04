package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.User;

import java.util.Collection;
import java.util.List;

public interface IUserDAO {
    void addUser(User user);
    boolean deleteUser(int id);
    User findUser(int id);
    boolean updateUser(User user);
    List<User> getAllUsers();
    User findUserByLoginAndPassword(String login, String password);
    boolean isLoginFree(String login);
}
