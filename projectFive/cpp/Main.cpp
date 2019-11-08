#include "vendingmachine.h"
#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "fstream"
using namespace std;

int main() {
    cout << "Enter Mode: ";
    char m;
    cin >> m;

    cout << "Enter Starting State of the Machine: ";
    int q = 0;
    int d = 0;
    int n = 0;
    string in = "";
    cin >> in;
    for(int i = 0; i < in.size(); i++) {
        if(in[i] == 'q') {q += 1;}
        if(in[i] == 'd') {d += 1;}
        if(in[i] == 'n') {n += 1;}
    }
    q=d=n=0;
    in = "";
    VendingMachine* vm = new VendingMachine(q, d, n);


    //We want a batch mode here
    if(m == 'b') {
        string line = "";
        ifstream file("input.txt");
        if(file.is_open()) {
            while(getline(file, line)) {
                cout << line << endl;
                vm->run(line);
            }
            file.close();
        }
    }
    //Break out of batch mode and continue with live data
    cout << "Enter Change, c to Cancel: ";
    cin >> in;
    while(in != "exit") {
        vm->run(in);
        in = "";
        cout << "Enter Change, c to Cancel: ";
        cin >> in;
    }
    vm->destruct();
    delete(vm);
    return 1;
}
