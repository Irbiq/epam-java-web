package main.com.bsu.musicshop.command.impl;

import main.com.bsu.musicshop.command.AbstractCommand;
import main.com.bsu.musicshop.command.Pages;
import main.com.bsu.musicshop.entity.Album;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.entity.Comment;
import main.com.bsu.musicshop.service.IAlbumService;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.ICommentService;
import main.com.bsu.musicshop.service.impl.AlbumService;
import main.com.bsu.musicshop.service.impl.AudioService;
import main.com.bsu.musicshop.service.impl.CommentService;
import main.com.bsu.musicshop.util.Attributes;
import org.w3c.dom.Attr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class GetAlbumCommand extends AbstractCommand {


    private IAlbumService albumService = new AlbumService();
    private IAudioService audioService = new AudioService();
    private ICommentService commentService = new CommentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int albumId = Integer.parseInt(request.getParameter("id"));
        Album album = albumService.getAlbumById(albumId);
        List<Audio> audios = audioService.getAudiosByAlbum(albumId);
        List<Comment> comments = commentService.getAllCommentsByAlbumId(albumId);
        request.setAttribute(Attributes.ALBUM, album);
        request.setAttribute(Attributes.AUDIOS, audios);
        request.setAttribute(Attributes.COMMENTS,comments);
        return Pages.ALBUM;

    }
}
