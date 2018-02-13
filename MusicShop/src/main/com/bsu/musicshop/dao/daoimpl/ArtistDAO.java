package main.com.bsu.musicshop.dao.daoimpl;

import main.com.bsu.musicshop.dao.IArtistDAO;
import main.com.bsu.musicshop.dbmanager.ConnectionPool;
import main.com.bsu.musicshop.entity.Artist;
import main.com.bsu.musicshop.entity.Artist;
import main.com.bsu.musicshop.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO implements IArtistDAO {

    private final static Logger logger = LogManager.getLogger(ArtistDAO.class);

    private static final String SELECT_ARTISTS = "SELECT * FROM ARTIST";
    
    @Override
    public List<Artist> getAllArtists() throws DAOException {

        List<Artist> artists = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_ARTISTS);
            ResultSet rs = statement.executeQuery();
            artists = new ArrayList<>();
            Artist artist;
            while (rs.next()) {
                artist = new Artist();
                artist.setId(rs.getInt(1));
                artist.setName(rs.getString(2));
                artists.add(artist);
            }
        } catch (SQLException e) {
            logger.error("Error. Impossible to load artists : " + e);
            throw new DAOException("Error. Impossible to load Artists : " + e,e);
        }
        return artists;
    }
}
