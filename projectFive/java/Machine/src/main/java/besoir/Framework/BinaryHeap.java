package besoir.Framework;

public class BinaryHeap {
    private final int MAX = 1025;
    private Event[] heap;
    private int count;

    public BinaryHeap() {
        heap = new Event[MAX];
        count = 0;
    }

    private int parent(int curr) {
        return (curr-1)/2;
    }

    private int leftChild(int curr) {
        return ((2*curr)+1);
    }

    private int rightChild(int curr) {
        return ((2*curr)+2);
    }

    private boolean isLeaf(int curr) {
        return curr >= (count / 2) && curr <= count;
    }
    
    private void swap(int pos1, int pos2) {
        Event temp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = temp;
    }

    private void shiftHeap(int pos) {
        //System.out.println(this);
        if(!isLeaf(pos)) {
            if(heap[leftChild(pos)] == null) {
                if(heap[rightChild(pos)].getTime().getReal() < heap[pos].getTime().getReal())
                    swap(pos, rightChild(pos));
            } else if(heap[rightChild(pos)] == null) {
                if(heap[leftChild(pos)].getTime().getReal() < heap[pos].getTime().getReal())
                    swap(pos, leftChild(pos));
            } else if(heap[leftChild(pos)].getTime().getReal() < heap[pos].getTime().getReal() 
                || heap[pos].getTime().getReal() > heap[rightChild(pos)].getTime().getReal()) {
                if(heap[leftChild(pos)].getTime().getReal() < heap[rightChild(pos)].getTime().getReal()) {
                    swap(pos, leftChild(pos));
                    shiftHeap(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    shiftHeap(rightChild(pos));
                }
            }
        }
    }

    public Event peek() {
        return heap[0];
    }

    public Event remove() {
        Event temp = heap[0];
        heap[0] = heap[count-1];
        //System.out.println(this);
        shiftHeap(0);
        count -= 1;
        heap[count] = null;
        return temp;
    }

    public void insert(Event e) {
        //System.out.println(count);
        heap[count] = e;
        int curr = count;
        count += 1;
        while(heap[curr].getTime().getReal() < heap[parent(curr)].getTime().getReal()) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }

    public boolean isNull() {
        return count <= 0;
    }

    @Override
    public String toString() {
        String ret = "Heap:\n";/*
        for(int i = 0; i <= count / 2; i++) {
            ret += heap[i].getMachine() + "" + heap[i].getTime() + " " + heap[i].getCount() + "\n";
            if(heap[leftChild(i)] != null) {
                ret += "Left Child: " + heap[leftChild(i)].getMachine() + "" + heap[leftChild(i)].getTime() + " " + heap[leftChild(i)].getCount() + "\n";
            }
            if(heap[rightChild(i)] != null) {
                ret += "Right Child: " + heap[rightChild(i)].getMachine() + "" + heap[rightChild(i)].getTime() + " " + heap[rightChild(i)].getCount() + "\n";
            }
        }
        */
        for(int i = 0; i < count; i++) {
            ret += heap[i].getMachine() + "" + heap[i].getTime() + " " + heap[i].getCount() + "\n";
        }
        return ret;
    }
}