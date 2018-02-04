package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.service.IUserService;
import main.com.bsu.musicshop.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends AbstractCommand {

    IUserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        /*if (request.getMethod().equals("POST")) {*/
        String login = request.getParameter("user-login");
        String password = request.getParameter("user-password");
        User user = userService.findByLoginAndPassword(login, password);

        System.out.println(login +" "+ password +" "+ user);

        if (user != null) {
            request.getSession().setAttribute("user", user);
            System.out.println("LOGGED");
            return Pages.HOME;
        } else {
            return Pages.LOGIN;
        }
    }

}
