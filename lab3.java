// 1. SRP

public class Book {
	String title, author, publisher, text;
	Map<String, Integer> countWords(), countLines();
	int getTotalWords();
	void printToConsole();
	void saveToFile();
	void saveToDB();
	+ getters/setters
}

// 2. OCP

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

// 3. LSP

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