#include <iostream>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include "vendingmachine.h"
using namespace std;

int main() {
    cout << "Enter Starting State of the Machine: " << endl;
    int q = 0;
    int d = 0;
    int n = 0;
    bool w = false;
    bool c = false;
    string in = "";
    cin >> in;
    for(int i = 0; i < in.size(); i++) {
        if(in[i] == 'q') {q += 1;}
        if(in[i] == 'd') {d += 1;}
        if(in[i] == 'n') {n += 1;}
    }
    q=d=n=0;
    in = "";
    VendingMachine* vm = new VendingMachine(q, d, n, c);
    cout << "Enter Change, c to Cancel: ";
    cin >> in;
    while(in != "exit") {
        for(int i = 0; i < in.size(); i++) {
            if(in[i] == 'q') {q += 1;}
            if(in[i] == 'd') {d += 1;}
            if(in[i] == 'n') {n += 1;}
            if(in[i] == 'c') {c = true;}
            if(in[i] == 'w') {w = true;}
        }
        vm->input(q, d, n, c, w);
        cout << "Enter Change, c to Cancel: ";
        in = "";
        cin >> in;
        q=d=n=0;
        w=c=false;
    }
    vm->destruct();
    delete(vm);
    return 1;
}