package main.com.bsu.musicshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Cart {

    int userId;
    List<Audio> audios;

}
