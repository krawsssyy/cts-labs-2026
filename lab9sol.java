import java.util.*;

// composite

// this is a tree-like structure. and we use employees as the leaves, and then treat all the above as a set of objects
// this provides uniformity and allows us to manipulate groups of objects, regardless of their type/level in the tree
interface Item {
    void GetSalary();
}

class Employee implements Item { // for uniformity, we will have both the employee and the manager implement a common interface
    private String name;
    private Float salary;

    public Employee(String name, Float salary) {
        this.name = name;
        this.salary = salary;
    }


    @Override
    public void GetSalary() {
        System.out.println("\nEmployee salary is: " + this.salary);
    }
}

class Manager implements Item { // the manager also implements Item, for uniformity
    private String name;
    private Float salary;
    private List<Item> subordinates = new ArrayList<>(); // and it holds a list of its subordinates
    // due to us using Item as the list type, this list can hold other managers, each with their own lists, thus creating the tree structure we needed

    public Manager(String name, Float salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void GetSalary() {
        System.out.println("Manager salary is: " + this.salary);
        System.out.println("Subordinates salaries are:");
        for (Item subordinate : this.subordinates) {
            subordinate.GetSalary();
        }
    }

    public void AddSubordinate(Item subordinate) {
        this.subordinates.add(subordinate);
    }
}

// flyweight

// it allows to share parts of an object, in an attempt to avoid re-creating the same object over and over again
class CharacterStyle { // the common "attribates" that may or may not vary in our base objects are grouped in a class of their own
    private String font;
    private Integer size;

    public CharacterStyle(String font, Integer size) {
        this.font = font;
        this.size = size;
    }

    public void DisplayStyle() {
        System.out.println("Font is: " + this.font);
        System.out.println("Size is: " + this.size);
    }
}

class Character { // then, we construct our object by splitting its attributes - the ones that vary less are grouped and put in their own class
    // and we use that as an attribute
    // and then, we are left with the unique attributes (ones that always vary)
    private Integer position;
    private CharacterStyle style;
    private char symbol;

    public Character(Integer position, CharacterStyle style, char symbol) {
        this.position = position;
        this.style = style;
        this.symbol = symbol;
    }

    public void DisplayCharacter() {
        System.out.println("\nCharacter is: " + this.symbol);
        System.out.println("Position is: " + this.position);
        this.style.DisplayStyle();
    }
}

// in order to facilitate the re-use, we create a factory for the common attributes

class StyleFactory {
    private static Map<String, CharacterStyle> styles = new HashMap<>();

    public static CharacterStyle GetCharacterStyle(String font, Integer size) {
        String key = font + size.toString();
        CharacterStyle style = styles.get(key);
        if (style == null) {
            style = new CharacterStyle(font, size);
            System.out.println("\nNew style created");
            styles.put(key, style);
        }
        else {
            System.out.println("\nStyle already exists");
        }
        return style;
    }
}

// chain of responsibility

// by creating this chain, we are able to pass the request to multiple handlers, hoping we reach the appropriate one

abstract class SpamFilter { // we create this template for all handlers
    // such that we can make a linked-list-like structure out of them
    // if one is unable to handle our request, we send it to the next one in the chain
    // it allows us to define multiple handlers for specific issues, and letting the application decide which one to use for each situation
    // and not having us hardcode a lot of stuff to decide which one to use
    protected SpamFilter next;

    public void SetNext(SpamFilter next) {
        this.next = next;
    }

    public abstract void Filter(String content);
}

class KeywordsFilter extends SpamFilter {

