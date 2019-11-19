package besoir.Model;

import besoir.Framework.*;
import java.util.ArrayList;

public class MachineNetwork extends NetworkModel<Integer,Integer> {
    Machine press, drill;
    private double lastTime;
    public MachineNetwork() {
        models = new ArrayList<>();
        bh = new BinaryHeap();
        lastTime = 0.0;
        
        this.press = new Machine(0, 0.0, 1.0);
        this.drill = new Machine(0, 0.0, 2.0);
    }

    public void run(String event) {
        //Event e = new Event(press, Double.parseDouble(event.substring(1, event.indexOf(","))), 0.0);
        double time = Double.parseDouble(event.substring(1, event.indexOf(",")));
        int amountEnt = Integer.parseInt(event.substring(event.indexOf(",") + 1, event.length()-1));

        //if((lastTime == 0.0 || bh.peek().getTime().getReal() - time <= 0.0) && amountEnt != 0) {
            //enter the amount of things into the binary heap
            bh.insert(new Event(press, time, 0, amountEnt));
            //for(int i = 1; i <= amountEnt; i++) {
                //bh.insert(new Event(press, time + i, 0, 0));
            //}
        //}
            //System.out.println("-- Heap ----------------------------");
            //System.out.println(bh);
            //System.out.println("-- End ----------------------------\n");

            while(!bh.isNull() && bh.peek().getTime().getReal() <= time) {
                Event e = bh.remove();
                //System.out.println("here");
                //System.out.println("Removed: " + e);
                //System.out.println("-- Heap ----------------------------");
                //System.out.println(bh);
                //System.out.println("-- End ----------------------------\n");
                if(lastTime != 0.0) {
                    if(e.getCount() == 0) {
                        //System.out.println(e + " " + e.getMachine().getType());
                        e.getMachine().confluentDelta(amountEnt);
                        if(e.getMachine().getType() == 1.0) {
                            if(e.getMachine().getCount() > 0) {
                                bh.insert(new Event(press, e.getTime().getReal(), 0, 1));
                                bh.insert(new Event(press, e.getTime().getReal() + 1.0, 0, 0));
                            }
                            bh.insert(new Event(drill, e.getTime().getReal(), 0, e.getMachine().lambda()));
                            bh.insert(new Event(drill, e.getTime().getReal() + 2.0, 0, 0));
                        } else {
                            //System.out.println(e);
                            System.out.println(e.getMachine().lambda());
                        }
                        //System.out.println("internal");
                        e.getMachine().internalDelta();
                    } else if(e.getMachine().getAlarm() == e.getTime().getReal() - lastTime) {
                        //System.out.println(e.getMachine());
                        if(e.getMachine().getType() == 1.0) {
                            if(e.getMachine().getCount() > 0) {
                                bh.insert(new Event(press, e.getTime().getReal(), 0, amountEnt));
                                bh.insert(new Event(press, e.getTime().getReal() + 1.0, 0, 0));
                            }
                            bh.insert(new Event(drill, e.getTime().getReal(), 0, e.getMachine().lambda()));
                            bh.insert(new Event(drill, e.getTime().getReal() + 2.0, 0, 0));
                        } else {
                            //System.out.println(e);
                            System.out.println(e.getMachine().lambda());
                        }
                        //System.out.println("confluent");
                        
                    }
                } else if(e.getCount() > 0) {
                    //System.out.println(e.getMachine());
                    //System.out.println("external");
                    e.getMachine().externalDelta(e.getTime().getReal() - time, e.getCount());
                    if(e.getMachine().getCount() > 0) {
                        bh.insert(new Event(e.getMachine(), e.getTime().getReal(), 0, amountEnt));
                        bh.insert(new Event(e.getMachine(), e.getTime().getReal() + e.getMachine().getType(), 0, 0));
                    }
                }
                lastTime = e.getTime().getReal();
            }

            //System.out.println("-- Heap ----------------------------");
            //System.out.println(bh);
            //System.out.println("-- End ----------------------------\n");
            //System.out.println("post");
            lastTime = time;
        //}
    }

    @Override
    public Integer lambda() {
        System.out.println(1);
        return 1;
    }

    @Override
    public void internalDelta() {
        //if we want to do the internal delta on the press
        models.get(1).internalDelta();
        //if we want to do the external delta on the press
        models.get(1).externalDelta(1.0, 1);
        //if we want to do the confluent delta on the press
        models.get(1).confluentDelta(1);
    }

    @Override
    public void externalDelta(double e, int q) {

    }

    @Override
    public void confluentDelta(int q) {

    }
}