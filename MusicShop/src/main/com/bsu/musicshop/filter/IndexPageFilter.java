package main.com.bsu.musicshop.filter;

import main.com.bsu.musicshop.entity.Album;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.IAlbumService;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.impl.AlbumService;
import main.com.bsu.musicshop.service.impl.AudioService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebFilter(filterName = "IndexFilter", urlPatterns = {"/index.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class IndexPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        /*if (((HttpServletRequest) servletRequest).getMethod().equals("GET"))*/
        IAudioService audioService = new AudioService();
        IAlbumService albumService = new AlbumService();
        if(request.getSession().getAttribute(Attributes.AUDIOS)==null) {
            List<Audio> audios = audioService.getAudios();

            request.getSession().setAttribute(Attributes.AUDIOS_AMOUNT, audios.size());
            request.getSession().setAttribute(Attributes.AUDIOS, audios);
        }
        if(request.getSession().getAttribute(Attributes.ALBUMS)==null) {
            List<Album> albums = albumService.getAlbums();
            request.getSession().setAttribute(Attributes.ALBUMS_AMOUNT, albums.size());
            request.getSession().setAttribute(Attributes.ALBUMS, albums);
        }
        System.out.println("In filter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
