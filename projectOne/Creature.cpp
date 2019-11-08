#include "room.h"
#include "creature.h"
#include <string>
#include <iostream>
using namespace std;

Creature::Creature(int n, int type, Room* in) {
		name = n;
		cType = type;
		roomIn = in;
};

int Creature::changeState(int changeBy, int mag) {
		if(roomIn->changeState(changeBy)) {
			return roomIn->alertAll(changeBy, mag);
		} else {
			if(changeBy < 0) {
				cout << "This room is already clean" << endl;
			} else {
				cout << "This room is already dirty" << endl;
			}
		}
		return 0;
}

void Creature::toString(void) {
	if(this->cType == 0) {cout << "PC" << endl;}
	else if(this->cType == 1) {cout << "animal " << to_string(this->name) << endl;}
	else {cout << "human " << to_string(this->name) << endl;}
};

int Creature::getName() {
	return this->name;
}

int Creature::getType() {
	return this->cType;
}

void Creature::setRoom(Room* newRoom) {
	this->roomIn = newRoom;
}