package org.example.repository;

public enum HQLQueries {

    UPDATE("UPDATE Game g SET g.title = :title, g.genre = :genre, g.price = :price, " +
            "g.rating = :rating, g.description = :description, g.releaseDate = :releaseDate, " +
            "g.addedDate = :addedDate WHERE g.id = :id"),
    FIND_BY_TITLE("FROM Game g WHERE lower(g.title) LIKE :title"),
    FIND_ALL("FROM Game"),
    FIND_BY_GENRE("FROM Game g WHERE g.genre = :genre"),
    FILTER_BY_PRICE("FROM Game g WHERE g.price > :price"),
    FILTER_BY_RATING("FROM Game g WHERE g.rating > :rating"),
    REMOVE_BY_ID("DELETE FROM Game g WHERE g.id = :id"),
    SORTED_BY_DATE("FROM Game g ORDER BY g.addedDate ASC");

    private final String query;

    HQLQueries(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
