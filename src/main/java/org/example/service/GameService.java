package org.example.service;

import org.example.AppMessages;
import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.List;
import java.util.Optional;

public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game save(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null");
        }
        Game savedGame = gameRepository.save(game);
        return Optional.ofNullable(savedGame)
                .orElseThrow(() -> new IllegalArgumentException(AppMessages.SAVED_ERROR.get() + game.getTitle()));
    }

    public Game findById(int id) {
        Game game = gameRepository.getById(id);
        return Optional.ofNullable(game)
                .orElseThrow(() -> new IllegalArgumentException(AppMessages.FOUND_BY_ID_ERROR.get() + id));
    }

    public List<Game> findByTitle(String title) {
        return gameRepository.findByTitle(title);
    }

    public int update(Game game) {
        return gameRepository.update(game);
    }

    public int deleteById(int id) {
        return gameRepository.remove(id);
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public List<Game> filterByGenre(String genre) {
        return gameRepository.filterByGenre(genre);
    }

    public List<Game> sortedByAddedDate() {
        return gameRepository.sortedByAddedDate();
    }

    public List<Game> filterByPrice(double price) {
        return gameRepository.filterByPrice(price);
    }

    public List<Game> filterByRating(double rating) {
        return gameRepository.filterByRating(rating);
    }
}
