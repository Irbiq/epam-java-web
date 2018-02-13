package main.com.bsu.musicshop.service.impl;


import main.com.bsu.musicshop.dao.ICartDAO;
import main.com.bsu.musicshop.dao.daoimpl.CartDAO;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.ICartService;

import java.util.Set;

public class CartService implements ICartService {

    private ICartDAO cartDAO = new CartDAO();

    @Override
    public void deleteAudio(int audioId) {

    }
    @Override
    public void buyAll(Set<Audio> cartList) {

        cartDAO.buyAll(cartList);
    }
}
