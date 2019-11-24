#ifndef ATOMICMODEL_HPP
#define ATOMICMODEL_HPP

#include "model.hpp"
#include "state.hpp"

template<typename I, typename O>
class AtomicModel : public Model<I, O>{
    protected:
        State *s;
        double alarm;
    public:
        virtual O lambda();
        virtual void internal_delta();
        virtual void external_delta(double e, int q);
        virtual void confluent_delta(int q);
        virtual void time_advance();
};
#endif