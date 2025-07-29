package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Game {
    private int id;
    private String name;
    private double price;
    private int rating;
    private String category;
    private String description;
    private LocalDate releaseDate;
    private LocalDate addedDate;
}
