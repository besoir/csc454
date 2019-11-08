package besoir.Framework;

public abstract class AtomicModel<I, O> implements Model<I, O> {
    protected State s;
    protected double alarm, e;

    public void timeAdvance() {
        if(s.getParts() > 0) {
            alarm = s.getTime();
        } else {
            alarm = Double.POSITIVE_INFINITY;
        }
    }
}