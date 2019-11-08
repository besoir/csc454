package besoir.Framework;

import java.lang.Comparable;
import java.util.ArrayList;

public class PriorityQueue<T> implements Comparable<T>{
    private ArrayList<T> queue;
 
    public PriorityQueue() {
        queue = new ArrayList<>();
    }

    public void insert(T t) {
        queue.add(0, t);
    }

    public void deleteMin() {

    }

    public T dequeue() {
        T place = queue.get(0);
        queue.forEach(t -> queue.add(queue.indexOf(t) - 1, t));
        return place;
    }

    public void findMin() {

    }

    @Override
    public int compareTo(T t) {
        return this.compareTo(t);
    }
}