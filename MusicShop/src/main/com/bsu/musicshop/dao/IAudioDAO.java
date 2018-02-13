package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.exception.DAOException;

import java.util.List;

public interface IAudioDAO {
    void deleteAudio(int audioId) throws DAOException;

    void addAudio(String title, double price, double discount,
                  String image_url, int albumId, int artistId) throws DAOException;

    Audio getAudioById(int id) throws DAOException;

    List<Audio> getAudiosByAlbum(int id) throws DAOException;

    List<Audio> getAudios() throws DAOException;
}
