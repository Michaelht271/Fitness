package view;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> mChon;
    private static final Scanner sc = new Scanner(System.in); // Tạo một đối tượng Scanner duy nhất

    public Menu() {
        mChon = new ArrayList<>();
    }

    public Menu(String title, String[] mc) {
        this.title = title;
        mChon = new ArrayList<>();
        for (String s : mc) {
            mChon.add((T) s);
        }
    }

    public void display() {
        System.out.println("\n" + title);
        System.out.println("--------------------------------");
        for (int i = 0; i < mChon.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, mChon.get(i)); // Sử dụng printf để căn chỉnh
        }
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
    }

    public int getSelected() {
        int choice = -1;
        display();
        while (true) {
            System.out.print("Enter your choice: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
            try {
                choice = Integer.parseInt(input);
                if (choice < 0 || choice > mChon.size()) {
                    System.out.println("Invalid choice. Please select between 0 and " + mChon.size());
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return choice;
    }

    public abstract void execute(int choice);

    public void run() {
        while (true) {
            int n = getSelected();
            if (n == 0) {
                System.out.println("Exiting menu...");
                break; // Thoát khỏi menu
            }
            if (n <= mChon.size()) {
                execute(n);
            }
        }
    }
}
