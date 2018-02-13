package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.IAudioDAO;
import main.com.bsu.musicshop.dao.daoimpl.AudioDAO;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.exception.DAOException;
import main.com.bsu.musicshop.exception.ServiceException;
import main.com.bsu.musicshop.service.IAudioService;

import java.util.List;

public class AudioService implements IAudioService {


    private IAudioDAO audioDAO = new AudioDAO();

    @Override
    public List<Audio> getAudios() {
        try {
            return audioDAO.getAudios();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Audio> getAudiosByAlbum(int albumId) {
        try {
            return audioDAO.getAudiosByAlbum(albumId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAudio(int audioId) {
        try {
            audioDAO.deleteAudio(audioId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
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
        try {
            audioDAO.addAudio(title,price,discount,imageUrl,albumId,artistId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Audio getAudioById(int audioId) {
        try {
            return audioDAO.getAudioById(audioId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

