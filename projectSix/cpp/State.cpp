#include "state.h"

State::State(int q, int d, int n, int v) {
    this->quarters = q;
    this->dimes = d;
    this->nickels = n;
    this->value = v;
}

int State::coin_quan(char c) {
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

int State::get_value() {
    return this->value;
}