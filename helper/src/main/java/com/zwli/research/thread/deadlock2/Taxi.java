package com.zwli.research.thread.deadlock2;

import com.zwli.research.thread.deadlock2.annotations.GuardedBy;

public class Taxi {

    @GuardedBy("this")
    private Point location, destination;

    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination)) {
            dispatcher.notifyAvailable(this);
        }
    }

}
