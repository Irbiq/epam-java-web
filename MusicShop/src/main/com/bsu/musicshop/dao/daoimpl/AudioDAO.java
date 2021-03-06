package main.com.bsu.musicshop.dao.daoimpl;

import main.com.bsu.musicshop.dao.IAudioDAO;
import main.com.bsu.musicshop.dbmanager.ConnectionPool;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AudioDAO implements IAudioDAO {

    private final static Logger logger = LogManager.getLogger(AudioDAO.class);

    private static final String SELECT_AUDIOS =
            "SELECT\n" +
                    "  aud.idaudio,\n" +
                    "  aud.title,\n" +
                    "  aud.price,\n" +
                    "  aud.discount,\n" +
                    "  aud.image_url,\n" +
                    "  art.name  AS artist,\n" +
                    "  alb.title AS album\n" +
                    "FROM audio aud\n" +
                    "  INNER JOIN artist art\n" +
                    "    ON aud.artist_idartist = art.idartist\n" +
                    "  LEFT OUTER JOIN album alb\n" +
                    "    ON aud.album_idalbum = alb.idalbum;\n";

    private static final String SELECT_AUDIO_BY_ID =
            "SELECT \n" +
                    "    aud.idaudio,\n" +
                    "    aud.title,\n" +
                    "    aud.price,\n" +
                    "    aud.discount,\n" +
                    "    aud.image_url,\n" +
                    "    art.name AS artist,\n" +
                    "    alb.title AS album\n" +
                    "FROM\n" +
                    "    audio aud\n" +
                    "        INNER JOIN\n" +
                    "    artist art ON aud.artist_idartist = art.idartist\n" +
                    "        LEFT OUTER JOIN\n" +
                    "    album alb ON aud.album_idalbum = alb.idalbum\n" +
                    "WHERE\n" +
                    "    aud.idaudio = ? ;";

    private static final String SELECT_AUDIOS_BY_ALBUM =
            "select * from audio\n" +
                    "where  album_idalbum = ?;";

    private static final String INSERT_AUDIO =
            "INSERT INTO music_store.audio " +
                    "(title, price, discount, image_url, album_idalbum, artist_idartist) values (?,?,?,?,?,?);";

    private static final String DELETE_AUDIO = "DELETE FROM audio WHERE  idaudio = ?;";


    @Override
    public void deleteAudio(int audioId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_AUDIO);
            ps.setInt(1, audioId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error. Impossible to delete audio : ", e);
            throw new DAOException("Error. Impossible to delete audio : ", e);
        }
    }

    @Override
    public void addAudio(String title, double price, double discount,
                         String image_url, int albumId, int artistId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_AUDIO);
            ps.setString(1, title);
            ps.setDouble(2, price);
            ps.setDouble(3, discount);
            ps.setString(4, image_url);
            if (albumId == 0) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, albumId);
            }
            ps.setInt(6, artistId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error. Impossible to insert audios : ", e);
            throw new DAOException("Error. Impossible to insert audios : ", e);
        }
    }


    @Override
    public Audio getAudioById(int id) throws DAOException {
        Audio audio = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_AUDIO_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            audio = getAudiosFromResultSet(rs).get(0);
            return audio;
        } catch (SQLException e) {
            logger.error("Error. Impossible to load audio by id : ", e);
            throw new DAOException("Error. Impossible to load audios : ", e);
        }
    }

    @Override
    public List<Audio> getAudiosByAlbum(int id) throws DAOException {
        List<Audio> audios = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_AUDIOS_BY_ALBUM);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            audios = new ArrayList<>();
            audios = getAudiosFromResultSet(rs);
        } catch (SQLException e) {
            logger.error("Error. Impossible to load audio by album : ", e);
            throw new DAOException("Error. Impossible to load audio by album : ", e);
        }
        return audios;
    }


    @Override
    public List<Audio> getAudios() throws DAOException {

        List<Audio> audios = Collections.emptyList();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement ps = connection.prepareStatement(SELECT_AUDIOS);
            ResultSet rs = ps.executeQuery();
            audios = getAudiosFromResultSet(rs);
            audios.forEach(System.out::println);
        } catch (SQLException e) {
            logger.error("Error. Impossible to load audios : ", e);
            throw new DAOException("Error. Impossible to load audios : ", e);
        }
        return audios;
    }

    private ArrayList<Audio> getAudiosFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Audio> audios = new ArrayList<>();
        Audio audio;
        while (rs.next()) {
            audio = new Audio();
            audio.setId(rs.getInt(1));
            audio.setTitle(rs.getString(2));
            audio.setPrice(rs.getInt(3));
            audio.setDiscount(rs.getInt(4));
            audio.setImageUrl(rs.getString(5));
            audio.setArtist(rs.getString(6));
            audio.setAlbum(rs.getString(7));
            audios.add(audio);
        }
        return audios;
    }
}
