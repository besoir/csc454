package besoir.Framework;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AtomicModel<I,O> implements Model<I,O> {
    public AtomicModel() {}
    protected Collection<Port<I>> ports = new ArrayList<>();
    protected Port<O> outP;
    public void addInPort(Port<I> p) {
        ports.add(p);
    }
    @Override
    public void setOutPort(Port<O> p) {
        this.outP = p;
    }

    @Override
    public Port<O> getOutPort() {
        return this.outP;
    }

    @Override
    public Collection<Port<I>> getInPorts() {
        return ports;
    }
}