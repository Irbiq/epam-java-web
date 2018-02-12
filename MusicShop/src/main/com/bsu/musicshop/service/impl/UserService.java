package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.IUserDAO;
import main.com.bsu.musicshop.dao.daoimpl.UserDAO;
import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.exception.ServiceException;
import main.com.bsu.musicshop.service.IUserService;
import main.com.bsu.musicshop.util.DataValidator;
import main.com.bsu.musicshop.util.Roles;

import java.util.List;

public class UserService implements IUserService {

    IUserDAO userDAO = new UserDAO();
    
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        System.out.println("In service" + userDAO.isLoginFree(user.getLogin()));

        if(DataValidator.isLoginCorrect(user.getLogin()) && DataValidator.isPasswordCorrect(user.getPassword())) {
            if (userDAO.isLoginFree(user.getLogin())) {
                user.setRole(Roles.USER.toString());
                userDAO.addUser(user);
                return true;
            } else {
                throw new ServiceException("Login is used");
            }
        }else {
            throw new ServiceException("Incorrect login or password");
        }
    }

    @Override
    public void deleteUser(int userId){
        userDAO.deleteUser(userId);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return userDAO.findUserByLoginAndPassword(login, password);
    }
}
