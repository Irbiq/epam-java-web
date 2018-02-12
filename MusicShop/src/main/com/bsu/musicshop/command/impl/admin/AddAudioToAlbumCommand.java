package main.com.bsu.musicshop.command.impl.admin;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.Album;
import main.com.bsu.musicshop.entity.Artist;
import main.com.bsu.musicshop.exception.ServiceException;
import main.com.bsu.musicshop.service.IAlbumService;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.impl.AlbumService;
import main.com.bsu.musicshop.service.impl.AudioService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class AddAudioToAlbumCommand extends AbstractCommand {

    private IAudioService audioService = new AudioService();
    private IAlbumService albumService = new AlbumService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String title = request.getParameter("audio-title");
        double price = Double.parseDouble(request.getParameter("audio-price"));
        int discount = Integer.parseInt(request.getParameter("audio-discount"));
        String imageUrl = request.getParameter("audio-image");
        int albumId = Integer.parseInt(request.getParameter("audio-album"));

        Album album = albumService.getAlbumById(albumId);
        List<Artist> artists = (List<Artist>) request.getSession().getAttribute(Attributes.ARTISTS);
        int artistId = 0;
        for (Artist artist : artists) {
            if (artist.getName()
                    .equals(album.getArtist())) {
                artistId = artist.getId();
            }
        }
        try {
            audioService.addAudio(title, price, discount, imageUrl, album.getId(), artistId);
            System.out.println(title +" "+ price +" "+ discount +" "+ imageUrl +" "+ album.getId() +" "+ artistId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return Pages.AUDIOS;
    }
}
