package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.IAudioDAO;
import main.com.bsu.musicshop.dao.daoimpl.AudioDAO;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.exception.ServiceException;
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

    @Override
    public void deleteAudio(int audioId) {
        System.out.println(audioId);
        audioDAO.deleteAudio(audioId);
    }

    @Override
    public void addAudio(String title, double price, double discount, String imageUrl, int albumId, int artistId) throws ServiceException {
        if(imageUrl.isEmpty()){
            imageUrl = "default-cover.png";
        }
        if( discount<0||discount>100 ){
            throw new ServiceException("incorrect discount");
        }
        if(artistId == 0  ){
            throw new ServiceException("incorrect artist");
        }

        audioDAO.addAudio(title,price,discount,imageUrl,albumId,artistId);
    }

    @Override
    public Audio getAudioById(int audioId) {
        return audioDAO.getAudioById(audioId);
    }


}

