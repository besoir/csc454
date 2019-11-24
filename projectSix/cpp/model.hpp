#ifndef MODEL_HPP
#define MODEL_HPP

template <typename I, typename O>
class Model {
    public:
        virtual O lambda();
        virtual void internal_delta();
        virtual void confluent_delta(int q);
        virtual void external_delta(double e, int q);
};
#endif