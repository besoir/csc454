package besoir;

public class Event {
    private char coin;
    private double time;

    public Event(double t, char c) {
        this.time = t;
        this.coin = c;
    }

    public double getTime() {
        return this.time;
    }

    public char getCoin() {
        return this.coin;
    }

    @Override
    public String toString() {
        return this.time + " " + this.coin;
    }
}