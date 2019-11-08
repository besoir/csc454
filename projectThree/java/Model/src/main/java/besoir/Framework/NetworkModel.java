package besoir.Framework;

import java.util.ArrayList;
import java.util.Collection;

public abstract class NetworkModel<I,O> implements Model<I,O> {
    public NetworkModel() {}
    protected Collection<Model<I,O>> models = new ArrayList<>();
    protected Port<O> outP;

    public void addModel(Model<I,O> mod) {
        models.add(mod);
    }

    @Override
    public void setOutPort(Port<O> p) {
        // TODO Auto-generated method stub
        this.outP = p;

    }

    @Override
    public Port<O> getOutPort() {
        // TODO Auto-generated method stub
        return this.outP;
    }

    @Override
    public Collection<Port<I>> getInPorts() {
        // TODO Auto-generated method stub
        return this.getInPorts();
    }
}