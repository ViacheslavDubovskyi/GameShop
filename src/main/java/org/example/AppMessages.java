package org.example;

public enum AppMessages {

    MENU("""
            Hello! Choose option with typing command from the list:
            1 - Add new game.
            2 - Search game by ID.
            3 - Update existing game.
            4 - Delete game.
            5 - Show all games.
            6 - Search games by title.
            7 - Get games by genre.
            8 - Filter games by MAX price.
            9 - Filter games by MIN rating.
            10 - Sort games by added date.
            exit - Exit from the app.
            """),

    INVALID_INPUT("Invalid input!"),
    SAVED_SUCCESS("Game has been saved!"),
    SAVED_ERROR("Failed to save the game "),
    UPDATED_SUCCESS("Game has been updated! Number of rows affected: "),
    UPDATED_ERROR("An error occurred while updating the game: "),
    DELETED("Game has been deleted! Status: "),
    FOUND_BY_ID("Game with ID: "),
    FOUND_BY_ID_ERROR("No game found with ID "),
    FOUND_BY_TITLE("Games with title "),
    GET_ALL("List of all games:"),
    GET_BY_GENRE("List of all games by genre: "),
    GET_BY_TITLE("List of all games by title: "),
    GET_BY_PRICE("List of all games with price lower than "),
    GET_BY_RATING("List of all games with rating higher than "),
    SORTED_BY_DATE("List of all games sorted by adding date:"),
    CANCELED("Operation is cancelled."),
    FAREWELL("Thank you for using the app, bye!");

    private final String command;

    AppMessages(String command) {
        this.command = command;
    }

    public String get() {
        return command;
    }
}
