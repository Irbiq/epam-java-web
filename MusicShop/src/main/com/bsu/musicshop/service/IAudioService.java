package main.com.bsu.musicshop.service;

import main.com.bsu.musicshop.entity.Audio;

import java.util.List;

public interface IAudioService {
    List<Audio> getAudios();

    List<Audio> getAudiosByAlbum(int albumId);

    void deleteAudio(int audioId);

    void addAudio(String title, double price, double discount,
                  String image_url, int albumId, int artistId);

    Audio getAudioById(int audioId);
}
