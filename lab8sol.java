// adapter

interface NewPrinterInterface {
    void print(String message); // the new interface which we want to use
}
class OldPrinter { // old legacy system which we aren't allowed to modify
    public void printText(String message, Boolean color) {
        String col  = color == true ? " with " : " without ";
        System.out.println("Printing " + message + col + "color via legacy system");
    }
}

class PrinterAdapter implements NewPrinterInterface { // adapter class
    // it will hold an attribute of the legacy class' type
    // then adapt the new format of the function to use the old one in its logic
    // such that we can still rely on the old logic, but use it under a new format
    private OldPrinter printer;

    public PrinterAdapter(OldPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        this.printer.printText(message, true); // we can assume that modern printers always print with color, so its fine to hardcode it
        // if not, we could have added another attribute for this class! (not function)
    }
}

// facade

class AirConditioner {
    public void TurnOn() {
        System.out.println("Turning AC on");
    }
    public void TurnOff() {
        System.out.println("Turning AC off");
    }
    public void SetTemp(Float temp) {
        System.out.println("Setting AC temperature to " + temp);
    }
}

class TV {
    public void TurnOn() {
        System.out.println("Turning TV on");
    }

    public void TurnOff() {
        System.out.println("Turning TV off");
    }

    public void SetChannel(String channel) {
        System.out.println("Setting TV channel to " + channel);
    }
}

class WiFiRouter {
    public void TurnOn() {
        System.out.println("Turning WiFi router on");
    }

    public void TurnOff() {
        System.out.println("Turning WiFi router off");
    }
}

// we have the 3 individual systems we want to link together for common usecases


class HomeSystemsFacade { // we make this class that encompasses the different systems and links them together
    // such that common usecases are easier to create, rather than having the user do them manually
    private AirConditioner airConditioner;
    private TV tv;
    private WiFiRouter wifiRouter;

    public HomeSystemsFacade(AirConditioner airConditioner, TV tv, WiFiRouter wifiRouter) {
        this.airConditioner = airConditioner;
        this.tv = tv;
        this.wifiRouter = wifiRouter;
    }

    public void comeHome() {
        System.out.println("Coming home");
        this.airConditioner.TurnOn();
        this.tv.TurnOn();
        this.wifiRouter.TurnOn();
    }

    public void leaveHome() {
        System.out.println("Leaving home");
        this.airConditioner.TurnOff();
        this.tv.TurnOff();
        this.wifiRouter.TurnOff();
    }

    public void watchNetflixSummer() {
        System.out.println("Watching Netflix in the summer");
        this.wifiRouter.TurnOn();
        this.airConditioner.TurnOn();
        this.airConditioner.SetTemp(18f);
        this.tv.TurnOn();
        this.tv.SetChannel("Netflix");
    }
}
public class Main {
    public static void main(String[] args) {
        // use the printing feature with the new interface, but still using legacy system
        PrinterAdapter printer = new PrinterAdapter(new OldPrinter());
        printer.print("Hello World");

        // instantiate the facade and use it for common usecases
        HomeSystemsFacade facade = new HomeSystemsFacade(new AirConditioner(), new TV(), new WiFiRouter());
        facade.leaveHome();
        facade.comeHome();
        facade.watchNetflixSummer();
    }
}
