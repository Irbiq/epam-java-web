package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.impl.AudioService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AudioCommand extends AbstractCommand {


    private IAudioService songService = new AudioService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Audio> audios = songService.getAudios();
        request.getSession().setAttribute("audios",audios);

        return Pages.SONGS;
    }
}
