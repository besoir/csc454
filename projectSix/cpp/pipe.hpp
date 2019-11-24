#ifndef PIPE_HPP
#define PIPE_HPP

#include "port.hpp"

template<typename T>
class Pipe {
    private:
        Port<T> in, out;
    public:
        void delta();
};
#endif