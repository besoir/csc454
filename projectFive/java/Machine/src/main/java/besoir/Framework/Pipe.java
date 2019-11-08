package besoir.Framework;
public class Pipe<T> {
    private Port<T> portTo, portFrom;

    public Pipe(Port<T> from, Port<T> to) {
        this.portFrom = from;
        this.portTo = to;
    }

    public void delta() {
        portTo.setVal(portFrom.sendVal());
    }

    @Override
    public String toString() {
        return this.portFrom + " " + this.portTo + " " + portTo.sendVal();
    }
}