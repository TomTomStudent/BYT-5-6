package Mediator_Pattern;

import java.util.ArrayList;
import java.util.List;

interface ATCInterface {
    void registerPlane(Aircraft aircraft);
    void send(String message, Aircraft sender);
}

interface Aircraft {
    void receive(String message);
    void send(String message);
}

class Plane implements Aircraft {
    private final ATCInterface mediator;
    private final String name;

    public Plane(ATCInterface mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    @Override
    public void receive(String message) {
        System.out.println(name + " received: " + message);
    }

    @Override
    public void send(String message) {
        System.out.println(name + " sending: " + message);
        mediator.send(message, this);
    }
}

class ATC implements ATCInterface {
    private final List<Aircraft> aircraftList;

    public ATC() {
        this.aircraftList = new ArrayList<>();
    }

    @Override
    public void registerPlane(Aircraft aircraft) {
        aircraftList.add(aircraft);
    }

    @Override
    public void send(String message, Aircraft sender) {
        for (Aircraft aircraft : aircraftList) {
            if (aircraft != sender) {
                aircraft.receive(message);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ATCInterface atc = new ATC();
        Aircraft airbus = new Plane(atc, "Airbus A330");
        Aircraft boeing = new Plane(atc, "Boeing 737");
        Aircraft cessna = new Plane(atc, "Cessna 172");

        atc.registerPlane(airbus);
        atc.registerPlane(boeing);
        atc.registerPlane(cessna);

        airbus.send("Skies looking beautiful today ladies and gentlemen, safe flights");
        System.out.println();
        boeing.send("Thanks JOHN");
        System.out.println();
        cessna.send("Yeah, we *love* you JOHN");
    }
}
