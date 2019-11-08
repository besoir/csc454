package besoir.Framework;
public class BinaryHeap<T> {
    PriorityQueue<T> pq;
    public BinaryHeap() {
        pq = new PriorityQueue<>();
    }

    public T getMin() {
        T t = pq.dequeue();
        //reshuffle maybe?
        return t;
    }

    //I believe that we dont need this because it is implemented from our priority queue
    public void extractMin() {}

    //decreases the value of the key, reordering may be necessary
    public void decreaseKey(){}

    public void insert(T t) {
        pq.insert(t);
    }
}