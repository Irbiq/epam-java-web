package main.com.bsu.musicshop.service.impl;

import main.com.bsu.musicshop.dao.ICommentDAO;
import main.com.bsu.musicshop.dao.daoimpl.CommentDAO;
import main.com.bsu.musicshop.entity.Comment;
import main.com.bsu.musicshop.service.ICommentService;

import java.util.List;

public class CommentService implements ICommentService {

    private ICommentDAO commentDAO = new CommentDAO();

    @Override
    public List<Comment> getAllCommentsByAlbumId(int albumId) {
        return commentDAO.getAllCommentsByAlbumId(albumId);
    }

    @Override
    public int addComment(String text, int userId, int albumId) {
        return commentDAO.addComment(text, userId, albumId);
    }

    @Override
    public void deleteComment(int commentId) {
        commentDAO.deleteComment(commentId);
    }


}
