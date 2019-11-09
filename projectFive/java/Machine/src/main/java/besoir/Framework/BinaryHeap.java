package besoir.Framework;

import java.util.PriorityQueue;
public class BinaryHeap<T> {
    PriorityQueue<T> pq;
    public BinaryHeap() {
        pq = new PriorityQueue<>();
    }

    public T getMin() {
        return pq.poll();
    }

    //I believe that we dont need this because it is implemented from our priority queue
    public void extractMin() {}

    //decreases the value of the key, reordering may be necessary
    public void decreaseKey(){}

    public void insert(T t) {
        pq.add(t);
    }
}