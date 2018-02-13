package main.com.bsu.musicshop.service;

import main.com.bsu.musicshop.entity.Audio;

import java.util.Set;

public interface ICartService {
    void deleteAudio(int audioId);

    void buyAll(Set<Audio> cartList);
}
