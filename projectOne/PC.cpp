#include "room.h"
#include "creature.h"
#include "pc.h"
#include <string>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

PC::PC(int n, int type, Room* in) : Creature(n, type, in) {
    this->stateHate = 2;
    this->respect = 40;
}

int PC::alert(int changeBy, int mag) {
	return 0;
}

void PC::setRespect(int value) {
    this->respect += value;
}

int PC::getRespect() {
    return this->respect;
}