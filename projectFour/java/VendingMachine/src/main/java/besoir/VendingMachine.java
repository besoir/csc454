package besoir;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.text.DecimalFormat;

public class VendingMachine {
    private State s;
    private int numCoffees;
    private double alarm, time, e;
    private Stack<Event> events;
    DecimalFormat df = new DecimalFormat("0.##");

    public VendingMachine(int q, int d, int n) {
        events = new Stack<>();
        s = new State(q, d, n, 0);
        numCoffees = 0;
        time = e = 0.0;
        alarm = Double.POSITIVE_INFINITY;
    }

    public void lambda() throws VendingMachineException {
        System.out.println("Returned: {" + IntStream.range(0, numCoffees).mapToObj(i -> "c").collect(Collectors.joining()) + produceChange() + "}");
    }

    public void internalDelta() throws VendingMachineException {
        // this is when we take away the change
        int[] arr = getQuarters(s.getValue() - (100 * numCoffees));
        s = new State(s.getCoinQuant('q') - arr[0], s.getCoinQuant('d') - arr[1], s.getCoinQuant('n') - arr[2], 0);
        timeAdvance();
        numCoffees = 0;
    }

    public void externalDelta(char c) throws VendingMachineException {
        switch (c) {
        case 'q':
            s = new State(s.getCoinQuant('q') + 1, s.getCoinQuant('d'), s.getCoinQuant('n'), s.getValue() + 25);
            break;
        case 'd':
            s = new State(s.getCoinQuant('q'), s.getCoinQuant('d') + 1, s.getCoinQuant('n'), s.getValue() + 10);
            break;
        case 'n':
            s = new State(s.getCoinQuant('q'), s.getCoinQuant('d'), s.getCoinQuant('n') + 1, s.getValue() + 5);
            break;
        }
        timeAdvance();
    }

    public void confluentDelta(char c) throws VendingMachineException {
        int[] arr = getQuarters(s.getValue() - (100 * numCoffees));
        switch(c) {
            case 'q':
                s = new State(s.getCoinQuant('q') - arr[0] + 1, s.getCoinQuant('d') - arr[1], s.getCoinQuant('n') - arr[2], 25);
                break;
            case 'd':
                s = new State(s.getCoinQuant('q') - arr[0], s.getCoinQuant('d') - arr[1] + 1, s.getCoinQuant('n') - arr[2], 10);
                break;
            case 'n':
                s = new State(s.getCoinQuant('q') - arr[0], s.getCoinQuant('d') - arr[1], s.getCoinQuant('n') - arr[2] + 1, 5);
                break;
        }
        timeAdvance();
        numCoffees = 0;
    }

    public void run(String given) throws VendingMachineException {
        numCoffees = (int)Math.floor(s.getValue() / 100);
        //System.out.println(s);

        double nextTime = Double.parseDouble(given.substring(1, given.indexOf(",")));
        char c = given.charAt(given.indexOf(",") + 1);
        e = Double.parseDouble(df.format(nextTime - time));
        //System.out.println(e);

        if(e > 0.0) {
            //System.out.println(e);
            if(e > 2.0)  {
                events.push(new Event(nextTime, c));
                events.push(new Event(time + 2.0, ' '));
            } else {
                events.push(new Event(nextTime, c));
            }      
        }
    
        while(events.getCount() > 0) {
            //System.out.println(events);
            Event ev = events.pop();
            if(Double.parseDouble(df.format(ev.getTime()-time)) == alarm && ev.getCoin() != ' ') {
                lambda();
                confluentDelta(ev.getCoin());
            } else if(Double.parseDouble(df.format(ev.getTime()-time)) == alarm) {
                lambda();
                internalDelta();
            } else  {
                externalDelta(ev.getCoin());
            }
        }

        if(e > 0.0)
            time = nextTime;
    }

    private void timeAdvance() {
        if (s.getValue() > 0) {
            this.alarm = 2;
        } else {
            this.alarm = Double.POSITIVE_INFINITY;
        }
    }

    private String produceChange() throws VendingMachineException {
        String build = "";
        int tot = s.getValue() - (100 * numCoffees);
        int[] comb = getQuarters(tot);
        build += IntStream.range(0, comb[0]).mapToObj(i -> "q").collect(Collectors.joining());
        build += IntStream.range(0, comb[1]).mapToObj(i -> "d").collect(Collectors.joining());
        build += IntStream.range(0, comb[2]).mapToObj(i -> "n").collect(Collectors.joining());
        return build;
    }

    private int[] getQuarters(int val) throws VendingMachineException {
        int rQ = (int) Math.floor(val / 25);
        val = val - (rQ * 25);
        if (rQ > (s.getCoinQuant('q'))) {
            int pb = rQ - s.getCoinQuant('q');
            rQ -= pb;
            val += (pb * 25);
        }
        int[] place = getDimes(val);
        int[] ret = { rQ, place[0], place[1] };
        return ret;
    }

    private int[] getDimes(int val) throws VendingMachineException {
        int rD = (int) Math.floor(val / 10);
        val = val - (rD * 10);
        if (rD > s.getCoinQuant('d')) {
            int pb = rD - s.getCoinQuant('d');
            rD -= pb;
            val += (pb * 10);
        }
        int[] ret = { rD, getNickels(val) };
        return ret;
    }

    private int getNickels(int val) throws VendingMachineException {
        int rN = (int) Math.floor(val / 5);
        val -= rN * 5;
        if (rN > s.getCoinQuant('n')) {
            // we can drop this down here because were always going to
            throw new VendingMachineException("Vending Machine Out of Order, Please Contact Manager");
        }
        return rN;
    }

    @Override
    public String toString() {
        return s.toString();
    }
}