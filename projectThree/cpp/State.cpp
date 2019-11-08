#include "state.h"

State::State(int q, int d, int n, bool c) {
    this->quarters = q;
    this->dimes = d;
    this->nickels = n;
    this->change = c;
    this->value = 0;
}

State::State(State *s, int q, int d, int n, int v, bool c) {
    this->quarters = s->getQuanCoins('q') + q;
    this->dimes = s->getQuanCoins('d') + d;
    this->dimes = s->getQuanCoins('n') + n;
    this->change = c;
    this->value = s->getValue() + this->makeValue(q, d, n) - v;
}

int State::makeValue(int q, int d, int n) {
    return ((q * 25) + (d * 10) + (n * 5));
}

int State::getQuanCoins(char c) {
    int qCoins = 0;
    switch(c) {
        case 'q':
            qCoins = this->quarters;
            break;
        case 'd':
            qCoins = this->dimes;
            break;
        case 'n':
            qCoins = this->nickels;
            break;
    }
    return qCoins;
}

int State::getValue() {
    return this->value;
}

bool State::isPressed() {
    return this->change;
}