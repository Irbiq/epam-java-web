package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.IAlbumDAO;
import main.com.bsu.musicshop.dao.daoimpl.AlbumDAO;
import main.com.bsu.musicshop.entity.Album;
import main.com.bsu.musicshop.exception.DAOException;
import main.com.bsu.musicshop.service.IAlbumService;

import java.util.List;

public class AlbumService implements IAlbumService {

    private IAlbumDAO albumDAO = new AlbumDAO();

    @Override
    public List<Album> getAlbums() {
        try {
            return albumDAO.getAlbums();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Album getAlbumById(int albumId) {
        try {
            return albumDAO.getAlbumById(albumId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addAlbum(String title, String imageUrl, int artistId) {

        if (title.isEmpty()){
            return;
        }

        if(imageUrl.isEmpty()){
            imageUrl = "default-cover.png";
        }
        try {
            albumDAO.addAlbum(title, imageUrl, artistId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAlbum(int albumId) {

        try {
            albumDAO.deleteAlbum(albumId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}
