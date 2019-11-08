package besoir.Framework;

import java.util.Collection;

public interface Model<I,O> {
    void lambda();
    void delta();
    void setOutPort(Port<O> p);
    Port<O> getOutPort();
    Collection<Port<I>> getInPorts();

}