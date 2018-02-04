package main.com.bsu.musicshop.dbmanager;

import java.util.ResourceBundle;

public class DatabaseConstants {

    final static   String DRIVER;
    final static  String URL;
    final static String LOGIN;
    final static int POOL_SIZE;
    final static String PASSWORD;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("configuration.database");
        DRIVER = resourceBundle.getString("db.driver");
        URL = resourceBundle.getString("db.url");
        LOGIN = resourceBundle.getString("db.login");
        PASSWORD = resourceBundle.getString("db.password");
        POOL_SIZE = Integer.valueOf(resourceBundle.getString("db.poolsize"));
    }
}


class Test{


    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("configuration.database");

    }

}