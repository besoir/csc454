import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.lang.Math;

public class VendingMachine {
    State s;
    int numCoffees = 0;
    public VendingMachine(int q, int d, int n, boolean c) {
        s = new State(q, d, n, c);
    }

    //we want to make a new state second
    //get coffee first
    //produce change if wanted -> error check
    //produce the output
    //set a new state
    public void input(int q, int d, int n, boolean c, boolean w) throws VendingMachineException {
        lambda();
        delta(q, d, n, c);
    }

    public void lambda() throws VendingMachineException {
        String out = "";
        if(numCoffees > 0) {
            out += IntStream.range(0, numCoffees).mapToObj(i -> "c").collect(Collectors.joining());
        }
        if(s.isPressed()) {
            out += produceChange();
        }
        if(out.equals("")) {
            System.out.println("Returned: {nothing}");
        } else {
            System.out.println("Returned: {" + out + "}");
        }
        numCoffees = 0;
    }

    public void delta(int q, int d, int n, boolean c) {
        numCoffees = (int)Math.floor((s.getValue() + (q * 25) + (d * 10) + (n * 5)) / 100);
        int v = (numCoffees * 100);
        if(s.isPressed()) {
            v = s.getValue();
        }
        s = new State(s, q, d, n, v, c);
    }

    public String produceChange() throws VendingMachineException {
        String build = "";
        int tot = s.getValue();
        int[] comb = getQuarters(tot);
        build += IntStream.range(0, comb[0]).mapToObj(i -> "q").collect(Collectors.joining());
        build += IntStream.range(0, comb[1]).mapToObj(i -> "d").collect(Collectors.joining());
        build += IntStream.range(0, comb[2]).mapToObj(i -> "n").collect(Collectors.joining());
        return build;
    }

    public int[] getQuarters(int val) throws VendingMachineException {
        int rQ = (int)Math.floor(val / 25);
        val = val - (rQ * 25);
        if(rQ > (s.getQuanCoins('q'))) {
            int pb = rQ - s.getQuanCoins('q');
            rQ -= pb;
            val += (pb * 25);
        }
        int[] place = getDimes(val);
        int[] ret = {rQ, place[0], place[1], place[2]};
        return ret;
    }

    public int[] getDimes(int val) throws VendingMachineException {
        int rD = (int)Math.floor(val / 10);
        val = val - (rD * 10);
        if(rD > s.getQuanCoins('d')) {
            int pb = rD - s.getQuanCoins('d');
            rD -= pb;
            val += (pb * 10);
        }
        int[] place = getNickels(val);
        int[] ret = {rD, place[0], place[1]};
        return ret;
    }

    public int[] getNickels(int val) throws VendingMachineException {
        int rN = (int)Math.floor(val / 5);
        val -= rN * 5;
        if(rN > s.getQuanCoins('n')) {
            //we can drop this down here because were always going to
            throw new VendingMachineException("Vending Machine Out of Order, Please Contact Manager");
        }
        int[] ret = {rN, val};
        return ret;
    }

    @Override
    public String toString() {
        return s.toString();
    }
}