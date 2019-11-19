#include "state.h"

State::State(int q, double s) {
    this->count = q;
    this->time = s;
}

int State::get_count() {
    return this->count;
}

int State::get_time() {
    return this->time;
}