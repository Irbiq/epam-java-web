package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.IAlbumDAO;
import main.com.bsu.musicshop.dao.daoimpl.AlbumDAO;
import main.com.bsu.musicshop.entity.Album;
import main.com.bsu.musicshop.service.IAlbumService;

import java.util.List;

public class AlbumService implements IAlbumService {

    private IAlbumDAO albumDAO = new AlbumDAO();

    @Override
    public List<Album> getAlbums() {
        return albumDAO.getAlbums();
    }

    @Override
    public Album getAlbumById(int albumId){
        return albumDAO.getAlbumById(albumId);
    }

}
