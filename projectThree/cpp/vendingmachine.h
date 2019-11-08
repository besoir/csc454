#ifndef VENDINGMACHINE_H
#define VENDINGMACHINE_H
#include <iostream>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>

class State;
class VendingMachine {
    private:
        int numCoffees;
        State* s;
    public:
        void input(int q, int d, int n, bool c, bool w);
        void lambda();
        void delta(int q, int d, int n, bool c);
        std::string produceChange();
        int getQuarters(int val);
        int getDimes(int val);
        int getNickels(int val);
        void destruct();
        VendingMachine(int q, int d, int n, bool c);
};

#endif