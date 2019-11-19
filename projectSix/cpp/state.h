#ifndef STATE_H
#define STATE_H

class State {
    private:
        int quarters, nickels, dimes, value;

    public:
        int coin_quan(char c);
        int get_value();
        State(int q, int d, int n, int v);
};

#endif