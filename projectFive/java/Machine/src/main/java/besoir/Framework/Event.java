package besoir.Framework;

import besoir.Model.*;

public class Event implements Comparable<Event> {
    private int count;
    private Machine m;
    private Time time;

    public Event(Machine m, double tr, double ti, int ent) {
        //logic for whether imaginary time is needed
        this.time = new Time(tr, ti);
        this.m = m;
        this.count = ent;
    }

    public int getCount() {
        return this.count;
    }

    public Time getTime() {
        return this.time;
    }

    public Machine getMachine() {
        return this.m;
    }

    @Override
    public int compareTo(Event e) {
        if(this.time.getReal() < e.getTime().getReal()) {
            return -1;
        } else if(this.time.getReal() == e.getTime().getReal() && this.m.equals(e.getMachine())){
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Machine: " + this.m + " Time: " + this.time;
    }
}