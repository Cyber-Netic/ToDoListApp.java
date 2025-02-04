import java.io.*;
import java.util.*;

public class ToDoListApp {
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = loadTasks();

        while (true) {
            System.out.println("\nTo-Do List");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    saveTasks(tasks);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    viewTasks(tasks);
                    break;
                case 3:
                    viewTasks(tasks);
                    System.out.print("Enter task number to delete: ");
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > 0 && taskNumber <= tasks.size()) {
                        tasks.remove(taskNumber - 1);
                        saveTasks(tasks);
                        System.out.println("Task deleted successfully!");
                    } else {
                        System.out.println("Invalid task number!");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static List<String> loadTasks() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            System.out.println("No existing tasks found.");
        }
        return tasks;
    }

    private static void saveTasks(List<String> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private static void viewTasks(List<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
