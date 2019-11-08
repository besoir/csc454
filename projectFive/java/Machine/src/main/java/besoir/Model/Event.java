package besoir.Model;

public class Event implements Comparable<Event> {
    private Time time;
    private char coin;

    public Event(Time t, char c) {
        this.time = t;
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
        return 0;
    }

    @Override
    public String toString() {
        return "Time: " + this.time + " Coin: " + this.coin;
    }
}