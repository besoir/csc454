#include "vendingmachine.h"
#include "state.h"
#include "event.h"
using namespace std;

VendingMachine::VendingMachine(int q, int d, int n) {
    this->s = new State(q, d, n, 0);
    alarm = time = e = 0.0;
}

void VendingMachine::run(string given) {
    num_coffees = s->get_value() / 100;
    //do some pushing here
    double newTime = stod(given.substr(1, given.find(",")));
    e = newTime - time;

    if(e > 2.0) {
        Event *ev = new Event(newTime, given.at(given.find(",")+1));
        stack.push_back(ev);
        ev = new Event(time + 2.0, ' ');
        stack.push_back(ev);
        delete(ev);
    } else if(e > 0.0) {
        Event *ev = new Event(newTime, given.at(given.find(",")+1));
        stack.push_back(ev);
        delete(ev);
    }

    while(!stack.empty()) {
        Event *ev = stack.back();
        stack.pop_back();
        if(ev->get_coin() == ' ') {
            lambda();
            internal_delta();
        } else if(e == alarm) {
            lambda();
            confluent_delta(ev->get_coin());
        } else {
            external_delta(ev->get_coin());
        }
    }
    if(e > 0.0) {
        time = newTime;
    }
}

void VendingMachine::lambda() {
    string out = "";

    int count = 0;
    while(count < num_coffees) {
        out += 'c';
        count += 1;
    }

    out += produce_change();

    if(out != "") {
        cout << "Returned: {" << out << "}" << endl;
    }

    num_coffees = 0;
}

//change state with subtracted change
void VendingMachine::internal_delta() {
    int tot = s->get_value();
    int q_ret = this->get_quarters(tot);
    int d_ret = this->get_dimes((tot - (25*q_ret)));
    int n_ret = this->get_nickels((tot - ((25 * q_ret) + (10 * d_ret))));

    this->s = new State(s->coin_quan('q') - q_ret, s->coin_quan('d') - d_ret, s->coin_quan('n') - n_ret, 0);
    time_advance();
}

void VendingMachine::external_delta(char c) {
    switch(c) {
        case 'q':
            this->s = new State(s->coin_quan('q') + 1, s->coin_quan('d'), s->coin_quan('n'), s->get_value() + 25);
            break;
        case 'd':
            this->s = new State(s->coin_quan('q'), s->coin_quan('d') + 1, s->coin_quan('n'), s->get_value() + 10);
            break;
        case 'n':
            this->s = new State(s->coin_quan('q'), s->coin_quan('d'), s->coin_quan('n') + 1, s->get_value() + 5);
            break;
    }
    time_advance();
}

void VendingMachine::confluent_delta(char c) {
    int tot = s->get_value();
    
    int q_ret = this->get_quarters(tot);
    int d_ret = this->get_dimes((tot - (25*q_ret)));
    int n_ret = this->get_nickels((tot - ((25 * q_ret) + (10 * d_ret))));

    switch(c) {
        case 'q':
            this->s = new State(s->coin_quan('q') - q_ret + 1, s->coin_quan('d') - d_ret, s->coin_quan('n') - n_ret, 25);
            break;
        case 'd':
            this->s = new State(s->coin_quan('q') - q_ret, s->coin_quan('d') - d_ret + 1, s->coin_quan('n') - n_ret, 10);
            break;
        case 'n':
            this->s = new State(s->coin_quan('q') - q_ret, s->coin_quan('d') - d_ret, s->coin_quan('n') - n_ret + 1, 5);
            break;
    }
    time_advance();
}

void VendingMachine::time_advance() {
    if(s->get_value() == 0) {
        alarm = INFINITY;
    } else {
        alarm = 2.0;
    }
}

string VendingMachine::produce_change() {
    string build = "";
    int tot = s->get_value() - (100 * num_coffees);
    int q_ret = get_quarters(tot);
    int d_ret = get_dimes((tot - (25*q_ret)));
    int n_ret = get_nickels((tot - ((25 * q_ret) + (10 * d_ret))));
    for(int i = 0; i < q_ret; i++) { build += 'q'; }
    for(int i = 0; i < d_ret; i++) { build += 'd'; }
    for(int i = 0; i < n_ret; i++) { build += 'n'; }
    return build;
}

int VendingMachine::get_quarters(int val) {
    int q = (int) floor(val/25);
    val -= q * 25;
    if(q > s->coin_quan('q')) {
        int pb = q - s->coin_quan('q');
        q -= pb;
    }
    return q;
}

int VendingMachine::get_dimes(int val) {
    int d = (int) floor(val/10);
    val -= d * 25;
    if(d > s->coin_quan('d')) {
        int pb = d - s->coin_quan('d');
        d -= pb;
    }
    return d;
}

int VendingMachine::get_nickels(int val) {
    int n = (int) floor(val/5);
    val -= n * 25;
    if(n > s->coin_quan('n')) {
        cout << "Not enough coins, please contact manager" << endl;
        delete(this);
        exit(0);
    }
    return n;
}

void VendingMachine::destruct() {
    delete(s);
}
