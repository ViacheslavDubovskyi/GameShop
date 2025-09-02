package service;

import mock.GameRepositoryMock;
import org.example.model.Game;
import org.example.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameServiceTest {

    private GameRepositoryMock gameRepository;
    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameRepository = new GameRepositoryMock();
        gameService = new GameService(gameRepository);
        gameRepository.clear();
    }

    @Test
    public void save_success() {
        Game created = Game.builder().title("gta5").genre("action").build();
        Game saved = gameService.save(created);

        Assertions.assertNotNull(saved);
        assertEquals(created, saved);
    }

    @Test
    public void save_If_Null_throws_IllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameService.save(null);
        });
    }

    @Test
    public void find_ById_success() {
        int id = 10;
        Game created = Game.builder().id(id).build();
        Game saved = gameService.save(created);
        Game found = gameService.findById(saved.getId());

        Assertions.assertNotNull(found);
        assertEquals(saved, found);
    }

    @Test
    public void find_byId_throws_IllegalArgumentException() {
        int id = 99;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gameService.findById(id);
        });
    }

    @Test
    public void update_existingGame_success() {
        Game oldGame = Game.builder().id(1).title("gta5").price(10.0).build();
        gameService.save(oldGame);

        Game updated = Game.builder().id(1).title("gta6").price(20.0).build();
        int result = gameService.update(updated);

        assertEquals(1, result);
        Game saved = gameService.findById(1);
        Assertions.assertNotNull(saved);
        assertEquals("gta6", saved.getTitle());
        assertEquals(20.0, saved.getPrice());
    }

    @Test
    public void update_nonExistingGame_returns0() {
        Game game = Game.builder().id(99).title("unknown").build();
        int result = gameService.update(game);
        assertEquals(0, result);
    }

    @Test
    public void remove_game_byId() {
        Game created = Game.builder().id(1).build();
        gameRepository.save(created);
        int saved = gameRepository.remove(created.getId());

        assertEquals(1, saved);
    }

    @Test
    public void find_byTitle_returnList() {
        String target = "gta";

        String title1 = "gta5";
        String title2 = "gta4";

        List<Game> games = List.of(
                Game.builder().title(title1).build(),
                Game.builder().title(title2).build());
        games.forEach(gameService::save);

        List<Game> founded = gameService.findByTitle(target);
        assertEquals(2, founded.size());
        assertTrue(founded.stream().allMatch(g -> g.getTitle().contains(target)));
    }

    @Test
    public void find_byGenre_returnList() {
        String target = "action";

        String title1 = "gta5";
        String title2 = "gta4";

        List<Game> games = List.of(
                Game.builder().title(title1).genre(target).build(),
                Game.builder().title(title2).genre(target).build());
        games.forEach(gameService::save);

        List<Game> founded = gameService.filterByGenre(target);
        assertEquals(2, founded.size());
        assertTrue(founded.stream().allMatch(g -> g.getGenre().contains(target)));
    }

    @Test
    public void filter_byPrice_returnList() {
        double target = 10.3;

        double price1 = 5.6;
        double price2 = 15.5;

        List<Game> games = List.of(
                Game.builder().price(price1).build(),
                Game.builder().price(price2).build());
        games.forEach(gameService::save);

        List<Game> founded = gameService.filterByPrice(target);
        assertEquals(1, founded.size());
        assertTrue(founded.stream()
                .allMatch(g -> Double.compare(g.getPrice(), target) <= 0));
    }

    @Test
    public void filter_byRating_returnList() {
        double target = 8.2;

        double rating1 = 5.6;
        double rating2 = 9.3;

        List<Game> games = List.of(
                Game.builder().rating(rating1).build(),
                Game.builder().rating(rating2).build());
        games.forEach(gameService::save);

        List<Game> founded = gameService.filterByRating(target);
        assertEquals(1, founded.size());
        assertTrue(founded.stream()
                .allMatch(g -> Double.compare(g.getRating(), target) <= 0));
    }

    @Test
    public void sorted_byAddedDate_returnList() {
        Game g1 = Game.builder().title("gta5")
                .addedDate(LocalDate.of(2025, 1, 1)).build();
        Game g2 = Game.builder().title("horizon")
                .addedDate(LocalDate.of(2025, 2, 1)).build();
        Game g3 = Game.builder().title("cyberpunk")
                .addedDate(LocalDate.of(2024, 12, 15)).build();

        List<Game> games = List.of(g1, g2, g3);
        games.forEach(gameService::save);

        List<Game> sorted = gameService.sortedByAddedDate();
        List<Game> expected = List.of(g3, g1, g2);

        assertEquals(expected, sorted);
    }

    @Test
    public void find_All() {
        List<Game> games = List.of(
                Game.builder().title("gta5").price(5.6).build(),
                Game.builder().title("horizon").price(10.5).build());
        games.forEach(gameService::save);

        List<Game> allGames = gameService.findAll();
        assertEquals(2, allGames.size());
    }
}