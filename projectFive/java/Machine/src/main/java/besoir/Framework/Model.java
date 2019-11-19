package besoir.Framework;

public interface Model<I,O> {
    O lambda();
    void internalDelta();
    void externalDelta(double e, int q);
    void confluentDelta(int q);
}