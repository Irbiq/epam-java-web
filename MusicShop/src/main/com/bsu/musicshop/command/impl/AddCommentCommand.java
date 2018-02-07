package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.service.ICommentService;
import main.com.bsu.musicshop.service.impl.CommentService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCommentCommand extends AbstractCommand {


    ICommentService commentService = new CommentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int userId = ((User)request.getSession().getAttribute(Attributes.USER)).getId();
        int albumId = Integer.parseInt(request.getParameter("comment-album-id"));
        String text = request.getParameter("comment-text");
        System.out.println(userId +" "+ albumId + text);
        int result  = commentService.addComment(text,userId,albumId);
        return "/controller?command=get_album&id="+albumId;
    }
}
