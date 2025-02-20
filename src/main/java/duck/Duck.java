package duck;

import java.io.IOException;

import duck.exception.UnknownCommandException;

/**
 * The main class for the DUCK task manager application.
 * It initializes the required components and handles user interaction.
 */
public class Duck {

    private static final String FILE_PATH = "./data/duck.txt";
    private TaskHistory history;
    private Parser parser;
    private Storage storage;
    private TaskList list;
    private Ui ui;

    /**
     * Creates an instance of Duck
     */
    public Duck() throws IOException {
        storage = new Storage(FILE_PATH);
        parser = new Parser();
        ui = new Ui();
        list = new TaskList(storage.load());
        history = new TaskHistory();
    }

    /**
     * The main entry point of the DUCK application.
     *
     * @param args Command-line arguments (not used).
     * @throws UnknownCommandException If an unrecognized command is entered.
     * @throws IOException If an error occurs while reading or writing the storage file.
     */
    public static void main(String[] args) throws UnknownCommandException, IOException {

        Storage storage = new Storage(FILE_PATH);
        Parser parser = new Parser();
        Ui ui = new Ui();
        TaskList list = new TaskList(storage.load());

        ui.showWelcome();

        boolean isByeCommand = false;
        while (!isByeCommand) {
            String userInput = ui.readCommand();
            parser.parseCommand(userInput, list, ui);
            isByeCommand = userInput.equalsIgnoreCase("bye");
            storage.save(FILE_PATH, list);
        }
    }

    public String getResponse(String input) throws UnknownCommandException {
        String response = parser.parseCommand(input, list, ui);
        storage.save(FILE_PATH, list);
        return response;
    }
}
