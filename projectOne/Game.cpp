#include <iostream>
#include <string>
#include <vector>
#include "room.h"
#include "creature.h"
#include "animal.h"
#include "npc.h"
#include "pc.h"
#include "game.h"
using namespace std;

int Game::start() {
	PC *player;
	cin >> roomNum;

	int currRoom = 0;
	int rooms[5];
	while(currRoom < roomNum) {
		for (int i = 0; i < 5; i++) {
			cin >> rooms[i];
		}

		roomList.push_back(new Room(currRoom, rooms[0], rooms[1], rooms[2], rooms[3], rooms[4]));
		currRoom += 1;
	}

	int tempRoom = 0;
	while(tempRoom < roomNum) {
		int curr = 0;
		while(curr < 4) {
			roomList[tempRoom]->setRoom(roomList[roomList[tempRoom]->getDoorTo(curr)]);
			curr += 1;
		}
		tempRoom += 1;
	}

	cin >> creatureNum;

	int currCreat = 0;
	int creat[2];
	while (currCreat < creatureNum) {
		for (int i = 0; i < 2; i++) {
			cin >> creat[i];
		}
		if(creat[0] == 0) {
			pcIn = creat[1];
			pcName = currCreat;
		}
		Creature *c;
		if(creat[0] == 0) {
			c = new PC(currCreat, creat[0], roomList[creat[1]]);
			player = (PC*)c;
		} else if(creat[0] == 1) {
			c = new Animal(currCreat, creat[0], roomList[creat[1]]);
		} else {
			c = new NPC(currCreat, creat[0], roomList[creat[1]]);
		} 
		roomList[creat[1]]->addCreature(c);
		currCreat += 1;
	}

	string cmd = "";
	cin >> cmd;
	while(cmd != "exit") {
		if(cmd == "look") {
			look(pcIn);
		} else if(cmd == "north") {
			if(roomList[pcIn]->moveCreature(0, pcName)) {
				pcIn = roomList[pcIn]->getDoorTo(0);
			}
		} else if(cmd == "south") {
			if(roomList[pcIn]->moveCreature(1, pcName)) {
				pcIn = roomList[pcIn]->getDoorTo(1);
			}
		} else if(cmd == "east") {
			if(roomList[pcIn]->moveCreature(2, pcName)) {
				pcIn = roomList[pcIn]->getDoorTo(2);
			}
		} else if(cmd == "west") {
			if(roomList[pcIn]->moveCreature(3, pcName)) {
				pcIn = roomList[pcIn]->getDoorTo(3);
			}
		} else if(cmd == "clean") {
			player->setRespect(roomList[pcIn]->getCreatureByName(pcName)->changeState(-1, 1));
			cout << "Respect is now " << to_string(player->getRespect()) << endl;
		} else if(cmd == "dirty") {
			player->setRespect(roomList[pcIn]->getCreatureByName(pcName)->changeState(1, 1));
			cout << "Respect is now " << to_string(player->getRespect()) << endl;
		} else if(cmd.find(':') != string::npos) {
			int cre = stoi(cmd.substr(0,1));
			string newCmd = cmd.substr(2);
			cout << to_string(cre) << " " << newCmd << endl;
			if(newCmd == "clean") {
				if(roomList[pcIn]->contains(cre)) {
					player->setRespect(roomList[pcIn]->getCreatureByName(pcIn)->changeState(-1, 3));
					cout << "Respect is now " << to_string(player->getRespect()) << endl;
				} else {
					cout << to_string(cre) << " is not in the room" << endl;
				}
			} else if(newCmd == "dirty") {
				if(roomList[pcIn]->contains(cre)) {
					player->setRespect(roomList[pcIn]->getCreatureByName(pcIn)->changeState(1, 3));
					cout << "Respect is now " << to_string(player->getRespect()) << endl;
				} else {
					cout << to_string(cre) << " is not in the room" << endl;
				}
			}
		} else {
			cout << cmd << endl;
		}
		cmd = "";
		cin >> cmd;
	}
	delete(player);
	return 0;
};

void Game::gameFacts() {
	cout << "Number of Rooms: " << to_string(roomNum) << " Number of Creatures: " << to_string(creatureNum) << endl;
	int i = 0;
	while(i < roomList.size()) {
		cout << "Room: " << to_string(roomList[i]->getName()) << endl;
		roomList[i]->getCreatures();
		i += 1;
	}
};

void Game::look(int rN) {
	cout << "Room " << to_string(roomList[rN]->getName()) << ", " << roomList[rN]->getStateName();
	cout << ", neighbors " << roomList[rN]->getDoors() << ", contains: " << endl;
	roomList[rN]->getCreatures();
};

int main() {
	Game *g = new Game();
	g->start();
	cout << "Goodbye!" << endl;
}