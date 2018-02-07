package main.com.bsu.musicshop.command.impl.cart;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.impl.AudioService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddToCart extends AbstractCommand {

    IAudioService audioService = new AudioService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("audio-id"));
        Audio audio = audioService.getAudioById(id);
        List<Audio> cartList = (List<Audio>)request.getSession().getAttribute("cart_list");
        cartList.add(audio);

        return null;
    }
}
