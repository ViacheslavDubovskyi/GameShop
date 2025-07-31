package org.example.service;

import org.example.model.Game;
import org.example.repository.GameRepositoryImpl;

import java.util.Optional;

public class GameService {

    private final GameRepositoryImpl gameRepository;

    public GameService(GameRepositoryImpl gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game save(Game game) {
        Game savedGame = gameRepository.save(game);
        return Optional.ofNullable(savedGame)
                .orElseThrow(() -> new IllegalStateException("Failed to save the game " + game.getTitle()));
    }

    public Game findById(int id) {
        Game game = gameRepository.getById(id);
        return Optional.ofNullable(game)
                .orElseThrow(() -> new IllegalStateException("No game exists with id: " + id));
    }

    public Game findByTitle(String title) {
        Game game = gameRepository.findByTitle(title);
        return Optional.ofNullable(game)
                .orElseThrow(() -> new IllegalStateException("No game exists with title: " + title));
    }

    public int update(Game game) {
        return gameRepository.update(game);
    }

    public boolean deleteById(int id) {
        return gameRepository.remove(id);
    }
}
