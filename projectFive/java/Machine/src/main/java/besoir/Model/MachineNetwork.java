package besoir.Model;

import besoir.Framework.*;
import java.util.ArrayList;

public class MachineNetwork extends NetworkModel<Integer,Integer> {
    public MachineNetwork() {
        models = new ArrayList<>();
        bh1 = new BinaryHeap<>();
        bh2 = new BinaryHeap<>();
        
        models.add(new Press(0, 0.0));
        models.add(new Drill(0, 0.0));
    }

    @Override
    public Integer lambda() {
        models.forEach(m -> m.lambda());

        //replace this with our output from the network
        return 1;
    }

    @Override
    public void delta(Integer i) {
        bh1.insert(i);
        models.forEach(m -> {
            m.delta(bh1.getMin());
        });
    }
}