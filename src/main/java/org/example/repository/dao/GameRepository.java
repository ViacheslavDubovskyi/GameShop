package org.example.repository.dao;

import org.example.model.Game;

import java.util.List;

public interface GameRepository {

    Game save(Game game);

    Game getByID(int id);

    Game findByName(String name);

    int update(Game game);

    boolean remove(int id);

    List<Game> filterByPrice();

    List<Game> filterByCategory();

    List<Game> sortedByAddedDate();

    List<Game> findAll();
}


