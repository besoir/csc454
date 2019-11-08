package besoir.Framework;

import java.util.List;

public abstract class NetworkModel<I,O> implements Model<I,O> {
    protected List<Model<I,O>> models;
    protected BinaryHeap<I> bh1;
    protected BinaryHeap<O> bh2;

    public void addModel(Model<I,O> mod) {
        models.add(mod);
    }
}