import java.util.Scanner;
import java.util.ArrayList;

public class Duck {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("_________________________________________________");
        System.out.println("Hello! I'm DUCK");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________________");
        String userInput = scan.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("_________________________________________________");
                int size = list.size();
                for (int i = 0; i< size; i++) {
                    System.out.println("     "+(i+1)+". "+list.get(i));
                }
                System.out.println("_________________________________________________");
            } else {
                System.out.println("_________________________________________________");
                System.out.println("     added: "+userInput);
                System.out.println("_________________________________________________");
                list.add(userInput);
            }
            userInput = scan.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________");
    }
}
