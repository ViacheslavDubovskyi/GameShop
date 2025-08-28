package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "games")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "rating")
    private double rating;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "added_date", nullable = false)
    private LocalDate addedDate;
}
