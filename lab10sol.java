import java.util.*;

// proxy

interface Video {
    void play();
}

class RealVideo implements Video {
    @Override
    public void play() {
        System.out.println("Playing current video...");
    }
}

class VideoProxy implements Video {
    private RealVideo realVideo; // real object
    private String role;
    public VideoProxy(String role) {
        this.role = role;
    }
    @Override
    public void play() {
        if (this.role.equals("premium")) { // access proxy
            if (this.realVideo == null) {
                this.realVideo = new RealVideo(); // virtual/lazy proxy
            }
            this.realVideo.play();
            System.out.println("Proxy passed!");
        }
        else {
            System.out.println("Not premium!");
        }

    }
}

// command

interface Command {
    void execute();
    void undo();
}

class VideoPlayer {
    public Boolean isOn = false;
    public Boolean isPlaying = false;
}

// various commands on the base object

class PlayCommand implements Command {
    private VideoPlayer videoPlayer;
    public PlayCommand(VideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }
    @Override
    public void execute() {
        this.videoPlayer.isOn = true;
        this.videoPlayer.isPlaying = true;
        System.out.println("Video playing!");
    }
    @Override
    public void undo() {
        this.videoPlayer.isPlaying = true;
        System.out.println("Undo playing!");
    }
}

class PauseCommand implements Command {
    private VideoPlayer videoPlayer;
    public PauseCommand(VideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }
    @Override
    public void execute() {
        if (!this.videoPlayer.isOn) {
            System.out.println("Not on, cannot pause!");
            return;
        }
        this.videoPlayer.isPlaying = false;
        System.out.println("Video paused!");
    }
    @Override
    public void undo() {
        if (!this.videoPlayer.isOn) {
            System.out.println("Not on, cannot pause!");
            return;
        }
        this.videoPlayer.isPlaying = true;
        System.out.println("Undo pause!");
    }
}

class StopCommand implements Command {
    private VideoPlayer videoPlayer;
    public StopCommand(VideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }
    @Override
    public void execute() {
        this.videoPlayer.isOn = false;
        this.videoPlayer.isPlaying = false;
        System.out.println("Video stopped!");
    }
    @Override
    public void undo() {
        this.videoPlayer.isOn = true;
        System.out.println("Undo stop!");
    }
}

// orchestrator

class VideoController {
    private Command cmd;
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack =  new Stack<>();

    public void setCmd(Command cmd) {
        this.cmd = cmd;
    }

    public void doCmd() {
        if (this.cmd != null) {
            this.cmd.execute();
            this.undoStack.add(this.cmd);
        }
    }

    public void undoCmd() {
        if (!this.undoStack.isEmpty()) {
            Command e =  undoStack.pop();
            e.undo();
            this.redoStack.add(e);
        }
    }

    public void redoCmd() {
        if (!this.redoStack.isEmpty()) {
            Command e =  redoStack.pop();
            e.execute();
            this.undoStack.add(e);
        }
    }
}

// strategy

interface Strategy {
    void getRoute(String src, String dest);
}

class FastestRoute implements Strategy {
    @Override
    public void getRoute(String src, String dest) {
        System.out.println("Fastest route from " + src + " to " + dest + "is computing...");
        System.out.println("Done, check your map!");
    }
}

class ShortestRoute implements Strategy {
    @Override
    public void getRoute(String src, String dest) {
        System.out.println("Shortest route from " + src + " to " + dest + "is computing...");
        System.out.println("Done, check your map!");
    }
}

class NoTollsRoute implements Strategy {
    @Override
    public void getRoute(String src, String dest) {
        System.out.println("No tolls route from " + src + " to " + dest + " is computing...");
        System.out.println("Done, check your map!");
    }
}

class MapStrategist {
    private Strategy strategy;
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public void executeRoute(String src, String dest) {
        this.strategy.getRoute(src, dest);
    }
}

// main

public class Main {
    public static void main(String[] args) {
        // proxy
        VideoProxy videoProxy = new VideoProxy("premium"); // we only use the proxy to access the base object
        videoProxy.play();

        VideoProxy videoProxy2 = new VideoProxy("non-premium");
        videoProxy2.play();

        // command

        VideoPlayer videoPlayer = new VideoPlayer();
        PlayCommand playCommand = new PlayCommand(videoPlayer);
        PauseCommand pauseCommand = new PauseCommand(videoPlayer);
        StopCommand stopCommand = new StopCommand(videoPlayer);
        VideoController videoController = new VideoController();

        videoController.setCmd(playCommand);
        videoController.doCmd();
        videoController.undoCmd();
        videoController.redoCmd();
        videoController.setCmd(pauseCommand);
        videoController.doCmd();
        videoController.setCmd(stopCommand);
        videoController.doCmd();
        videoController.undoCmd();
        videoController.undoCmd();
        videoController.redoCmd();
        videoController.redoCmd();
        videoController.undoCmd();
        videoController.redoCmd();

        // strategy
        MapStrategist mapStrategist = new MapStrategist();
        mapStrategist.setStrategy(new FastestRoute());
        mapStrategist.executeRoute("Bucuresti", "Cluj");
        mapStrategist.setStrategy(new ShortestRoute());
        mapStrategist.executeRoute("Bucuresti", "Cluj");
    }
}