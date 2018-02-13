package main.com.bsu.musicshop.command;

import main.com.bsu.musicshop.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommand {

    public abstract String execute(HttpServletRequest request, HttpServletResponse response) ;

}
