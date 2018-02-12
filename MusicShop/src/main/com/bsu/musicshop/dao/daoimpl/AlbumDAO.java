package main.com.bsu.musicshop.dao.daoimpl;

import main.com.bsu.musicshop.dao.IAlbumDAO;
import main.com.bsu.musicshop.dbmanager.ConnectionPool;
import main.com.bsu.musicshop.entity.Album;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO implements IAlbumDAO {
    private final static Logger logger = LogManager.getLogger(AlbumDAO.class);

    private final static String SELECT_ALBUMS =
            "SELECT alb.idalbum,alb.title,alb.image_url,art.name \n" +
                    "FROM album alb \n" +
                    "INNER JOIN artist art ON alb.artist_idartist = art.idartist;";

    private final static String SELECT_ALBUM_BY_ID =
            "SELECT alb.idalbum,alb.title,alb.image_url,art.name FROM album alb " +
                    "INNER JOIN artist art on alb.artist_idartist = art.idartist WHERE alb.idalbum = ?";

    private static final String INSERT_ALBUM = "INSERT INTO album (title,image_url,artist_idartist) values(?,?,?)";

    private static final String DELETE_ALBUM = "DELETE FROM album WHERE idalbum=?";

    @Override
    public void addAlbum(String title, String imageUrl, int artistId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_ALBUM);
            ps.setString(1, title);
            ps.setString(2, imageUrl);
            ps.setInt(3, artistId);
            int result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error. Impossible to load albums : " + e);
        }
    }

    @Override
    public void deleteAlbum(int albumId) {
        Album album = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_ALBUM);
            ps.setInt(1, albumId);
            int result = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error. Impossible to load albums : " + e);
        }
    }

    @Override
    public Album getAlbumById(int id) {
        Album album = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALBUM_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                album = new Album();
                album.setId(rs.getInt(1));
                album.setTitle(rs.getString(2));
                album.setImageUrl(rs.getString(3));
                album.setArtist(rs.getString(4));
            }
        } catch (SQLException e) {
            logger.error("Error. Impossible to load albums : " + e);
        }
        return album;
    }

    @Override
    public List<Album> getAlbums() {
        List<Album> albums = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_ALBUMS);
            ResultSet rs = statement.executeQuery();

            albums = new ArrayList<>();
            Album album;
            while (rs.next()) {
                album = new Album();
                album.setId(rs.getInt(1));
                album.setTitle(rs.getString(2));
                album.setImageUrl(rs.getString(3));
                album.setArtist(rs.getString(4));
                albums.add(album);
            }
        } catch (SQLException e) {
            logger.error("Error. Impossible to load albums : " + e);
        }
        return albums;
    }
}
