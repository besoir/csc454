package besoir.Framework;

public class State {
    private int parts;
    private double time;
    public State(int parts, double time) {
        this.parts = parts;
        this.time = time;
    }

    public int getParts() {
        return this.parts;
    }

    public double getTime() {
        return this.time;
    }

    public boolean isEmpty() {
        return this.parts == 0;
    }
}