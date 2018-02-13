package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.IArtistDAO;
import main.com.bsu.musicshop.dao.daoimpl.ArtistDAO;
import main.com.bsu.musicshop.entity.Artist;
import main.com.bsu.musicshop.exception.DAOException;
import main.com.bsu.musicshop.service.IArtistService;

import java.util.List;

public class ArtistService implements IArtistService {

    private IArtistDAO artistDAO = new ArtistDAO();

    @Override
    public List<Artist> getAllArtists() {
        try {
            return artistDAO.getAllArtists();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
