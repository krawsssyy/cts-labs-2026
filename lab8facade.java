class DVDPlayer {
    void on() { System.out.println("DVD Player ON"); }
    void play() { System.out.println("Playing movie"); }
}

class Projector {
    void on() { System.out.println("Projector ON"); }
}

class SoundSystem {
    void on() { System.out.println("Sound System ON"); }
}

class Lights {
    void dim() { System.out.println("Lights dimmed"); }
}

class HomeTheaterFacade {
    private DVDPlayer dvd;
    private Projector projector;
    private SoundSystem sound;
    private Lights lights;

    public HomeTheaterFacade(DVDPlayer dvd, Projector projector,
                             SoundSystem sound, Lights lights) {
        this.dvd = dvd;
        this.projector = projector;
        this.sound = sound;
        this.lights = lights;
    }

    public void watchMovie() {
        lights.dim();
        projector.on();
        sound.on();
        dvd.on();
        dvd.play();
    }
}

public class Main {
    public static void main(String[] args) {
        HomeTheaterFacade theater = new HomeTheaterFacade(
            new DVDPlayer(),
            new Projector(),
            new SoundSystem(),
            new Lights()
        );

        theater.watchMovie();
    }
}