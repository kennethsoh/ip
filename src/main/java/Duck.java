import java.util.Scanner;
import java.util.ArrayList;

public class Duck {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        System.out.println("_________________________________________________");
        System.out.println("Hello! I'm DUCK");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________________");
        String userInput = scan.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("_________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("     " + (i + 1) + ".["+list.get(i).getStatusIcon()+"] " + list.get(i).getDescription());
                }
                System.out.println("_________________________________________________");
            } else if (userInput.startsWith("mark")) {
                String[] input = userInput.split(" ");
                int number = Integer.parseInt(input[1]);
                Task task = list.get(number - 1);
                task.mark();
                String status = task.getStatusIcon();
                String description = task.getDescription();
                System.out.println("_________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("     ["+status+"] "+description);
                System.out.println("_________________________________________________");

            } else if (userInput.startsWith("unmark")) {
                String[] input = userInput.split(" ");
                int number = Integer.parseInt(input[1]);
                Task task = list.get(number - 1);
                task.unmark();
                String status = task.getStatusIcon();
                String description = task.getDescription();
                System.out.println("_________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("     ["+status+"] "+description);
                System.out.println("_________________________________________________");

            } else {
                System.out.println("_________________________________________________");
                System.out.println("     added: "+userInput);
                System.out.println("_________________________________________________");
                list.add(new Task(userInput));
            }
            userInput = scan.nextLine();
        }
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________");
    }
}
