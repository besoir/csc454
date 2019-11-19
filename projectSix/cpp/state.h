#ifndef STATE_H
#define STATE_H

class State {
    private:
        int count;
        double time;

    public:
        int get_count();
        int get_time();
        State(int q, double s);
};

#endif