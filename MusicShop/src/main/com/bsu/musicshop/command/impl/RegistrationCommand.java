package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.exception.ServiceException;
import main.com.bsu.musicshop.service.IUserService;
import main.com.bsu.musicshop.service.impl.UserService;
import main.com.bsu.musicshop.util.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand extends AbstractCommand {

    private IUserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {

        String name = request.getParameter("user-name");
        String surname = request.getParameter("user-surname");
        String login = request.getParameter("user-login");
        String password = request.getParameter("user-password");
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(Roles.USER.toString());
        boolean isAdded = false;
        try {
            isAdded = userService.addUser(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println("IN REGISTRATION");
        System.out.println(isAdded);
        System.out.println(user);
        if (isAdded) {
            request.getSession().setAttribute("user", user);
            return Pages.HOME;
        } else {
            return Pages.REGISTARTION;
        }
    }
}
