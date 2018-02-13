package main.com.bsu.musicshop.command.impl.admin;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.exception.ServiceException;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.impl.AudioService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAudioCommand extends AbstractCommand {

    private IAudioService audioService = new AudioService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        String title = request.getParameter("audio-title");
        int artistId = Integer.parseInt(request.getParameter("audio-artist"));
        double price = Double.parseDouble(request.getParameter("audio-price"));
        int discount = Integer.parseInt(request.getParameter("audio-discount"));
        String imageUrl = request.getParameter("audio-image");

        try {
            audioService.addAudio(title, price, discount, imageUrl, 0, artistId);
        } catch (ServiceException e) {

        }


        return Pages.AUDIOS;
    }
}
