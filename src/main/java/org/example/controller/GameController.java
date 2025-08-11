package org.example.controller;

import org.example.model.Game;
import org.example.service.GameService;

import java.util.List;
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

    public Runnable updateGame() {
        return () -> {
            while (true) {
                try {
                    System.out.println(GameCard.ID.get());
                    String userId = scanner.next();
                    if (userExit(userId)) return;

                    int id = GameValidator.parsePositiveInt((userId));
                    Game existing = gameService.findById(id);
                    Game.GameBuilder builder = getBuilder(existing);
                    fillGameFields(builder, existing);
                    Game updatedGame = builder.build();

                    int result = gameService.update(updatedGame);
                    System.out.println("Game has been updated! Number of rows affected: " + result);
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.err.println(e.getMessage());
                } catch (Exception e) {
                    System.err.println("An error occurred while updating the game: " + e.getMessage());
                }
            }
        };
    }

    public Runnable deleteGame() {
        return () -> {
            System.out.println(GameCard.ID.get());
            String userId = scanner.next();
            int id = GameValidator.parsePositiveInt((userId));
            int status = gameService.deleteById(id);

            if (status > 0) {
                System.out.println("Game with ID " + userId + " has been deleted! Status: " + status);
            } else {
                System.out.println("No game found with ID " + userId + ".");
            }
        };
    }

    public Runnable getByTitle() {
        return () -> {
            System.out.println(GameCard.TITLE.get());
            String userTitle = scanner.next();
            String title = GameValidator.parseNonEmpty(userTitle);
            List<Game> games = gameService.findByTitle(title);

            System.out.println("Games with title " + title + ":");
            games.forEach(System.out::println);
        };
    }

    public Runnable getById() {
        return () -> {
            System.out.println(GameCard.ID.get());
            String userId = scanner.next();
            int id = GameValidator.parsePositiveInt((userId));
            Game game = gameService.findById(id);
            System.out.println("Game with ID " + userId + ": " + game);
        };
    }

    public Runnable getAll() {
        return () -> {
            List<Game> games = gameService.findAll();
            System.out.println("List of all games:");
            games.forEach(System.out::println);
        };
    }

    public Runnable filterByGenre() {
        return () -> {
            System.out.println(GameCard.GENRE.get());
            String userGenre = scanner.next();
            String genre = GameValidator.parseNonEmpty(userGenre);
            List<Game> games = gameService.filterByGenre(genre);

            System.out.println("List of all games by genre " + genre + ":");
            games.forEach(System.out::println);
        };
    }

    public Runnable filterByPrice() {
        return () -> {
            System.out.println(GameCard.PRICE.get());
            String userPrice = scanner.next();
            double price = GameValidator.parsePositiveDouble(userPrice);
            List<Game> games = gameService.filterByPrice(price);

            System.out.println("List of all games with price lower than" + price + ":");
            games.forEach(System.out::println);
        };
    }

    public Runnable filterByRating() {
        return () -> {
            System.out.println(GameCard.RATING.get());
            String userRating = scanner.next();
            double rating = GameValidator.parsePositiveDouble(userRating);
            List<Game> games = gameService.filterByRating(rating);

            System.out.println("List of all games with rating higher than" + rating + ":");
            games.forEach(System.out::println);

        };
    }

    public Runnable sortedByAdding() {
        return () -> {
            List<Game> games = gameService.sortedByAddedDate();
            System.out.println("List of all games sorted by adding date:");
            games.forEach(System.out::println);
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

                    if (userExit(userInput)) return;
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

    private Game.GameBuilder getBuilder(Game existing) {
        return Game.builder()
                .title(existing.getTitle())
                .genre(existing.getGenre())
                .price(existing.getPrice())
                .rating(existing.getRating())
                .description(existing.getDescription())
                .releaseDate(existing.getReleaseDate())
                .addedDate(existing.getAddedDate())
                .id(existing.getId());
    }

    private boolean userExit(String userInput) {
        if (userInput.equalsIgnoreCase("exit")) {
            System.out.println("Operation is cancelled.");
            return true;
        }
        return false;
    }
}