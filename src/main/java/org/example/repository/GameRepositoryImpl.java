package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.model.Game;
import org.example.repository.dao.GameRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@AllArgsConstructor
public class GameRepositoryImpl implements GameRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Game save(Game game) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(game);
            transaction.commit();
            return game;
        }
    }

    @Override
    public Game getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Game.class, id);
        }
    }

    @Override
    public int update(Game game) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            int rows = session.createQuery(HQLQueries.UPDATE.get())
                    .setParameter("title", game.getTitle())
                    .setParameter("genre", game.getGenre())
                    .setParameter("price", game.getPrice())
                    .setParameter("rating", game.getRating())
                    .setParameter("description", game.getDescription())
                    .setParameter("releaseDate", game.getReleaseDate())
                    .setParameter("addedDate", game.getAddedDate())
                    .setParameter("id", game.getId())
                    .executeUpdate();

            transaction.commit();
            return rows;
        }
    }

    @Override
    public int remove(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int rows = session.createQuery(HQLQueries.REMOVE_BY_ID.get())
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
            return rows;
        }
    }

    @Override
    public List<Game> findByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            List<Game> games = session.createQuery(HQLQueries.FIND_BY_TITLE.get(), Game.class)
                    .setParameter("title", "%" + title + "%")
                    .getResultList();
            return games;
        }
    }

    @Override
    public List<Game> filterByPrice(double max) {
        try (Session session = sessionFactory.openSession()) {
            List<Game> games = session.createQuery(HQLQueries.FILTER_BY_PRICE.get(), Game.class)
                    .setParameter("price", max)
                    .getResultList();
            return games;
        }
    }

    @Override
    public List<Game> filterByGenre(String genre) {
        try (Session session = sessionFactory.openSession()) {
            List<Game> games = session.createQuery(HQLQueries.FIND_BY_GENRE.get(), Game.class)
                    .setParameter("genre", genre)
                    .getResultList();
            return games;
        }
    }

    @Override
    public List<Game> filterByRating(double rating) {
        try (Session session = sessionFactory.openSession()) {
            List<Game> games = session.createQuery(HQLQueries.FILTER_BY_RATING.get(), Game.class)
                    .setParameter("rating", rating)
                    .getResultList();
            return games;
        }
    }

    @Override
    public List<Game> sortedByAddedDate() {
        try (Session session = sessionFactory.openSession()) {
            List<Game> games = session.createQuery(HQLQueries.SORTED_BY_DATE.get(), Game.class)
                    .getResultList();
            return games;
        }
    }

    @Override
    public List<Game> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Game> games = session.createQuery(HQLQueries.FIND_ALL.get(), Game.class)
                    .getResultList();
            return games;
        }
    }
}
