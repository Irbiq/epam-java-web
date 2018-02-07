package main.com.bsu.musicshop.command.impl.cart;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.ICartService;
import main.com.bsu.musicshop.service.impl.CartService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteFromCart extends AbstractCommand {

    ICartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int audioId = Integer.parseInt(request.getParameter("audio-delete-id"));
        ((List<Audio>)request.getSession().getAttribute(Attributes.CART_LIST)).removeIf(audio -> audio.getId()==audioId);

        return Pages.CART;
    }
}
