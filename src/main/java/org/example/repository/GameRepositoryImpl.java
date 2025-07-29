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
    public Game findByName(String name) {
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
    public List<Game> filterByPrice(double min, double max) {
        return List.of();
    }

    @Override
    public List<Game> filterByCategory(String category) {
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
