package org.example.repository;

import org.example.config.ConnectionPool;
import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.sql.*;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {

    @Override
    public Game save(Game game) {
        Game savedGame = null;
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD.get(),
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            setGame(game, ps);
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                game.setId(generatedKeys.getInt(1));
                savedGame = game;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return savedGame;
    }

    @Override
    public Game getById(int id) {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQLQueries.FIND_BY_ID.get())) {

            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                return getGame(resultSet);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Game findByTitle(String title) {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQLQueries.FIND_BY_TITLE.get())) {

            ps.setString(1, title);
            try (ResultSet resultSet = ps.executeQuery()) {
                return getGame(resultSet);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public int update(Game game) {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQLQueries.UPDATE.get())) {

            setGame(game, ps);
            ps.setDate(7, Date.valueOf(game.getAddedDate()));
            ps.setInt(8, game.getId());
            return ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void setGame(Game game, PreparedStatement ps) throws SQLException {
        ps.setString(1, game.getTitle());
        ps.setString(2, game.getGenre());
        ps.setDouble(3, game.getPrice());
        ps.setDouble(4, game.getRating());
        ps.setString(5, game.getDescription());
        ps.setDate(6, Date.valueOf(game.getReleaseDate()));
    }

    private Game getGame(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Game.builder()
                    .title(resultSet.getString("title"))
                    .genre(resultSet.getString("genre"))
                    .price(resultSet.getDouble("price"))
                    .rating(resultSet.getDouble("rating"))
                    .description(resultSet.getString("description"))
                    .releaseDate(resultSet.getDate("release_date").toLocalDate())
                    .addedDate(resultSet.getDate("added_date").toLocalDate())
                    .id(resultSet.getInt("id"))
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public boolean remove(int id) {
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQLQueries.REMOVE_BY_ID.get())) {

            ps.setInt(1, id);
            return ps.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
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
