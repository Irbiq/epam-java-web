package main.com.bsu.musicshop.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private int id;
    private int userId;
    private int audioId;
    private String text;
}
