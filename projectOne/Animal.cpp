#include "room.h"
#include "creature.h"
#include "animal.h"
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

Animal::Animal(int n, int type, Room* in) : Creature(n, type, in) {
    this->stateHate = 2;
}

int Animal::alert(int changeBy, int mag) {
	if(changeBy == 1) {
		int ret = growl(mag);
		if(this->roomIn->getState() == this->stateHate) {
			int ran = rand() % 4;
			while(this->roomIn->getDoorTo(ran) == -1) {
				//cout << "Direction: " << to_string(ran) << " Room: " << to_string(roomIn->getDoorTo(ran)) << endl;
				ran = rand() % 4;
			}
			roomIn->moveCreature(ran, this->name);
		}
		return ret;
	} else if(changeBy != 22){
		return lickFace(mag);
	} else {
		return growl(1);
	}
}

int Animal::growl(int count) {
	if(count == 1) {
		cout << this->name << " growls." << endl;
		return -1;
	} else {
		cout << this->name << " growls a lot." << endl;
		return -3;
	}
}

int Animal::lickFace(int count) {
	if(count == 1) {
		cout << this->name << " licks your face." << endl;
		return 1;
	} else {
		cout << this->name << " licks your face a lot." << endl;
		return 3;
	}
}