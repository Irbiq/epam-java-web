package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.Artist;
import main.com.bsu.musicshop.exception.DAOException;

import java.util.List;

public interface IArtistDAO {

    List<Artist> getAllArtists() throws DAOException;
}
