import java.util.*;
import java.time.LocalDateTime;

public class CityParkSystem {

    // 1 — AUDIT LOG — Enum Singleton
    // the requirement says "tamper-proof" and "exactly one log"
    // enum prevents a second instance being created via serialisation or reflection, the two ways a regular Singleton can be broken
    public enum AuditLog {
        INSTANCE;

        private final List<String> entries = new ArrayList<>();

        public void record(String carPark, String action, String operator) {
            String entry = String.format("[LOG][%s] CAR PARK: %s | ACTION: %s | OPERATOR: %s", LocalDateTime.now(), carPark, action, operator);
            this.entries.add(entry);
            System.out.println(entry);
        }

        // return an unmodifiable list, such that no one can modify it externally
        public List<String> getEntries() {
            return Collections.unmodifiableList(this.entries);
        }

        public int count() {
            return this.entries.size();
        }
    }


    // 2 — REPORTING ENGINE — Lazy Singleton

    // the requirement explicitly says "should not be initialised unless a member of staff actually requests a report"
    // the engine is expensive to start and we defer that cost until first use, then reuse the same instance for every subsequent report
    // volatile + double-checked locking for thread safety
    public static final class ReportingEngine {

        private static volatile ReportingEngine instance; // lazy singleton instance

        // some "heavy" resources loaded at initialisation
        private final List<String> loadedTemplates;
        private final List<String> loadedFonts;

        private ReportingEngine() {
            System.out.println("[ReportingEngine] Initialising — loading templates and fonts...");

            // simulated expensive startup
            this.loadedTemplates = List.of("standard.tmpl", "summary.tmpl", "detailed.tmpl");
            this.loadedFonts = List.of("Arial", "Helvetica", "Courier");

            System.out.println("[ReportingEngine] Ready. Templates: " + this.loadedTemplates + " | Fonts: " + this.loadedFonts);
        }

        public static ReportingEngine getInstance() {
            if (instance == null) {
                synchronized (ReportingEngine.class) {
                    if (instance == null) {
                        instance = new ReportingEngine();
                    }
                }
            }
            return instance;
        }

        public void generate(String format, String carPark, String period) {
            System.out.printf("[ReportingEngine] Generating %s report | Car park: %s | Period: %s%n", format, carPark, period);

            switch (format.toUpperCase()) {
                case "PDF" -> System.out.println("Rendering PDF with template: " + this.oadedTemplates.get(0));
                case "CSV" -> System.out.println("Serialising data to CSV rows");
                case "SCREEN" -> System.out.println("Formatting summary for on-screen display");
                default -> throw new IllegalArgumentException("Unknown format: " + format);
            }

            AuditLog.INSTANCE.record(carPark, "REPORT_GENERATED (" + format + ")", "system");
        }
    }


    // 3 — CAR PARK CONFIG REGISTRY — Registry Singleton

    // there are multiple car parks, each with their own config, but they are all the same type. 
    // a registry gives us one managed access point for all of them and we avoid creating a separate Singleton class per car park (which violates DRY)
    // the registry itself is eager — it is always needed and cheap to create

    // the config to use in our registry
    public static final class CarParkConfig {
        private final String name;
        private final int capacity;
        private final double hourlyRate;
        private final String openingHours;

        public CarParkConfig(String name, int capacity, double hourlyRate, String openingHours) {
            this.name = name;
            this.capacity = capacity;
            this.hourlyRate = hourlyRate;
            this.openingHours = openingHours;
        }

        public String getName() { 
            return this.name; 
        }

        public int getCapacity() { 
            return this.capacity; 
        }

        public double getHourlyRate() { 
            return this.hourlyRate; 
        }

        public String getOpeningHours() { 
            return this.openingHours; 
        }
    }

    // the registry class — one instance (singleton), holds all car park configs (different classes, not itself), looked up by name 
    public static final class CarParkConfigRegistry {
        private static final CarParkConfigRegistry INSTANCE = new CarParkConfigRegistry(); // eager
        private final Map<String, CarParkConfig> configs = new HashMap<>(); // the registry

        private CarParkConfigRegistry() {}

        public static CarParkConfigRegistry getInstance() {
            return INSTANCE;
        }

        // we call this once at startup to register a car park's config
        public void register(String key, CarParkConfig config) {
            if (this.configs.containsKey(key)) {
                throw new IllegalStateException("Config already registered for: " + key);
            }
            this.configs.put(key, config);
            System.out.println("[Registry] Registered config for: " + key);
        }

        // get a config
        public CarParkConfig get(String key) {
            CarParkConfig config = this.configs.get(key);
            if (config == null) {
                throw new IllegalArgumentException("No config found for car park: " + key);
            }
            return config;
        }

        // get a list of all the configs
        public Set<String> registeredKeys() {
            return Collections.unmodifiableSet(configs.keySet());
        }

        // ideally we'd have all CRUD operations here (in a production app), but these suffice for this example
    }
}