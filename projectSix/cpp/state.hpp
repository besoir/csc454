#ifndef STATE_HPP
#define STATE_HPP

#include <stdlib.h>
#include <string>
#include <iostream>
using namespace std;

class State {
    private:
        int count;
        double time;

    public:
        int get_count();
        int get_time();
        void print_state();
        State(int q, double s);
};

#endif