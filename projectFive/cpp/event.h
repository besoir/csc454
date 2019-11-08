#ifndef EVENT_H
#define EVENT_H

class Event {
    private:
        double time;
        char coin;

    public:
        double get_time();
        char get_coin();
        void destruct();
        Event(double t, char c);
};

#endif
