package main.com.bsu.musicshop.command.impl.admin;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.service.IUserService;
import main.com.bsu.musicshop.service.impl.UserService;
import main.com.bsu.musicshop.util.Attributes;
import main.com.bsu.musicshop.util.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetEditUserCommand extends AbstractCommand {

    private IUserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        /**/
        int userId = Integer.parseInt(request.getParameter("user-edit-id"));
        User user = userService.findById(userId);
        if(user!=null || user.getId()!=0) {
            request.getSession().setAttribute(Attributes.EDITED_USER,user);

            return Pages.EDIT_USER;
        }else{
            return Pages.USERS;
        }

    }
}
