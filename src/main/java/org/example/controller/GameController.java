package org.example.controller;

import org.example.model.Game;
import org.example.service.GameService;

import java.util.Scanner;

public class GameController {

    private final GameService gameService;
    private final Scanner scanner;

    public GameController(GameService gameService, Scanner scanner) {
        this.gameService = gameService;
        this.scanner = scanner;
    }

    public Runnable saveGame() {
        return () -> {
            try {
                Game.GameBuilder builder = Game.builder();
                fillGameFields(builder, null);
                Game newGame = builder.build();
                Game saved = gameService.save(newGame);

                System.out.println("Game has been saved!");
                System.out.println(saved);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        };
    }

    public Runnable updateGame(int id) {
        return () -> {
            try {
                Game existing = gameService.findById(id);
                if (existing == null) {
                    System.out.println("Game not found!");
                    return;
                }

                Game.GameBuilder builder = Game.builder()
                        .title(existing.getTitle())
                        .genre(existing.getGenre())
                        .price(existing.getPrice())
                        .rating(existing.getRating())
                        .description(existing.getDescription())
                        .releaseDate(existing.getReleaseDate())
                        .addedDate(existing.getAddedDate())
                        .id(existing.getId());

                fillGameFields(builder, existing);
                Game updatedGame = builder.build();

                int result = gameService.update(updatedGame);
                System.out.println("Game has been updated! Number of rows affected: " + result);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        };
    }

    private void fillGameFields(Game.GameBuilder gameBuilder, Game existing) {
        boolean isUpdate = existing != null;
        for (GameCard field : GameCard.values()) {
            while (true) {
                try {
                    String currentValue = isUpdate ? getCurrentFieldValue(existing, field) : null;
                    System.out.println(field.get() + (currentValue != null ? " [current: " + currentValue + "]" : "") + ":");

                    String userInput = scanner.nextLine().trim();
                    if (isUpdate && userInput.isEmpty()) {
                        break;
                    }

                    switch (field) {
                        case TITLE -> gameBuilder.title(GameValidator.parseNonEmpty(userInput));
                        case GENRE -> gameBuilder.genre(GameValidator.parseNonEmpty(userInput));
                        case PRICE -> gameBuilder.price(GameValidator.parsePositiveDouble(userInput));
                        case RATING -> {
                            if (!userInput.isEmpty()) {
                                gameBuilder.rating(GameValidator.parsePositiveDouble(userInput));
                            }
                        }
                        case DESCRIPTION -> gameBuilder.description(userInput);
                        case RELEASE_DATE -> gameBuilder.releaseDate(GameValidator.parseDate(userInput));
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Wrong input: " + e.getMessage());
                }
            }
        }
    }

    private String getCurrentFieldValue(Game game, GameCard field) {
        if (game == null) return null;

        return switch (field) {
            case ID -> String.valueOf(game.getId());
            case TITLE -> game.getTitle();
            case GENRE -> game.getGenre();
            case PRICE -> String.valueOf(game.getPrice());
            case RATING -> String.valueOf(game.getRating());
            case DESCRIPTION -> game.getDescription();
            case RELEASE_DATE -> game.getReleaseDate() != null ? game.getReleaseDate().toString() : null;
            case ADDED_DATE -> game.getAddedDate() != null ? game.getAddedDate().toString() : null;
        };
    }
}