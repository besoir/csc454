package besoir.Framework;

public interface Model<I,O> {
    O lambda();
    void delta(I i);
}