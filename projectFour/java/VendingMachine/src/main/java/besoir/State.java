package besoir;
public class State {
    int q, d, n, v;
    public State(int q, int d, int n, int v) {
        this.q = q;
        this.d = d;
        this.n = n;
        this.v = v;
    }

    public int getCoinQuant(char c) {
        int count = -1;
        switch(c) {
            case 'q':
                count = this.q;
                break;
            case 'd':
                count = this.d;
                break;
            case 'n':
                count = this.n;
                break;
        }
        return count;
    }

    public int getValue() {
        return this.v;
    }

    @Override
    public String toString() {
        return "Q: " + this.q + " D: " + this.d + " N: " + this.n + " V: " + this.v;
    }
}