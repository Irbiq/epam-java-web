package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LocaleCommand extends AbstractCommand {

    private static final String RUS = "rus";
    private static final String ENG = "eng";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = request.getParameter("lang");
        if (lang.equalsIgnoreCase(RUS)) {
            request.getSession().setAttribute(Attributes.LOCALE, new Locale("ru","RU"));
        } else {
            request.getSession().setAttribute(Attributes.LOCALE, new Locale("en","EN"));
        }
        return Pages.HOME;
    }
}
