package org.example.controller;

public enum GameCard {

    ID("ID"),
    TITLE("title"),
    GENRE("genre"),
    PRICE("price"),
    RATING("rating"),
    DESCRIPTION("description"),
    RELEASE_DATE("releaseDate"),
    ADDED_DATE("addedDate");

    private final String field;

    GameCard(String field) {
        this.field = field;
    }

    public String get() {
        return "Enter the " + field + " of the game: ";
    }
}
