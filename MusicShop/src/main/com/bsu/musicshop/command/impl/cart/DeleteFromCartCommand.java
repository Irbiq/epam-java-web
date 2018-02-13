package main.com.bsu.musicshop.command.impl.cart;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.ICartService;
import main.com.bsu.musicshop.service.impl.CartService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class DeleteFromCartCommand extends AbstractCommand {

    private ICartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int audioId = Integer.parseInt(request.getParameter("audio-delete-id"));

        Set<Audio> cartList = ((Set<Audio>) request.getSession().getAttribute(Attributes.CART_LIST));
        double totalPrice = 0;
        if (request.getSession().getAttribute(Attributes.TOTAL_PRICE) != null) {
            totalPrice = (double) request.getSession().getAttribute(Attributes.TOTAL_PRICE);
        }
        for (Audio aud : cartList) {
            if (aud.getId() == audioId) {
                totalPrice -= aud.getPrice() * (1 - aud.getDiscount() / 100);
            }
        }
        cartList.removeIf(audio -> audio.getId() == audioId);
        request.getSession().setAttribute(Attributes.TOTAL_PRICE, totalPrice);

        return Pages.CART;
    }
}