    @Override
    public void Filter(String content) {
        if (content.contains("Prince of nigeria") || content.contains("I am an old lady") || content.contains("10 million dollars")) {
            System.out.println("\nMessage marked as spam by keywords");
            System.out.println(content);
        }
        else if (this.next != null) {
            System.out.println("\nMessage could not be filtered by keywords alone!");
            this.next.Filter(content);
        }
        else {
            System.out.println("\nEnd of the line - oops, this request wasn't handled :(");
        }
    }
}

class ReputationFilter extends SpamFilter {
    @Override
    public void Filter(String content) {
        if (content.contains(".so") ||  content.contains(".in") || content.contains(".ng")) {
            System.out.println("\nMessage marked as spam by sender reputation");
            System.out.println(content);
        }
        else if (this.next != null) {
            System.out.println("\nMessage could not be filtered by sender reputation");
            this.next.Filter(content);
        }
        else {
            System.out.println("\nEnd of the line - oops, this request wasn't handled :(");
        }
    }
}

class AIClassifierFilter extends SpamFilter {
    @Override
    public void Filter(String content) {
        if (new Random().nextInt(1000) % 2 == 0) {
            System.out.println("\nMessage marked as spam by AI classifier");
            System.out.println(content);
        }
        else if (this.next != null) {
            System.out.println("\nMessage could not be filtered by AI classifier");
            this.next.Filter(content);
        }
         else {
             System.out.println("\nEnd of the line - oops, this request wasn't handled :(");
        }
    }
}

// decorator

// useful for when we want to avoid making 1000 classes from a base class, for adding different options for it (and we cannot modify the original)

interface Pizza {
    void yummy();
}

class BasicPizza implements Pizza {
    @Override
    public void yummy() {
        System.out.println("Yummy Basic Pizza");
    }
}

abstract class PizzaDecorator implements Pizza { // we need this to implement pizza
    // because when extending for each decorator, having the pizza type will allow us to chain them
    protected Pizza pizza;
    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class PepperPizza extends PizzaDecorator {

    public PepperPizza(Pizza pizza) {
        super(pizza);
    }
    @Override
    public void yummy() {
        this.pizza.yummy();
        System.out.println("now with bell pepppers");
    }
}

class PepperoniPizza extends PizzaDecorator {

    public PepperoniPizza(Pizza pizza) {
        super(pizza);
    }
    @Override
    public void yummy() {
        this.pizza.yummy();
        System.out.println("now with pepperoni");
    }
}

class SaucePizza extends PizzaDecorator {

    public SaucePizza(Pizza pizza) {
        super(pizza);
    }
    @Override
    public void yummy() {
        this.pizza.yummy();
        System.out.println("now with tomato sauce");
    }
}

public class Main {
    public static void main(String[] args) {
        Employee emplOne = new Employee("Dave", 1500f);
        Employee emplTwo = new Employee("Anna", 1800f);
        Employee emplThree = new Employee("Michael", 1200f);

        Manager managerOne = new Manager("Monica", 2500f);
        Manager managerTwo = new Manager("David", 2300f);

        managerTwo.AddSubordinate(emplOne);
        managerTwo.AddSubordinate(emplTwo);
        managerOne.AddSubordinate(emplThree);
        managerOne.AddSubordinate(managerTwo);
        //                Monica
        //              /         \
        //            /             \
        //      David (manager)   Michael (empl)
        //        /     \
        //      Anna   Dave
        managerOne.GetSalary();
        // now, we can control an entire set of object just as if we'd treat one
        // and it also creates the appropriate tree-structure
        System.out.println("------------------------------");

        // by using the factory, we ensure we reuse styles we have already created, rather than re-creating them all over again
        Character chOne = new Character(1, StyleFactory.GetCharacterStyle("Times new roman", 16), 'H');
        Character chTwo = new Character(2, StyleFactory.GetCharacterStyle("Arial", 12), 'e');
        Character chThree = new Character(2, StyleFactory.GetCharacterStyle("Arial", 12), 'l');
        Character chFour = new Character(2, StyleFactory.GetCharacterStyle("Arial", 12), 'l');
        Character chTive = new Character(2, StyleFactory.GetCharacterStyle("Arial", 12), 'o');

        chOne.DisplayCharacter();
        chTwo.DisplayCharacter();
        chThree.DisplayCharacter();
        chFour.DisplayCharacter();
        chTive.DisplayCharacter();

        System.out.println("------------------------------");

        // it allows us to dynamically create the order of our filters, and even re-order them when necessary
        SpamFilter kf = new KeywordsFilter(); // root filter
        SpamFilter rf = new ReputationFilter();
        SpamFilter acf = new AIClassifierFilter();

        kf.SetNext(rf);
        rf.SetNext(acf);

        // keywords -> reputation -> ai

        kf.Filter("the Prince of Nigeria is offering you 10 million dollars if you just put your card details in this form");
        kf.Filter("From: lookatme@iamthecaptainnow.so\nSubject: Look at me\n Hey");
        kf.Filter("hopefully the ai doesn't catch this clear spam message 1");
        kf.Filter("hopefully the ai doesn't catch this clear spam message 2");
        kf.Filter("hopefully the ai doesn't catch this clear spam message 3");

        System.out.println("------------------------------");

        // using decorators we can now create our own pizza with whatever combos we want, rather than having 10s of classes for each possible combo
        // class explosion reduced exponentially

        Pizza pizzaOne = new SaucePizza(new PepperPizza(new BasicPizza()));
        Pizza pizzaTwo = new SaucePizza(new PepperoniPizza(new PepperPizza(new BasicPizza())));
        pizzaOne.yummy();
        pizzaTwo.yummy();
    }
}
