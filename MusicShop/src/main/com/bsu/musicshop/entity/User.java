package main.com.bsu.musicshop.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id ;
    private String role;
    private String name;
    private String surname;
    private String login;
    private String password;

    public User(int id, String name) {
    }
}
