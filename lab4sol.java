// 1. ISP

// create an interface for each appropriate type of action, thus specializing each interface as much as possible
interface PowerOperations {
    void powerOn();
    void powerOff();
}

interface PrintOperations {
    void print(String document);
}

interface ScanOperations {
    void scan(String content);
}

interface FaxOperations {
    void fax(String content, String destination);
}

interface NetworkOperations {
    void connectToWifi(String network, String password);
}

// each class will then implement only the required interfaces, thus removing unnecessary generalizations made
class Printer implements PowerOperations, PrintOperations, NetworkOperations {
    @Override
    public void powerOn() {
        System.out.println("Printer powered on");
    }

    @Override
    public void powerOff() {
        System.out.println("Printer powered off");
    }

    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }

    @Override
    public void connectToWifi(String network, String password) {
        System.out.println("Connecting printer to WiFi: " + network);
    }
}

class Scanner implements PowerOperations, ScanOperations, NetworkOperations {
    @Override
    public void powerOn() {
        System.out.println("Scanner powered on");
    }

    @Override
    public void powerOff() {
        System.out.println("Scanner powered off");
    }

    @Override
    public void scan(String content) {
        System.out.println("Scanning: " + content);
    }

    @Override
    public void connectToWifi(String network, String password) {
        System.out.println("Connecting scanner to WiFi: " + network);
    }
}

class AllInOnePrinter implements PowerOperations, PrintOperations, ScanOperations,
        FaxOperations, NetworkOperations {
    @Override
    public void powerOn() {
        System.out.println("All-in-one device powered on");
    }

    @Override
    public void powerOff() {
        System.out.println("All-in-one device powered off");
    }

    @Override
    public void print(String document) {
        System.out.println("All-in-one printing: " + document);
    }

    @Override
    public void scan(String content) {
        System.out.println("All-in-one scanning: " + content);
    }

    @Override
    public void fax(String content, String destination) {
        System.out.println("All-in-one faxing: " + content + " to " + destination);
    }

    @Override
    public void connectToWifi(String network, String password) {
        System.out.println("Connecting all-in-one device to WiFi: " + network);
    }
}

// 2. DIP

// make notifications an interface
interface NotificationChannel {
    void sendNotification(String recipient, String subject, String message);
    String getChannelType();
}

// now the low-level modules depend on interfaces, rather than being their own implementations
class EmailNotificationChannel implements NotificationChannel {
    @Override
    public void sendNotification(String recipient, String subject, String message) {
        System.out.println("Sending email to " + recipient + " with subject: " + subject);
        System.out.println("Message: " + message);
    }

    @Override
    public String getChannelType() {
        return "email";
    }
}

class SmsNotificationChannel implements NotificationChannel {
    @Override
    public void sendNotification(String recipient, String subject, String message) {
        System.out.println("Sending SMS to " + recipient);
        System.out.println("Message: " + message);
    }

    @Override
    public String getChannelType() {
        return "sms";
    }
}

// high-level module that depends on abstractions
class NotificationService {
    private final Map<String, NotificationChannel> channels;

    public NotificationService() {
        this.channels = new HashMap<>();
    }

    public void registerChannel(NotificationChannel channel) {
        // dependency injection of the abstractions, instead of specific implementations
        channels.put(channel.getChannelType(), channel);
    }

    public void sendNotification(String recipient, String subject, String message, String channelType) {
        NotificationChannel channel = channels.get(channelType);

        if (channel == null) {
            throw new IllegalArgumentException("Unsupported notification channel: " + channelType);
        }

        channel.sendNotification(recipient, subject, message);
    }
}