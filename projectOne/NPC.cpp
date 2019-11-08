#include "room.h"
#include "creature.h"
#include "npc.h"
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

NPC::NPC(int n, int type, Room* in) : Creature(n, type, in) {
    this->stateHate = 0;
}

int NPC::alert(int changeBy, int mag) {
	if(changeBy == -1) {
		int ret = grumble(mag);
		if(this->roomIn->getState() == this->stateHate) {
			int ran = rand() % 4;
			while(this->roomIn->getDoorTo(ran) == -1) {
				//cout << "Direction: " << to_string(ran) << " Room: " << to_string(roomIn->getDoorTo(ran)) << endl;
				ran = rand() % 4;
			}
			roomIn->moveCreature(ran, this->name);
		}
		return ret;
	} else if(changeBy != 22) {
		return smile(mag);
	} else {
		return grumble(1);
	}
}

int NPC::grumble(int count) {
	if(count == 1) {
		cout << this->name << " grumbles." << endl;
		return -1;
	} else {
		cout << this->name << " grumbles a lot." << endl;
		return -3;
	}
}

int NPC::smile(int count) {
	if(count == 1) {
		cout << this->name << " smiles." << endl;
		return 1;
	} else {
		cout << this->name << " smiles a lot." << endl;
		return 3;
	}
}