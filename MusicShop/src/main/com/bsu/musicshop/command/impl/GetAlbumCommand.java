package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.Album;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.IAlbumService;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.impl.AlbumService;
import main.com.bsu.musicshop.service.impl.AudioService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class GetAlbumCommand extends AbstractCommand {


    IAlbumService albumService = new AlbumService();
    IAudioService audioService = new AudioService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Album album = albumService.getAlbumById(id);
        List<Audio> audios = audioService.getAudiosByAlbum(id);
        request.setAttribute(Attributes.ALBUM, album);
        request.setAttribute(Attributes.AUDIOS, audios);
        System.out.println("in get album");
        return Pages.ALBUM;

    }
}
