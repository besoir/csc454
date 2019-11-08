package besoir.Model;

import besoir.Framework.*;

public class Drill extends AtomicModel<Integer, Integer> {
    public Drill(int p, double s) {
        this.s = new State(p, s);
    }

    private void internalDelta() {
        this.s = new State(s.getParts() - 1, 2.0);
        timeAdvance();
    }

    private void externalDelta(int q) {
        if(s.isEmpty()) {
            this.s = new State(s.getParts() + q, s.getTime());
        } else {
            this.s = new State(s.getParts() + q, 2.0);
        }
        timeAdvance();
    }

    private void confluentDelta(int q) {
        this.s = new State(s.getParts() + q - 1, 2.0);
        timeAdvance();
    }

    @Override
    public void delta(Integer i) {
        if(e < alarm) {
            externalDelta(i);
        } else if(e == alarm) {
            confluentDelta(i);
        } else {
            internalDelta();
        }
    }

    @Override
    public Integer lambda() {
        return 1;
    }
}