package besoir.Model;

import besoir.Framework.*;

public class MemoryModel extends AtomicModel<Integer, Integer> {
    private int bit1;

    public MemoryModel() {
        this.bit1 = 0;
    }

    @Override
    public void lambda() {
        //this.outP.setVal(this.bit1);
        //System.out.println("MM: " + this.bit1);
    }

    public void delta() {
        int temp = this.bit1;
        for(Port<Integer> p : ports) {
            this.bit1 = p.sendVal();
        }
        this.outP.setVal(temp);
        //System.out.println("New Bits: " + this.outP.sendVal() + " " + this.bit1);
    }
}