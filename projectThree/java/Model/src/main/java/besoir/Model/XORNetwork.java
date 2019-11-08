package besoir.Model;

import besoir.Framework.*;

import java.util.ArrayList;
import java.util.Collection;

public class XORNetwork extends NetworkModel<Integer,Integer> {
    //private Port<Integer> out;
    private Collection<Pipe<Integer>> pipes;
    private XORModel xor1, xor2;
    private MemoryModel mm;

    public XORNetwork(Port<Integer> p1, Port<Integer> p2) {
        outP = new Port<>("xor2 out", 0);
        pipes = new ArrayList<>();

        xor1 = new XORModel();
        models.add(xor1);
        mm = new MemoryModel();
        models.add(mm);
        xor2 = new XORModel();
        models.add(xor2);

        xor1.addInPort(new Port<Integer>("xor1 in1", 0));
        xor1.addInPort(new Port<Integer>("xor1 in2", 0));
        xor1.setOutPort(new Port<Integer>("xor1 out", 0));

        xor2.addInPort(new Port<Integer>("xor2 in1", 0));
        xor2.addInPort(new Port<Integer>("xor2 in2", 0));
        xor2.setOutPort(this.outP);

        mm.addInPort(new Port<Integer>("mm in", 0));
        mm.setOutPort(new Port<Integer>("mm out", 0));

        int i = 0;
        for(Port<Integer> p : xor1.getInPorts()) {
            if(i == 0) {
                pipes.add(new Pipe<Integer>(p1, p));
                i += 1;
            } else { 
                pipes.add(new Pipe<Integer>(p2, p));
            }
        }

        int j = 0;
        for(Port<Integer> p : xor2.getInPorts()) {
            if(j == 0) {
                pipes.add(new Pipe<Integer>(xor1.getOutPort(), p));
                j += 1;
            } else {
                pipes.add(new Pipe<Integer>(mm.getOutPort(),p));
            }
        }

        for(Port<Integer> p : mm.getInPorts()) {
            pipes.add(new Pipe<Integer>(xor2.getOutPort(), p));
        }

        //pipes.forEach(p -> System.out.println(p));
        
    }

    @Override
    public void lambda() {
        // TODO Auto-generated method stub
        models.forEach(m -> m.lambda());
        //System.out.println(out.sendVal());
        
    }

    @Override
    public void delta() {
        // TODO Auto-generated method stub
        
        
        models.forEach(m -> m.delta());
        pipes.forEach(p -> {
            p.delta();
            //System.out.println(p);
        });
        System.out.println(outP.sendVal());
    }
}