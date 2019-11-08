#ifndef PC_H
#define PC_H
#include "creature.h"
#include <string>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>

class Room;
class PC : public Creature {
    private:
        int stateHate, respect;

    public:
        int alert(int changeBy, int mag);
	    PC(int n, int type, Room* in);
        void setRespect(int value);
        int getRespect();
    };
#endif