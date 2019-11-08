#ifndef ANIMAL_H
#define ANIMAL_H
#include "creature.h"
#include <string>
#include <iostream>
#include <stdlib.h>
#include <stdio.h>

class Room;
class Animal : public Creature {
    private:
        int stateHate;

    public:
        int alert(int changeBy, int mag);
        int growl(int count);
        int lickFace(int count);
	    Animal(int n, int type, Room* in);
    };
#endif