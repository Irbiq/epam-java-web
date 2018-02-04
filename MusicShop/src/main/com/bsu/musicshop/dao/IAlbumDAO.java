package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.Album;

import java.util.List;

public interface IAlbumDAO {

    Album getAlbumById(int id);

    List<Album> getAlbums();

}
