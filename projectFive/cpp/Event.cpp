#include "event.h"

Event::Event(double t, char c) {
    this->time = t;
    this->coin = c;
}

double Event::get_time() {
    return this->time;
}

char Event::get_coin() {
    return this->coin;
}
