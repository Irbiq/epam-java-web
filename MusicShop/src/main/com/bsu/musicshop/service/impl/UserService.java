package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.IUserDAO;
import main.com.bsu.musicshop.dao.daoimpl.UserDAO;
import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.service.IUserService;

import java.util.List;

public class UserService implements IUserService {

    IUserDAO userDAO = new UserDAO();
    
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean addUser(User user) {
        System.out.println("In service" + userDAO.isLoginFree(user.getLogin()));
        if (userDAO.isLoginFree(user.getLogin())) {
            System.out.println("In service" +" "+ user.getLogin());
            userDAO.addUser(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return userDAO.findUserByLoginAndPassword(login, password);
    }
}
