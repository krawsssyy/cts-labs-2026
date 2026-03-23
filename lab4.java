// 1. ISP

interface DeviceOperations {
    void powerOn();
    void powerOff();
    void print(String document);
    void scan(String content);
    void fax(String content, String destination);
    void connectToWifi(String network, String password);
}

class Printer implements DeviceOperations {
    @Override
    public void powerOn() {
        Logger.getInstance().log("Printer powered on");
    }

    @Override
    public void powerOff() {
        Logger.getInstance().log("Printer powered off");
    }

    @Override
    public void print(String document) {
        Logger.getInstance().log("Printing: " + document);
    }

    @Override
    public void scan(String content) {
        throw new UnsupportedOperationException("This printer cannot scan");
    }

    @Override
    public void fax(String content, String destination) {
        throw new UnsupportedOperationException("This printer cannot fax");
    }

    @Override
    public void connectToWifi(String network, String password) {
        Logger.getInstance().log("Connecting printer to WiFi: " + network);
    }
}

class Scanner implements DeviceOperations {
    @Override
    public void powerOn() {
        Logger.getInstance().log("Scanner powered on");
    }

    @Override
    public void powerOff() {
        Logger.getInstance().log("Scanner powered off");
    }

    @Override
    public void print(String document) {
        throw new UnsupportedOperationException("This scanner cannot print");
    }

    @Override
    public void scan(String content) {
        Logger.getInstance().log("Scanning: " + content);
    }

    @Override
    public void fax(String content, String destination) {
        throw new UnsupportedOperationException("This scanner cannot fax");
    }

    @Override
    public void connectToWifi(String network, String password) {
        Logger.getInstance().log("Connecting scanner to WiFi: " + network);
    }
}

// 2. DIP

class EmailSender {
    public void sendEmail(String recipient, String subject, String message) {
        Logger.getInstance().log("Sending email to " + recipient + " with subject: " + subject);
        Logger.getInstance().log("Message: " + message);
    }
}

class SmsSender {
    public void sendSms(String phoneNumber, String message) {
        Logger.getInstance().log("Sending SMS to " + phoneNumber);
        Logger.getInstance().log("Message: " + message);
    }
}

class NotificationService {
    private EmailSender emailSender;
    private SmsSender smsSender;

    public NotificationService() {
        this.emailSender = new EmailSender();
        this.smsSender = new SmsSender();
        // tight coupling due to direct usage of specific modules
    }

    public void sendNotification(String to, String body, String message, String channel) {
        if ("email".equals(channel)) {
            emailSender.sendEmail(to, subject, message);
        } else if ("sms".equals(channel)) {
            smsSender.sendSms(to, message);
        } else {
            throw new IllegalArgumentException("Unsupported notification channel: " + channel);
        }
    }
}