#ifndef PORT_HPP
#define PORT_HPP

template<typename T>
class Port {
    private:
        T data;
    public:
        void set_data(T d);
        T send_data();
        Port();
};
#endif