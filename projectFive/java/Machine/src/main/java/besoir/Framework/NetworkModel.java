package besoir.Framework;

import java.util.List;

public abstract class NetworkModel<I,O> implements Model<I,O> {
    protected List<Model<I,O>> models;
    protected BinaryHeap bh;

    public void addModel(Model<I,O> mod) {
        models.add(mod);
    }

    public abstract void run(String event);
}