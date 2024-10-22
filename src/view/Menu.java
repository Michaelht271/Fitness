package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> mChon;

    public Menu() {
    }

    public Menu(String title, String[] mc) {
        this.title = title;
        mChon = new ArrayList<>();
        for (String s : mc) {
            mChon.add((T) s);
        }
    }

    public void display() {
        System.out.println(title);
        for (int i = 0; i < mChon.size(); i++) {
            System.out.println((i + 1) + ". " + mChon.get(i));
        }
        System.out.println("--------------------------------");
    }

    public int getSelected() {
        Scanner sc = new Scanner(System.in);
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
                if (choice < 1 || choice > mChon.size()) {
                    System.out.println("Invalid choice. Please select between 1 and " + mChon.size());
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
            if (n <= mChon.size()) {
                execute(n);
            } else {
                break;
            }
        }
    }
}
