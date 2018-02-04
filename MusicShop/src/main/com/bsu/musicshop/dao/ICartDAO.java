package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.Audio;

import java.util.List;

public interface ICartDAO {
    //void addAudioToCart (Audio audio);
    //void abbAudiosToCart (List<Audio> audios);
    void buyAll (List<Audio> audios);
}
