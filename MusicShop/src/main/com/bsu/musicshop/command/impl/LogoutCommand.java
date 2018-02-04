package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("user",null);
        request.getSession().invalidate();
        return Pages.LOGIN;
    }
}
