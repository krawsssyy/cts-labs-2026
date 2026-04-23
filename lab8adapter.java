interface PaymentProcessor {
    void pay(double amount);
}

class LegacyPaymentSystem {
    public void makePayment(double value) {
        System.out.println("Paid " + value + " using legacy system");
    }
}

class PaymentAdapter implements PaymentProcessor {
    private LegacyPaymentSystem legacySystem;

    public PaymentAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void pay(double amount) {
        legacySystem.makePayment(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        LegacyPaymentSystem legacy = new LegacyPaymentSystem();
        PaymentProcessor processor = new PaymentAdapter(legacy);

        processor.pay(100.0);
    }
}