#include "vendingmachine.h"
#include "state.h"
using namespace std;

VendingMachine::VendingMachine(int q, int d, int n, bool c) {
    this->s = new State(q, d, n, c);
}

void VendingMachine::input(int q, int d, int n, bool c, bool w) {
    this->lambda();
    this->delta(q, d, n, c);
}

void VendingMachine::lambda() {
    string out = "";

    int count = 0;
    while(count < numCoffees) {
        out += 'c';
        count += 1;
    }

    if(s->isPressed()) {
        out += this->produceChange();
    }

    if(out == "") {
        cout << "{nothing}" << endl;
    } else {
        cout << "Returned: {" << out << "}" << endl;
    }

    numCoffees = 0;
}

void VendingMachine::delta(int q, int d, int n, bool c) {
    numCoffees = (int) floor((s->getValue() + (q * 25) + (d * 10) + (n * 5)) / 100);
    int v = (numCoffees * 100);
    if(s->isPressed()) {
        v = s->getValue();
    }
    s = new State(s, q, d, n, v, c);
}

string VendingMachine::produceChange() {
    string build = "";
    int tot = s->getValue();
    int qRet = this->getQuarters(tot);
    int dRet = this->getDimes((tot - (25*qRet)));
    int nRet = this->getNickels((tot - ((25 * qRet) + (10 * dRet))));
    for(int i = 0; i < qRet; i++) { build += 'q'; }
    for(int i = 0; i < dRet; i++) { build += 'd'; }
    for(int i = 0; i < nRet; i++) { build += 'n'; }
    return build;
}

int VendingMachine::getQuarters(int val) {
    int rQ = (int) floor(val/25);
    val -= rQ * 25;
    if(rQ > s->getQuanCoins('q')) {
        int pb = rQ - s->getQuanCoins('q');
        rQ -= pb;
        val += (pb * 25);
    }
    return rQ;
}

int VendingMachine::getDimes(int val) {
    int rD = (int) floor(val/10);
    val -= rD * 25;
    if(rD > s->getQuanCoins('d')) {
        int pb = rD - s->getQuanCoins('d');
        rD -= pb;
        val += (pb * 25);
    }
    return rD;
}

int VendingMachine::getNickels(int val) {
    int rN = (int) floor(val/5);
    val -= rN * 25;
    if(rN > s->getQuanCoins('n')) {
        cout << "Not enough coins, please contact manager" << endl;
        exit(0);
    }
    return rN;
}

void VendingMachine::destruct() {
    delete(s);
}