#include <iostream>
#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "fstream"
using namespace std;

int main() {
    cout << "Enter Mode: ";
    char m;
    cin >> m;

    //We want a batch mode here
    if(m == 'b') {
        string line = "";
        ifstream file("input.txt");
        if(file.is_open()) {
            while(getline(file, line)) {
                //run
                cout << line << endl;
            }
            file.close();
        }
    }
    //Break out of batch mode and continue with live data
    string in;
    cout << "> ";
    cin >> in;
    while(in != "exit") {
        //run
        in = "";
        cout << "> ";
        cin >> in;
    }
    return 1;
}
