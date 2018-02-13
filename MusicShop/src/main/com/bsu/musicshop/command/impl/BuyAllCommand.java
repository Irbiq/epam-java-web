package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.ICartService;
import main.com.bsu.musicshop.service.impl.CartService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class BuyAllCommand extends AbstractCommand {


    private ICartService cartService = new CartService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Set<Audio> cartList = (Set<Audio>) request.getSession().getAttribute(Attributes.CART_LIST);
        cartService.buyAll(cartList);
        return Pages.CART;
    }
}
