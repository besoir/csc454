#ifndef VENDINGMACHINE_H
#define VENDINGMACHINE_H
#include <inttypes.h>
#include <iostream>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <vector>
using namespace std;

class Event;
class State;
class VendingMachine {
    private:
        double alarm, time, e;
        int num_coffees;
	    vector<Event*> stack;
        State* s;
    public:
	void run(string given);
        void lambda();
        void internal_delta();
        void external_delta(char c);
        void confluent_delta(char c);
        void time_advance();
        std::string produce_change();
        int get_quarters(int val);
        int get_dimes(int val);
        int get_nickels(int val);
        void destruct();
        VendingMachine(int q, int d, int n);
};

#endif
