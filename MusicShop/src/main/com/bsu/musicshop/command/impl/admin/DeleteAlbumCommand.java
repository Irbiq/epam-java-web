package main.com.bsu.musicshop.command.impl.admin;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.service.IAlbumService;
import main.com.bsu.musicshop.service.impl.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAlbumCommand extends AbstractCommand {

    private IAlbumService albumService = new AlbumService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("delete-album"));
        albumService.deleteAlbum(id);
        return Pages.ALBUMS;
    }
}
