package main.com.bsu.musicshop.command.impl.admin;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.service.IUserService;
import main.com.bsu.musicshop.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand extends AbstractCommand {

    IUserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int id  = Integer.parseInt(request.getParameter("user-id"));
        userService.deleteUser(id);
        return Pages.USERS;

    }
}
