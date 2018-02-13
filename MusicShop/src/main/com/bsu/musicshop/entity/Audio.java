package main.com.bsu.musicshop.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audio {

    private int id;
    private String title;
    private double price;
    private double discount;
    private String imageUrl;
    private String artist;
    private String album;

}
