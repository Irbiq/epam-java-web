package main.com.bsu.musicshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private int id;
    private String artist;
    private String title;
    private String imageUrl;
}
