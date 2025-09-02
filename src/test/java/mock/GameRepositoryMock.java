package mock;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GameRepositoryMock implements GameRepository {

    private List<Game> games = new ArrayList<>();

    public GameRepositoryMock() {
    }

    public void clear() {
        games.clear();
    }

    @Override
    public Game save(Game game) {
        games.add(game);
        return game;
    }

    @Override
    public Game getById(int id) {
        return games.stream()
                .filter(game -> game.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int update(Game game) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == game.getId()) {
                games.set(i, game);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int remove(int id) {
        Iterator<Game> iterator = games.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<Game> findByTitle(String title) {
        return games.stream()
                .filter(game -> game.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> filterByPrice(double max) {
        return games.stream()
                .filter(game -> Double.compare(game.getPrice(), max) <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> filterByGenre(String genre) {
        return games.stream()
                .filter(game -> game.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> filterByRating(double max) {
        return games.stream()
                .filter(game -> Double.compare(game.getRating(), max) <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> sortedByAddedDate() {
        return games.stream()
                .sorted(Comparator.comparing(Game::getAddedDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<>(games);
    }
}
