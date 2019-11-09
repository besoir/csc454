package besoir.Model;

public class Event implements Comparable<Event> {
    private Time time;
    private char coin;

    public Event(double t, char c) {
        //logic for whether imaginary time is needed
        this.time = new Time(t, 0);
        //this.time = t;
        this.coin = c;
    }

    public Time getTime() {
        return this.time;
    }

    public char getCoin() {
        return this.coin;
    }

    @Override
    public int compareTo(Event e) {
        if(time.getReal() < e.getTime().getReal()) {
            return -1;
        } else if(time.getReal() == e.getTime().getReal() && time.getImaginary() <= e.getTime().getImaginary()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Time: " + this.time + " Coin: " + this.coin;
    }
}