#ifndef STATE_H
#define STATE_H

class State {
    private:
        int quarters, nickels, dimes, value;
        bool change;

    public:
        int makeValue(int q, int d, int n);
        int getQuanCoins(char c);
        int getValue();
        bool isPressed();
        State(int q, int d, int n, bool c);
        State(State* s, int q, int d, int n, int v, bool c);
};

#endif