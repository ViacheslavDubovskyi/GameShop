package org.example;

import org.example.config.ConnectionPool;
import org.example.controller.GameController;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.dao.GameRepository;
import org.example.service.GameService;

import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

public class ConsoleApp {

    public static void start() {
        ConnectionPool pool = new ConnectionPool(
                "jdbc:postgresql://localhost:5432/gameshop",
                "postgres",
                "postgres");

        GameRepository repository = new GameRepositoryImpl(pool);
        GameService service = new GameService(repository);
        Scanner scanner = new Scanner(System.in);
        GameController controller = new GameController(service, scanner);

        Map<String, Runnable> commandRunner = init(controller);
        String userCommand;
        do {
            System.out.println(AppMessages.MENU.get());
            System.out.print("> ");
            userCommand = scanner.next();
            commandRunner
                    .getOrDefault(userCommand, () -> System.out.println(AppMessages.INVALID_INPUT.get()))
                    .run();

        } while (!userCommand.equals("exit"));
        System.out.println(AppMessages.FAREWELL.get());

        scanner.close();
        pool.shutdown();
    }

    public static Map<String, Runnable> init(GameController gameController) {
        return Map.ofEntries(
                entry("1", gameController.saveGame()),
                entry("2", gameController.getById()),
                entry("3", gameController.updateGame()),
                entry("4", gameController.deleteGame()),
                entry("5", gameController.getAll()),
                entry("6", gameController.getByTitle()),
                entry("7", gameController.filterByGenre()),
                entry("8", gameController.filterByPrice()),
                entry("9", gameController.filterByRating()),
                entry("10", gameController.sortedByAdding()),
                entry("exit", () -> System.exit(0))
        );
    }
}