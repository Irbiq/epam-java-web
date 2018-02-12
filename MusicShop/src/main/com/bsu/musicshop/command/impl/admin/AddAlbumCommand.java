package main.com.bsu.musicshop.command.impl.admin;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.service.IAlbumService;
import main.com.bsu.musicshop.service.impl.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAlbumCommand extends AbstractCommand {

    IAlbumService albumService = new AlbumService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String title = request.getParameter("album-title");
        String image = request.getParameter("album-image");
        int artistId = Integer.parseInt(request.getParameter("album-artist"));
        albumService.addAlbum(title,image,artistId);

        return Pages.ALBUMS;
    }
}
