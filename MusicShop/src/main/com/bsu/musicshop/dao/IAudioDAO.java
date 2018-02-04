package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.Audio;

import java.util.List;

public interface IAudioDAO {


    void deleteAudio(int audioId);

    void addAudio(String title, double price, double discount,
                  String image_url, int albumId, int artistId);

    List<Audio> getAudiosByAlbum(int id);

    List<Audio> getAudios() ;
}
