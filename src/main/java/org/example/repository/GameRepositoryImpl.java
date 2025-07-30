package org.example.repository;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.List;

public class GameRepositoryImpl implements GameRepository {

    @Override
    public Game save(Game game) {
        return null;
    }

    @Override
    public Game getById(int id) {
        return null;
    }

    @Override
    public Game findByTitle(String title) {
        return null;
    }

    @Override
    public int update(Game game) {
        return 0;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public List<Game> filterByPrice(double max) {
        return List.of();
    }

    @Override
    public List<Game> filterByGenre(String genre) {
        return List.of();
    }

    @Override
    public List<Game> sortedByAddedDate() {
        return List.of();
    }

    @Override
    public List<Game> findAll() {
        return List.of();
    }
}
