package main.com.bsu.musicshop.filter;

import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.service.IUserService;
import main.com.bsu.musicshop.service.impl.UserService;
import main.com.bsu.musicshop.util.Attributes;
import main.com.bsu.musicshop.util.Roles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "UsersFilter", urlPatterns = {"/pages/admin/users.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class UsersFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute(Attributes.USER);
        // if(user.getRole().equals(Roles.ADMIN)){
        IUserService userService = new UserService();
        List<User> users = userService.getAllUsers();
        request.getSession().setAttribute(Attributes.USERS, users);


        System.out.println("IN USERS FILTER");
        users.forEach(System.out::println);
        //}else {
        //    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        //}
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
