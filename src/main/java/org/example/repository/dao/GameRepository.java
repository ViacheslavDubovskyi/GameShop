package org.example.repository.dao;

import org.example.model.Game;

import java.util.List;

public interface GameRepository {

    Game save(Game game);

    Game getById(int id);

    Game findByName(String name);

    int update(Game game);

    boolean remove(int id);

    List<Game> filterByPrice(double min, double max);

    List<Game> filterByCategory(String category);

    List<Game> sortedByAddedDate();

    List<Game> findAll();
}


