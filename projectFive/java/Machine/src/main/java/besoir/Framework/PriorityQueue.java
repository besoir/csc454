package besoir.Framework;

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Iterator;

public class PriorityQueue<T> implements Comparable<T>{
    private ArrayList<T> queue;
 
    public PriorityQueue() {
        queue = new ArrayList<>();
    }

    public void insert(T t) {
        if(isNull()) {
            queue.add(0, t);
        } else {
            
        }
    }

    public T peek() {
        if(isNull()) 
            return null;

        return queue.get(0);
    }

    public T poll() {
        if(isNull())
            return null;

        //reorganizing here

        return queue.get(0);
    }

    public int size() {
        return queue.size();
    }

    public Iterator<T> iterator() {
        return queue.iterator();
    }

    public void clear() {
        queue = new ArrayList<>();
    }

    private boolean isNull() {
        return queue.size() == 0;
    }

    @Override
    public int compareTo(T t) {
        return this.compareTo(t);
    }
}
