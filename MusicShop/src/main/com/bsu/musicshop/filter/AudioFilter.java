package main.com.bsu.musicshop.filter;

import main.com.bsu.musicshop.entity.Artist;
import main.com.bsu.musicshop.entity.Audio;
import main.com.bsu.musicshop.service.IArtistService;
import main.com.bsu.musicshop.service.IAudioService;
import main.com.bsu.musicshop.service.impl.ArtistService;
import main.com.bsu.musicshop.service.impl.AudioService;
import main.com.bsu.musicshop.util.Attributes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "AudioFilter", urlPatterns = {"/pages/audios.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class AudioFilter implements Filter {


    Logger logger = LogManager.getLogger(AudioFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        /*if (((HttpServletRequest) servletRequest).getMethod().equals("GET"))*/
        IAudioService audioService = new AudioService();
        List<Audio> audios = audioService.getAudios();
        request.getSession().setAttribute(Attributes.AUDIOS, audios);
        request.getSession().setAttribute(Attributes.AUDIOS_AMOUNT, audios.size());
        audios.forEach(System.out::println);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}