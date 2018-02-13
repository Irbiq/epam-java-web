package main.com.bsu.musicshop.command.impl.admin;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.service.IUserService;
import main.com.bsu.musicshop.service.impl.UserService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserConfirmCommand extends AbstractCommand {



    private IUserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {



        User user = (User) request.getSession().getAttribute(Attributes.EDITED_USER);
        String name = request.getParameter("user-name");
        String surname = request.getParameter("user-surname");
        String login = request.getParameter("user-login");
        String role = request.getParameter("user-role");
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setRole(role);
        userService.updateUser(user);
        request.getSession().setAttribute(Attributes.EDITED_USER,null);
        return Pages.USERS;
    }
}
