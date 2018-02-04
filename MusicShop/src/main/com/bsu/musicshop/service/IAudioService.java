package main.com.bsu.musicshop.service;

import main.com.bsu.musicshop.entity.Audio;

import java.util.List;

public interface IAudioService {
    List<Audio> getAudios();
    List<Audio> getAudiosByAlbum(int albumId);
}
