package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.exception.DAOException;

import java.util.Collection;
import java.util.List;

public interface IUserDAO {
    void addUser(User user) throws DAOException;

    boolean deleteUser(int id);

    void updateUser(User user) throws DAOException;

    List<User> getAllUsers() throws DAOException;

    User findUserByLoginAndPassword(String login, String password) throws DAOException;

    boolean isLoginFree(String login) throws DAOException;

    User findById(int id) throws DAOException;
}
