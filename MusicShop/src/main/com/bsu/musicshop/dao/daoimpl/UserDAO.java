package main.com.bsu.musicshop.dao.daoimpl;

import main.com.bsu.musicshop.dao.IUserDAO;
import main.com.bsu.musicshop.dbmanager.ConnectionPool;
import main.com.bsu.musicshop.entity.User;
import main.com.bsu.musicshop.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private final static Logger logger = LogManager.getLogger(UserDAO.class);

    private static final String COUNT_USERS_BY_LOGIN_QUERY = "SELECT COUNT(*) FROM music_store.user WHERE login = ?";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT * FROM music_store.user WHERE login = ? and password = ?";
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM music_store.user";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM music_store.user WHERE iduser = ?";
    private static final String UPDATE_USER_QUERY =
            "update user \n" +
                    "set name = ?,\n" +
                    " surname = ?,\n" +
                    " login = ?,\n" +
                    " role = ?\n" +
                    "where iduser = ?;\n";
    private static final String INSERT_USER_QUERY = "insert into music_store.user (name,surname,login,password,role) values(?,?,?,?,?)";
    private static final String FIND_USER_BY_ID = "select * from user where iduser = ?";

    @Override
    public void updateUser(User user) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getRole());
            statement.setInt(5,user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error. Impossible to update user. ", e);
            throw new DAOException("Error. Impossible to update user. ", e);
        }
    }



    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> users = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_USERS_QUERY);
            users = new ArrayList<>();
            User user;
            int id;
            String name;
            String surname;
            String login;
            String password;
            String role;
            while (rs.next()) {
                user = new User();
                id = rs.getInt(1);
                login = rs.getString(2);
                password = rs.getString(3);
                name = rs.getString(4);
                surname = rs.getString(5);
                role = rs.getString(6);
                user.setId(id);
                user.setLogin(login);
                user.setPassword(password);
                user.setName(name);
                user.setSurname(surname);
                user.setRole(role);
                users.add(user);
            }

        } catch (SQLException e) {
            logger.error("Error. Impossible to select users ", e);
            throw new DAOException("Error. Impossible to select users ", e);
        }
        return users;
    }


    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setName(rs.getString(4));
                user.setSurname(rs.getString(5));
                user.setRole(rs.getString(6));
            }
        } catch (SQLException e) {
            logger.error("Error. Impossible to execute a finding user query. ", e);
            throw new DAOException("Error. Impossible to execute a finding user query.", e);
        }
        return user;
    }

    @Override
    public boolean isLoginFree(String login) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(COUNT_USERS_BY_LOGIN_QUERY);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int result = rs.getInt(1);
            if (result == 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error. Impossible to execute a finding login query. ", e);
            throw new DAOException("Error. Impossible to execute a finding login query. ", e);
        }
        return false;
    }

    @Override
    public void addUser(User user) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error. Impossible to add user. ", e);
            throw new DAOException("Error. Impossible to add user. ", e);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY);
            statement.setInt(1, id);
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }


    @Override
    public User findById(int id) throws DAOException {
        User user;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            user = new User();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setName(rs.getString(4));
                user.setSurname(rs.getString(5));
                user.setRole(rs.getString(6));
            }
            return user;
        } catch (SQLException e) {
            logger.error("Error. Impossible to execute a finding user query. ", e);
            throw new DAOException("Error. Impossible to execute a finding user query.", e);
        }
     }

}
