package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.Album;

import java.util.List;

public interface IAlbumDAO {

    void addAlbum(String title, String imageUrl, int artistId);

    void deleteAlbum(int albumId);

    Album getAlbumById(int id);

    List<Album> getAlbums();

}
