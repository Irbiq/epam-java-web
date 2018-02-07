package main.com.bsu.musicshop.dao;

import main.com.bsu.musicshop.entity.Comment;

import java.util.List;

public interface ICommentDAO {
    List<Comment> getAllCommentsByAlbumId(int albumId);
    void getAllComments();
    int addComment(String text, int userId, int albumId);
    void deleteComment(int commentId);

}
