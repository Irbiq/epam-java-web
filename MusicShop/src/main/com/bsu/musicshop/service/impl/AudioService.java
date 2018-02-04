package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.IAudioDAO;
import main.com.bsu.musicshop.dao.daoimpl.AudioDAO;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.IAudioService;

import java.util.List;

public class AudioService implements IAudioService {


    private IAudioDAO audioDAO = new AudioDAO();

    @Override
    public List<Audio> getAudios() {
        return audioDAO.getAudios();
    }

    @Override
    public List<Audio> getAudiosByAlbum(int albumId) {
        return audioDAO.getAudiosByAlbum(albumId);
    }


}
