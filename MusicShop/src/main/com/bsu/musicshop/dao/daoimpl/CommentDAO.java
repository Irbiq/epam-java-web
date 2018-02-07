package main.com.bsu.musicshop.dao.daoimpl;

import main.com.bsu.musicshop.dao.ICommentDAO;
import main.com.bsu.musicshop.dbmanager.ConnectionPool;
import main.com.bsu.musicshop.entity.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements ICommentDAO {

    private final static Logger logger = LogManager.getLogger(CommentDAO.class);

    private static final int ZERO_COMMENTS_ADDED = 0;
    public static final String SELECT_ALL_COMMENTS = "select * from comment";

    private static final String SELECT_COMMENTS_BY_ALBUM_ID = "select cmmt.idcomment,cmmt.text,cmmt.idalbum,usr.name from comment cmmt\n" +
            "inner join user usr\n" +
            "on cmmt.iduser = usr.iduser\n" +
            "where cmmt.idalbum = ?";

    private static final String INSERT_COMMENT = "insert into comment (text,iduser,idalbum) values(?,?,?)";

    public static final String DELETE_COMMENT = "delete from comment where idcomment = ?";


    @Override
    public List<Comment> getAllCommentsByAlbumId(int albumId) {
        List<Comment> comments = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_COMMENTS_BY_ALBUM_ID);
            ps.setInt(1, albumId);
            ResultSet rs = ps.executeQuery();
            Comment comment;
            comments = new ArrayList<>();
            while (rs.next()) {
                comment = new Comment();
                comment.setId(rs.getInt(1));
                comment.setText(rs.getString(2));
                comment.setAlbumId(rs.getInt(3));
                comment.setUser(rs.getString(4));
                comments.add(comment);
                return comments;
            }
        } catch (SQLException e) {
            logger.error("Error. Impossible to load albums : " + e);
        }
        return comments;
    }

    @Override
    public int addComment(String text, int userId, int albumId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_COMMENT);
            ps.setString(1, text);
            ps.setInt(2, userId);
            ps.setInt(3, albumId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error. Impossible to load albums : " + e);
        }
        return ZERO_COMMENTS_ADDED;
    }

    @Override
    public void deleteComment(int commentId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_COMMENT);
            ps.setInt(1, commentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error. Impossible to load albums : " + e);
        }
    }


    @Override
    public void getAllComments() {

    }

}
