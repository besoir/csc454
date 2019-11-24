#include "state.hpp"

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

void State::print_state() {
    cout << "Parts: " << to_string(count) << " Time: " << to_string(time) << endl;
}