#include "room.h"
#include "creature.h"
#include <string>
#include <vector>
using namespace std;

Room::Room(int n, int s, int nDoor, int sDoor, int eDoor, int wDoor) {
	name = n;
	state = s;
	northDoor = nDoor;
	southDoor = sDoor;
	eastDoor = eDoor;
	westDoor = wDoor;
	count = 0;
}

int Room::alertAll(int changeBy, int mag) {
	int curr = 0;
	int changeValue = 0;
	int currRoomCount = this->count;
	while(curr < count) {
		changeValue += cList[curr]->alert(changeBy, mag);
		if(currRoomCount != this->count) {
			currRoomCount = this->count;
		} else {
			curr += 1;
		}
	}
	//cout << to_string(changeValue) << endl;
	return changeValue;
}

bool Room::moveCreature(int dir, int cName) {
	bool boolean = false;
	if(this->getDoorTo(dir) != -1 && this->adjRooms[dir]->count < 10) {
		boolean = true;
		adjRooms[dir]->addCreature(this->getCreatureByName(cName));
		switch(dir) {
			case 0:
				if(this->getCreatureByName(cName)->getType() == 0) {
					cout << "You move towards the north" << endl;
				} else {
					cout << to_string(cName) << " moves towards the north" << endl;
				}
				break;
			case 1:
				if(this->getCreatureByName(cName)->getType() == 0) {
					cout << "You move towards the south" << endl;
				} else {
					cout << to_string(cName) << " moves towards the south" << endl;
				}
				break;
			case 2:
				if(this->getCreatureByName(cName)->getType() == 0) {
					cout << "You move towards the east" << endl;
				} else {
					cout << to_string(cName) << " moves towards the east" << endl;
				}
				break;
			case 3:
				if(this->getCreatureByName(cName)->getType() == 0) {
					cout << "You move towards the west" << endl;
				} else {
					cout << to_string(cName) << " moves towards the west" << endl;
				}
				break;
		}
	} else {
		cout << to_string(this->getCreatureByName(cName)->getName()) << " left the game." << endl;
		this->alertAll(22, 0);
	}
	
	this->removeCreature(cName, this->adjRooms[dir]);
	return boolean;
}

void Room::addCreature(Creature *c) {
	if(count < 10) {
		cList.push_back(c);
		this->count += 1;
	} else {
		cout << "Room full" << endl;
	}
};

void Room::getCreatures() {
	int i = 0;
	while(i < this->count) {
		cList[i]->toString();
		i += 1;
	}
}

int Room::getDoorTo(int dir) {
	switch(dir) {
		case 0:
			return this->northDoor;
			break;
		case 1:
			return this->southDoor;
			break;
		case 2:
			return this->eastDoor;
			break;
		case 3:
			return this->westDoor;
			break;
	}
	return -2;
}

string Room::getDoors() {
	string holder = "";
	for(int i = 0; i < 4; i+=1) {
		switch(i) {
			case 0:
				if(this->northDoor != -1) {holder += to_string(adjRooms[0]->getName()) + " to the north";}
				break;
			case 1:
				if(this->southDoor != -1) {holder += to_string(adjRooms[1]->getName()) + " to the south";}
				break;
			case 2:
				if(this->eastDoor != -1) {holder += to_string(adjRooms[2]->getName()) + " to the east";}
				break;
			case 3:
				if(this->westDoor != -1) {holder += to_string(adjRooms[3]->getName()) + " to the west";}
				break;
		}
	}
	return holder;
}

Creature* Room::getCreatureByName(int cName) {
	int i = 0;
	while(i < 10) {
		if(cList[i]->getName() == cName) {
			return cList[i];
		}
		i += 1;
	}
	return NULL;
}

void Room::removeCreature(int cName, Room* newIn) {
	this->getCreatureByName(cName)->setRoom(newIn);
	int currPosition = 0;
	while(currPosition < this->count) {
		if(cList[currPosition]->getName() == cName) {
			cList.erase(next(begin(cList), + currPosition));
			this->count -= 1;
			break;
		}
		currPosition += 1;
	}
}

bool Room::changeState(int val) {
	if((this->state + val == -1) || (this->state + val == 3)) {
		return false;
	} else {
		this->state = val + this->state;
	}
	return true;
}

string Room::getStateName() {
	if(this->state == 0) {
		return "clean";
	} else if(this->state == 1) {
		return "half-dirty";
	} else {
		return "dirty";
	}
}

int Room::getName() {
	return this->name;
}

void Room::setRoom(Room *r) {
	adjRooms.push_back(r);
}

int Room::getState() {
	return this->state;
}

int Room::getCount() {
	return this->count;
}

Room* Room::getRoomByDir(int want) {
	if(this->getDoorTo(want) != -1) {
		return adjRooms[getDoorTo(want)];
	}
	return ((Room*)nullptr);
}

bool Room::contains(int n) {
	int curr = 0;
	while(curr < this->count) {
		if(cList[curr]->getName() == n) {
			return true;
		}
		curr += 1;
	}
	return false;
}
