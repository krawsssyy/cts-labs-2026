import java.util.ArrayList;
import java.util.List;

public class LibraryApp {
    static List<String> titles = new ArrayList<>();
    static List<String> authors = new ArrayList<>();
    static List<Boolean> available = new ArrayList<>();

    public static void main(String[] args) {
        titles.add("Clean Code");
        authors.add("Robert Martin");
        available.add(true);

        titles.add("Effective Java");
        authors.add("Joshua Bloch");
        available.add(true);

        titles.add("The Pragmatic Programmer");
        authors.add("David Thomas");
        available.add(true);

            printBooks();

        borrow(0, "Alice");
        borrow(1, "Bob");

            printBooks();

        returnBook(0);

        printBooks();
    }

    static void borrow(int i, String person) {
        if (available.get(i)) {
                    available.set(i, false);
            System.out.println(person + " borrowed " + titles.get(i));
        } else {
            System.out.println(titles.get(i) + " is not available");
        }
    }

    static void returnBook(int i) {
        if (!available.get(i)) {
            available.set(i, true);
            System.out.println(titles.get(i) + " was returned");
        } else {
                System.out.println(titles.get(i) + " was not borrowed");
        }
    }

    static void printBooks() {
        System.out.println("=== Books ===");
        for (int i = 0; i < titles.size(); i++) {
            System.out.println("Title: " + titles.get(i));
            System.out.println("Author: " + authors.get(i));
            System.out.println("Available: " + available.get(i));
            System.out.println("---");
        }
        System.out.println("Available books:");
        for (int i = 0; i < titles.size(); i++) {
            if (available.get(i)) {
            System.out.println("- " + titles.get(i) + " by " + authors.get(i));
        }}
        System.out.println("Borrowed books:");
        for (int i = 0; i < titles.size(); i++) {
            if (!available.get(i)) {
            System.out.println("- " + titles.get(i) + " by " + authors.get(i));
        }}
    }
}