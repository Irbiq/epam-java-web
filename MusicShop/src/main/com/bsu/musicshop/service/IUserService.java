package main.com.bsu.musicshop.service;

import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.exception.ServiceException;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    void updateUser(User user);

    boolean addUser(User user) throws ServiceException;
    void deleteUser(int userId);
    User findByLoginAndPassword(String name, String password);
    User findById(int id);
}
