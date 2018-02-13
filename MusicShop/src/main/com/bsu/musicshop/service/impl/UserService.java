package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.IUserDAO;
import main.com.bsu.musicshop.dao.daoimpl.AlbumDAO;
import main.com.bsu.musicshop.dao.daoimpl.UserDAO;
import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.exception.DAOException;
import main.com.bsu.musicshop.exception.ServiceException;
import main.com.bsu.musicshop.service.IUserService;
import main.com.bsu.musicshop.util.DataValidator;
import main.com.bsu.musicshop.util.Roles;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService implements IUserService {


    private final static Logger logger = LogManager.getLogger(UserService.class);
    private IUserDAO userDAO = new UserDAO();

    @Override
    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        return null;
    }

    @Override
    public void updateUser(User user) {

        try {
            userDAO.updateUser(user);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        if (DataValidator.isLoginCorrect(user.getLogin()) && DataValidator.isPasswordCorrect(user.getPassword())) {
            try {
                if (userDAO.isLoginFree(user.getLogin())) {
                    user.setRole(Roles.USER.toString());
                    userDAO.addUser(user);
                    return true;
                } else {
                    throw new ServiceException("Login is used");
                }
            } catch (DAOException e) {
                e.printStackTrace();
            }
        } else {
            throw new ServiceException("Incorrect login or password");
        }
        return false;
    }

    @Override
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        try {
            return userDAO.findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        return null;
    }

    @Override
    public User findById(int id) {
        try {
            return userDAO.findById(id);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
        }
        return null;
    }
}
