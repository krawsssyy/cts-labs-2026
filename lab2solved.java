import java.util.ArrayList;
import java.util.List;
// also properly format everything inside
public class LibraryApp {
    static List<Book> books = new ArrayList<>();

    static class Book { // separate the 3 lists into an inner class
        // in order to avoid carrying the 3 lists throughout the program and making it more complex, when it didn't need to be
        // KISS
        String title;
        String author;
        boolean available;

        Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.available = true;
        }
    }

    public static void main(String[] args) {
        books.add(new Book("Clean Code", "Robert Martin"));
        books.add(new Book("Effective Java", "Joshua Bloch"));
        books.add(new Book("The Pragmatic Programmer", "David Thomas"));

        printBooks();

        borrow(0, "Alice");
        borrow(1, "Bob");

        printBooks();

        returnBook(0);

        printBooks();
    }

    static void borrow(int i, String person) {
        Book book = books.get(i);
        if (book.available) {
            book.available = false;
            System.out.println(person + " borrowed " + book.title);
        } else {
            System.out.println(book.title + " is not available");
        }
    }

    static void returnBook(int i) {
        Book book = books.get(i);
        if (!book.available) {
            book.available = true;
            System.out.println(book.title + " was returned");
        } else {
            System.out.println(book.title + " was not borrowed");
        }
    }

    static void printBooks() {
        System.out.println("All books:");
        for (Book book : books) {
            System.out.println("Title: " + book.title);
            System.out.println("Author: " + book.author);
            System.out.println("Available: " + book.available);
            System.out.println("---");
        }
        printFiltered(true);
        printFiltered(false);
    }

    static void printFiltered(boolean showAvailable) { // DRY method
        // fix the previous 2 almost identical pieces of code, which were differing just by the condition (whether it was negated or not)
        // implement a single method that takes in a bool and decides which path we take
        System.out.println(showAvailable ? "Available books:" : "Borrowed books:");
        for (Book book : books) {
            if (book.available == showAvailable) {
                System.out.println("- " + book.title + " by " + book.author);
            }
        }
    }
}