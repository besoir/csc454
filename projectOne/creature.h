#ifndef CREATURE_H
#define CREATURE_H
#include <string>
#include <iostream>

class Room;
class Creature {

protected:
    int name, cType;
    Room* roomIn;
public:
    virtual int alert(int changeBy, int mag) = 0;
    void toString(void);
	bool dislikeRoom(int state);
    int getName();
    int getType();
    int changeState(int changeBy, int mag);
    void setRoom(Room* newRoom);
	Creature(int n, int type, Room* in);
};
#endif