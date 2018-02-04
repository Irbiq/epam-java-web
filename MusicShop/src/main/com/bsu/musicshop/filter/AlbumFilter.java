package main.com.bsu.musicshop.filter;

import main.com.bsu.musicshop.entity.Album;
import main.com.bsu.musicshop.service.impl.AlbumService;
import main.com.bsu.musicshop.util.Attributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebFilter(filterName = "AudioFilter", urlPatterns = {"/pages/albums.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class AlbumFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        /*if(((HttpServletRequest) servletRequest).getMethod().equals("GET")) {*/
        AlbumService albumService = new AlbumService();
        List<Album> albums = albumService.getAlbums();
        request.getSession().setAttribute(Attributes.ALBUMS, albums);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
