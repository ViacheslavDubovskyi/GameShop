package org.example.repository.dao;

import org.example.model.Game;

import java.util.List;

public interface GameRepository {

    Game save(Game game);

    Game getById(int id);

    int update(Game game);

    int remove(int id);

    List<Game> findByTitle(String title);

    List<Game> filterByPrice(double max);

    List<Game> filterByGenre(String genre);

    List<Game> filterByRating(double max);

    List<Game> sortedByAddedDate();

    List<Game> findAll();
}


