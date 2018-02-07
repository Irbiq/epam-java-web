package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.service.IAlbumService;
import main.com.bsu.musicshop.service.impl.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAlbumCommand extends AbstractCommand {


    IAlbumService albumService = new AlbumService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        //albumService.addAlbum();
        return null;
    }
}
