package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.Audio;

import java.util.List;
import java.util.Set;

public interface ICartDAO {
    void buyAll(Set<Audio> cartList);
}
