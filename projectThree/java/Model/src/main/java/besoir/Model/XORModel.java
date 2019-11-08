package besoir.Model;

import besoir.Framework.*;

public class XORModel extends AtomicModel<Integer,Integer> {
    private int rBit;

    public XORModel() {
        //this.rBit = 0;
    }

    @Override
    public void lambda() {
        //System.out.println("XOR: " + this.outP.sendVal());
    }

    @Override
    public void delta() {
        int temp = -1;
        for(Port<Integer> p : ports) {
            //System.out.println(p.sendVal());
            if(temp == -1) {
                temp = p.sendVal();
            } else {
                this.rBit = temp ^ p.sendVal();
                //System.out.println("XOR Bits: " + temp + " " + p.sendVal() + " with new Result: " + this.rBit);
            }
        }
        this.outP.setVal(this.rBit);
    }
}