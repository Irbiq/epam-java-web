package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.Album;
import main.com.bsu.musicshop.exception.DAOException;

import java.util.List;

public interface IAlbumDAO {

    void addAlbum(String title, String imageUrl, int artistId) throws DAOException;

    void deleteAlbum(int albumId) throws DAOException;

    Album getAlbumById(int id) throws DAOException;

    List<Album> getAlbums() throws DAOException;

}
