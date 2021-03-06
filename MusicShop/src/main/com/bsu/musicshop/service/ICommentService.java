package main.com.bsu.musicshop.service;

import main.com.bsu.musicshop.entity.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> getAllCommentsByAlbumId(int albumId);

    int addComment(String text, int userId, int albumId);

    void deleteComment(int commentId);
}
