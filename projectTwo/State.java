import java.text.DecimalFormat;

public class State {
    DecimalFormat df = new DecimalFormat("#.##");
    int quarters, dimes, nickels, value;
    boolean change;
    public State(int q, int d, int n, boolean c) {
        this.quarters = q;
        this.dimes = d;
        this.nickels = n;
        this.change = c;
        this.value = 0;
    }

    public State(State s, int q, int d, int n, int v, boolean c) {
        this.quarters = s.getQuanCoins('q') + q;
        this.dimes = s.getQuanCoins('d') + d;
        this.nickels = s.getQuanCoins('n') + n;
        this.change = c;
        this.value = s.getValue() + this.makeValue(q, d, n) - v;
    }

    public int makeValue(int q, int d, int n) {
        //System.out.println(q + ", " + d + ", " + n);
        return ((q * 25) + (d * 10) + (n * 5)); 
    }

    public int getQuanCoins(char i) {
        int quant = 0;
        switch(i) {
            case 'q':
                quant = this.quarters;
                break;
            case 'd':
                quant = this.dimes;
                break;
            case 'n':
                quant = this.nickels;
                break;
        }
        return quant;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isPressed() {
        return this.change;
    }

    @Override
    public String toString() {
        return "Quarters: " + this.quarters + " Dimes: " + this.dimes + " Nickels: " + this.nickels + " Value: $" + df.format((float)this.value/100);
    }
}