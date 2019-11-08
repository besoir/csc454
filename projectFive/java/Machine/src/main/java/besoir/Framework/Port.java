package besoir.Framework;
public class Port<T> {
    String owner;
    private T val;
    
    public Port(String o, T v) {
        this.owner = o;
        this.val = v;
    }

    public T sendVal() {
        return this.val;
    }

    public void setVal(T v) {
        this.val = v;
    }

    @Override
    public String toString() {
        return this.owner;
    }
}