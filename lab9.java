import java.util.*;

interface FileSystemItem {
    void showDetails();
}

class File implements FileSystemItem {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public void showDetails() {
        System.out.println("File: " + name);
    }
}

class Folder implements FileSystemItem {
    private String name;
    private List<FileSystemItem> items = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemItem item) {
        items.add(item);
    }

    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemItem item : items) {
            item.showDetails();
        }
    }
}


/////////////////////////////

class TreeType {
    private String name;
    private String color;

    public TreeType(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void display(int x, int y) {
        System.out.println(name + " tree at (" + x + "," + y + ")");
    }
}


class TreeFactory {
    private static Map<String, TreeType> types = new HashMap<>();

    public static TreeType getTreeType(String name, String color) {
        String key = name + color;
        types.putIfAbsent(key, new TreeType(name, color));
        return types.get(key);
    }
}

class Tree {
    private int x, y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void display() {
        type.display(x, y);
    }
}



/////////////////////////

abstract class SupportHandler {
    protected SupportHandler next;

    public void setNext(SupportHandler next) {
        this.next = next;
    }

    public abstract void handleRequest(String issue);
}

class BasicSupport extends SupportHandler {
    public void handleRequest(String issue) {
        if (issue.equals("basic")) {
            System.out.println("Handled by basic support");
        } else if (next != null) {
            next.handleRequest(issue);
        }
    }
}

class AdvancedSupport extends SupportHandler {
    public void handleRequest(String issue) {
        if (issue.equals("advanced")) {
            System.out.println("Handled by advanced support");
        } else if (next != null) {
            next.handleRequest(issue);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SupportHandler basic = new BasicSupport();
        SupportHandler advanced = new AdvancedSupport();

        basic.setNext(advanced);

        basic.handleRequest("advanced");
    }
}

///////////////////////

interface Coffee {
    double cost();
}

class SimpleCoffee implements Coffee {
    public double cost() {
        return 5.0;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public double cost() {
        return coffee.cost() + 1.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public double cost() {
        return coffee.cost() + 0.5;
    }
}

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SugarDecorator(
                            new MilkDecorator(
                                new SimpleCoffee()));

        System.out.println(coffee.cost());
    }
}