package main.com.bsu.musicshop.dao.daoimpl;

import main.com.bsu.musicshop.dao.ICartDAO;
import main.com.bsu.musicshop.entity.Audio;

import java.util.List;
import java.util.Set;

public class CartDAO implements ICartDAO {

    private ICartDAO cartDAO = new CartDAO();

    @Override
    public void buyAll(Set<Audio> cartList) {

    }
}
