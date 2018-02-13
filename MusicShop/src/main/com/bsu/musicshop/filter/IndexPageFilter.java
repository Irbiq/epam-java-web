package main.com.bsu.musicshop.filter;

import main.com.bsu.musicshop.entity.Album;
import main.com.bsu.musicshop.entity.Artist;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.IAlbumService;
import main.com.bsu.musicshop.service.IArtistService;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.impl.AlbumService;
import main.com.bsu.musicshop.service.impl.ArtistService;
import main.com.bsu.musicshop.service.impl.AudioService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;


@WebFilter(filterName = "IndexFilter", urlPatterns = {"/index.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class IndexPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute(Attributes.AUDIOS) == null) {
            IAudioService audioService = new AudioService();
            List<Audio> audios = audioService.getAudios();
            request.getSession().setAttribute(Attributes.AUDIOS_AMOUNT, audios.size());
            request.getSession().setAttribute(Attributes.AUDIOS, audios);
        }
        if (request.getSession().getAttribute(Attributes.ALBUMS) == null) {
            IAlbumService albumService = new AlbumService();
            List<Album> albums = albumService.getAlbums();
            request.getSession().setAttribute(Attributes.ALBUMS_AMOUNT, albums.size());
            request.getSession().setAttribute(Attributes.ALBUMS, albums);
        }
        if (request.getSession().getAttribute(Attributes.LOCALE) == null) {
            Locale locale = request.getLocale();
            request.getSession().setAttribute(Attributes.LOCALE, locale);
        }
        if (request.getSession().getAttribute(Attributes.ARTISTS) == null) {
            IArtistService artistService = new ArtistService();
            List<Artist> artists = artistService.getAllArtists();
            request.getSession().setAttribute(Attributes.ARTISTS, artists);
        }
        if (request.getSession().getAttribute(Attributes.CART_LIST) == null) {
            request.getSession().setAttribute(Attributes.CART_LIST, new HashSet<Audio>());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
