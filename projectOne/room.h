#ifndef ROOM_H
#define ROOM_H
#include <string>
#include <vector>

class Creature;
class Room {
    private:
        int count, name, northDoor, southDoor, eastDoor, westDoor, state;
	    std::vector<Creature*> cList;
        std::vector<Room*> adjRooms;

    public:
        int alertAll(int changeBy, int mag);
        void addCreature(Creature *c);
        void getCreatures();
        int getCount();
        Creature* getCreatureByName(int cName);
        Room* getRoomByDir(int want);
        std::string getDoors();
        int getDoorTo(int dir);
        bool changeState(int val);
        std::string getStateName();
        int getState();
        int getName();
        bool moveCreature(int dir, int cName);
        void setRoom(Room *r);
        void removeCreature(int cName, Room* newIn);
        bool contains(int n);
        Room(int n, int state, int nDoor, int sDoor, int eDoor, int wDoor);
    };
#endif