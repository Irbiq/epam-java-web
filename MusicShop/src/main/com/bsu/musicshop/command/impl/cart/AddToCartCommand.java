package main.com.bsu.musicshop.command.impl.cart;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.impl.AudioService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public class AddToCartCommand extends AbstractCommand {

    IAudioService audioService = new AudioService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("audio-buy"));

        System.out.println(id);
        Audio audio = audioService.getAudioById(id);
        Set<Audio> cartList = (Set<Audio>)request.getSession().getAttribute(Attributes.CART_LIST);
        cartList.add(audio);
        double totalPrice = 0;
        for (Audio aud : cartList ){
            totalPrice+=aud.getPrice()*(1-aud.getDiscount()/100);

        }
        request.getSession().setAttribute(Attributes.TOTAL_PRICE,totalPrice);


        return Pages.AUDIOS;
    }
}
