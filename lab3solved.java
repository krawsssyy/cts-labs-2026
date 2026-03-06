import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

/* 1. S: Single Responsibility Principle
- Refactor the following class to adhere to SRP

public class Book {
	String title, author, publisher, text;
	Map<String, Integer> countWords(), countLines();
	int getTotalWords();
	void printToConsole();
	void saveToFile();
	void saveToDB();
	+ getters/setters
}
 */

class Book {
    public String title, author, publisher, text;
    // + getters/setters/constructor
} // only these attributes are specific to a book, and will make it adhere to SRP
// including other attributes will make the book class have additional functionalities which are not specific to a book, hence violating SRP

// the rest of the functionalities are grouped and moved in different classes
// mocked implementations, as only the explanation of the principle was required
class CounterStats { // statistics based on counting words, lines etc..
    Map<String, Integer> countWords(String text) {
        return new HashMap<String, Integer>();
    }

    Map<String, Integer> countLines(String text) {
        return new HashMap<String, Integer>();
    }

    Integer getTotalWords(String text) {
        return 0;
    }
}

class Printer { // printing to console class
    void printToConsole(String text) {
        System.out.println(text);
    }
}

class File { // saving to file class, we make a wrapper for it
    String filepath;

    public File(String filepath) {
        this.filepath = filepath;
    }
    Boolean open() {
        return true;
    }

    void close() {

    }

    Boolean write(String text) {
        return true;
    }
}

class DB { // similar for the DB as for the file, ideally this would be an interface
    String connectionString;

    public DB(String connectionString) {
        this.connectionString = connectionString;
    }
    Boolean open() {
        return true;
    }

    void close() {

    }

    Boolean addQuery(String text) {
        return true;
    }
}

/*
2. O: Open/Closed Principle
- Refactor the following code to adhere to OCP

public class Square {
	int side;
	int getSide();
	void setSide(int side);
}

public class Circle {
	int radius;
	int getRadius();
	void setRadius(int radius);
}

public class AreaCalculator() {
	int getArea(Object shape) {
		if (shape instanceof Square)
			return (Square)shape.getSide() * (Square)shape.getSide();
		else if (shape instanceof Circle)
			return (Circle)shape.getRadius() * (Circle)shape.getRadius() * Math.PI;
	}
}
 */
// instead of adding endlessly to our function in case new shapes are added, we make them implement an interface
// and ensure that each shape calculates its own area, such that we don't have to keep adding to the if case
// this way, we closed the classes for modifications, but opened everything for extension
// since adding a new shape won't modify any already existing code
interface Shape2D { // foreshadowing - also including I
    Double calculateArea();
}

class Square implements Shape2D {
    Double length;

    public Square(Double length) {
        this.length = length;
    }

    // + getter/setter

    @Override
    public Double calculateArea() {
        return this.length * this.length;
    }
}

class Circle implements Shape2D {
    Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    // + getter/setter

    @Override
    public Double calculateArea() {
        return this.radius * this.radius * Math.PI;
    }
}

class AreaCalculator {
    public Double getArea(Shape2D shape) {
        return shape.calculateArea();
    }
}


/*
3. L: Liskov Substitution Principle
- Refactor this code to adhere to LSP

public class Bird {
	void fly();
	void eat();
}

public class Duck extends Bird {
	@Override void fly();
	@Override void eat();
	void swim();
}

public class Ostrich extends Bird {
	@Override void fly();
	@Override void eat();
	void run();
}

public class Simulator {
	void simulateFly(List<Bird> birds) {
		for (Bird b: birds)
			b.fly();
	}
}
 */

// this code does not adhere to LSP since an ostrich can't fly, and this violates LSP in the sense that a child class doesn't behave like a parent class
// since we are supposed to be able to replace a parent class with a child class at any point in our program
// in our case, replacing an Bird with an Ostrich invalidates our existing logic due to the fact that an Ostrich can't fly
// to fix this, we have to make things more specific, in the sense that we have to differentiate between at least flying birds and non flying birds

abstract class Bird {
    abstract public void eat(); // keeping only the base function
}

interface FlyingBird {
    void fly();
}

interface RunningBird {
    void run();
}

interface SwimmingBird {
    void swim();
}

class Duck extends Bird implements FlyingBird, SwimmingBird {
    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }

    @Override
    public void swim() {

    }
}

class Ostrich extends Bird implements RunningBird {
    @Override
    public void eat() {

    }

    @Override
    public void run() {

    }
}

class Simulator {
    void simulateEat(List<Bird> birds) {
        for (Bird bird: birds) {
            bird.eat();
        }
    }

    void simulateFly(List<FlyingBird> birds) {
        for (FlyingBird bird: birds) {
            bird.fly();
        }
    }
    // .....
}