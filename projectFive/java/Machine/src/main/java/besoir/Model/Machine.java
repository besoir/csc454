package besoir.Model;

import besoir.Framework.*;

public class Machine extends AtomicModel<Integer, Integer> {
    private double timeAd;
    public Machine(int p, double s, double t) {
        this.s = new State(p, s);
        this.timeAd = t;
    }
    
    @Override
    public void internalDelta() {
        this.s = new State(s.getParts() - 1, timeAd);
        timeAdvance();
    }

    @Override
    public void externalDelta(double e, int q) {
        if(s.isEmpty()) {
            this.s = new State(s.getParts() + q, timeAd);
        } else {
            this.s = new State(s.getParts() + q, s.getTime() - e);
        }
        timeAdvance();
    }

    @Override
    public void confluentDelta(int q) {
        this.s = new State(s.getParts() + q - 1, timeAd);
        timeAdvance();
    }

    @Override
    public Integer lambda() {
        return 1;
    }

    public double getType() {
        return this.timeAd;
    }

    public int getCount() {
        return this.s.getParts();
    }

    @Override
    public String toString() {
        String ret = "Machine: ";
        if(timeAd == 1.0) {
            ret += "Press ";
        } else {
            ret += "Drill ";
        }
        return ret;
    }
}