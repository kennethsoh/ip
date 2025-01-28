package duck;
import duck.exception.EmptyDetailsException;
import duck.exception.UnknownCommandException;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class Duck {

    private static final String FILE_PATH = "./data/duck.txt";

    public static void main(String[] args) throws EmptyDetailsException, UnknownCommandException, IOException {

        Storage storage = new Storage(FILE_PATH);
        Parser parser = new Parser();
        Ui ui = new Ui();
        TaskList list = new TaskList(storage.load());

        ui.showWelcome();

        boolean checkBye = false;
        while (!checkBye) {
            String userInput = ui.readCommand();
            parser.parseCommand(userInput, list, ui);
            checkBye = userInput.equalsIgnoreCase("bye");
            storage.save(FILE_PATH, list);
        }
    }
}
