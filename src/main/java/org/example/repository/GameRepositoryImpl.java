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
    public Game getByID(int id) {
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
    public List<Game> filterByPrice() {
        return List.of();
    }

    @Override
    public List<Game> filterByCategory() {
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
