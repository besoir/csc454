#ifndef MACHINE_HPP
#define MACHINE_HPP

#include <stdlib.h>
#include "atomicmodel.hpp"
#include "state.hpp"
using namespace std;

class Machine : AtomicModel<int, int> {
    public:
        int lambda();
        void internal_delta();
        void external_delta(double e, int q);
        void confluent_delta(int q);
        void time_advance();
        Machine(double e, int q);
};
#endif