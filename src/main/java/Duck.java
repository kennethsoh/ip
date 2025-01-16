import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("_________________________________________________");
        System.out.println("Hello! I'm DUCK");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________________");
        String userInput = scan.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println("_________________________________________________");
            System.out.println("     "+userInput);
            System.out.println("_________________________________________________");
            userInput = scan.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________");
    }
}
