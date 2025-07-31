package org.example.repository;

public enum SQLQueries {

    ADD("""
            INSERT INTO games(title, genre, price, rating, description, release_date)
            VALUES (?, ?, ?, ?, ?, ?);
            """),
    UPDATE(""" 
            UPDATE games SET title=?, genre=?, price=?, rating=?, description=?, release_date=?, added_date=? 
            WHERE  id = ?; 
            """),
    FIND_BY_TITLE("SELECT * FROM games WHERE title LIKE ?"),
    FIND_BY_ID("SELECT * FROM games WHERE id = ?"),
    FIND_ALL("SELECT * FROM games"),
    FIND_BY_GENRE("SELECT * FROM games WHERE genre = ?"),
    FILTER_BY_PRICE("SELECT * FROM games WHERE price > ?"),
    FILTER_BY_RATING("SELECT * FROM games WHERE rating > ?"),
    REMOVE_BY_ID("DELETE FROM games WHERE id = ?"),
    SORTED_BY_DATE("SELECT * FROM games ORDER BY added_date ASC");

    private final String query;

    SQLQueries(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
