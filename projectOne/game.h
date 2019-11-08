#ifndef GAME_H
#define GAME_H
#include <iostream>
#include <vector>
#include <string>

class Creature;
class Room;

class Game {
    private:
	    int roomNum, creatureNum, pcIn, pcName;
	    std::vector<Room*> roomList;

    public:
	    Game(){};
	    int start();
	    void gameFacts();
	    void look(int roomNum);

};

#endif