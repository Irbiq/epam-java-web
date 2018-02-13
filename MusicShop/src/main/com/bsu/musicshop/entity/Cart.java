package main.com.bsu.musicshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Cart {

    int userId;
    Set<Audio> audios;

}
