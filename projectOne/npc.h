#ifndef NPC_H
#define NPC_H
#include "creature.h"
#include <string>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>

class Room;
class NPC : public Creature {
    private:
        int stateHate;

    public:
        int alert(int changeBy, int mag);
        int grumble(int count);
        int smile(int count);
	    NPC(int n, int type, Room* in);
    };
#endif