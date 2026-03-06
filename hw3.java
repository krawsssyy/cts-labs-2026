import java.util.ArrayList;
import java.util.List;

public class FineCalculator {

static final double BOOK_DAILY_FINE = 0.50;
static final double MAGAZINE_DAILY_FINE = 0.25;
    static final double DVD_DAILY_FINE = 1.00;
static final int GRACE_PERIOD_DAYS = 2;

    public static void main(String[] args) {
        List<String> types = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> borrowers = new ArrayList<>();
        List<Integer> daysOverdue = new ArrayList<>();

        types.add("Book"); titles.add("Clean Code"); borrowers.add("Alice"); daysOverdue.add(5);
        types.add("Magazine"); titles.add("IEEE Spectrum"); borrowers.add("Bob"); daysOverdue.add(3);
        types.add("Book"); titles.add("Effective Java"); borrowers.add("Alice"); daysOverdue.add(0);
        types.add("DVD"); titles.add("Inception"); borrowers.add("Charlie"); daysOverdue.add(2);
        types.add("Magazine"); titles.add("Nature"); borrowers.add("Bob"); daysOverdue.add(7);
        types.add("Book"); titles.add("The Pragmatic Programmer"); borrowers.add("Charlie"); daysOverdue.add(4);
        types.add("DVD"); titles.add("Interstellar"); borrowers.add("Alice"); daysOverdue.add(3);

        double totalFines = 0;

        for (int i = 0; i < titles.size(); i++) {
            if (daysOverdue.get(i) > GRACE_PERIOD_DAYS) {
            if (types.get(i).equals("Book")) {
            int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
            double fine = billableDays * BOOK_DAILY_FINE;
            if (billableDays > 7) fine += 2.00;
                    System.out.println(titles.get(i) + " (" + borrowers.get(i) + ") overdue by " + daysOverdue.get(i) + " days. Fine: $" + fine);
                totalFines += fine;
            } else if (types.get(i).equals("Magazine")) {
            int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
            double fine = billableDays * MAGAZINE_DAILY_FINE;
            if (billableDays > 7) fine += 2.00;
                    System.out.println(titles.get(i) + " (" + borrowers.get(i) + ") overdue by " + daysOverdue.get(i) + " days. Fine: $" + fine);
                totalFines += fine;
            } else if (types.get(i).equals("DVD")) {
            int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
            double fine = billableDays * DVD_DAILY_FINE;
            if (billableDays > 7) fine += 2.00;
                    System.out.println(titles.get(i) + " (" + borrowers.get(i) + ") overdue by " + daysOverdue.get(i) + " days. Fine: $" + fine);
                totalFines += fine;
            }
        }
        }

        System.out.println("\nTotal fines: $" + totalFines);

        System.out.println("\n=== Fines per Borrower ===");
        double aliceFine = 0;
        for (int i = 0; i < titles.size(); i++) {
            if (borrowers.get(i).equals("Alice") && daysOverdue.get(i) > GRACE_PERIOD_DAYS) {
                if (types.get(i).equals("Book")) {
                    int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
                    double fine = billableDays * BOOK_DAILY_FINE;
                    if (billableDays > 7) fine += 2.00;
                    aliceFine += fine;
                } else if (types.get(i).equals("Magazine")) {
                    int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
                    double fine = billableDays * MAGAZINE_DAILY_FINE;
                    if (billableDays > 7) fine += 2.00;
                    aliceFine += fine;
                } else if (types.get(i).equals("DVD")) {
                    int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
                    double fine = billableDays * DVD_DAILY_FINE;
                    if (billableDays > 7) fine += 2.00;
                    aliceFine += fine;
                }
            }
        }
        double bobFine = 0;
        for (int i = 0; i < titles.size(); i++) {
            if (borrowers.get(i).equals("Bob") && daysOverdue.get(i) > GRACE_PERIOD_DAYS) {
                if (types.get(i).equals("Book")) {
                    int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
                    double fine = billableDays * BOOK_DAILY_FINE;
                    if (billableDays > 7) fine += 2.00;
                    bobFine += fine;
                } else if (types.get(i).equals("Magazine")) {
                    int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
                    double fine = billableDays * MAGAZINE_DAILY_FINE;
                    if (billableDays > 7) fine += 2.00;
                    bobFine += fine;
                } else if (types.get(i).equals("DVD")) {
                    int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
                    double fine = billableDays * DVD_DAILY_FINE;
                    if (billableDays > 7) fine += 2.00;
                    bobFine += fine;
                }
            }
        }
        double charlieFine = 0;
        for (int i = 0; i < titles.size(); i++) {
            if (borrowers.get(i).equals("Charlie") && daysOverdue.get(i) > GRACE_PERIOD_DAYS) {
                if (types.get(i).equals("Book")) {
                    int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
                    double fine = billableDays * BOOK_DAILY_FINE;
                    if (billableDays > 7) fine += 2.00;
                    charlieFine += fine;
                } else if (types.get(i).equals("Magazine")) {
                    int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
                    double fine = billableDays * MAGAZINE_DAILY_FINE;
                    if (billableDays > 7) fine += 2.00;
                    charlieFine += fine;
                } else if (types.get(i).equals("DVD")) {
                    int billableDays = daysOverdue.get(i) - GRACE_PERIOD_DAYS;
                    double fine = billableDays * DVD_DAILY_FINE;
                    if (billableDays > 7) fine += 2.00;
                    charlieFine += fine;
                }
            }
        }
        System.out.println("Alice: $" + aliceFine);
        System.out.println("Bob: $" + bobFine);
        System.out.println("Charlie: $" + charlieFine);

        System.out.println("\n=== Member Discounts ===");
        double aliceDiscounted = aliceFine * (1 - 0.10);
        double savings = aliceFine - aliceDiscounted;
        System.out.println("Alice (Silver): $" + aliceDiscounted + " (saved $" + savings + ")");

        double bobDiscounted = bobFine * (1 - 0.20);
        double bobSavings = bobFine - bobDiscounted;
        System.out.println("Bob (Gold): $" + bobDiscounted + " (saved $" + bobSavings + ")");

        double charlieDiscounted = charlieFine * (1 - 0.10);
        double charlieSavings = charlieFine - charlieDiscounted;
        System.out.println("Charlie (Silver): $" + charlieDiscounted + " (saved $" + charlieSavings + ")");

        double charlieDiscountedGold = charlieFine * (1 - 0.20);
        double charlieSavingsGold = charlieFine - charlieDiscountedGold;
        System.out.println("Charlie (Gold): $" + charlieDiscountedGold + " (saved $" + charlieSavingsGold + ")");

        double charlieDiscountedPlatinum = charlieFine * (1 - 0.30);
        double charlieSavingsPlatinum = charlieFine - charlieDiscountedPlatinum;
        System.out.println("Charlie (Platinum): $" + charlieDiscountedPlatinum + " (saved $" + charlieSavingsPlatinum + ")");
    }
}