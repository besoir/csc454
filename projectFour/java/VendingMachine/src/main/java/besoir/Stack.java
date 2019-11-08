package besoir;

import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> stack;

    public Stack() {
        stack = new ArrayList<>();
    }

    public Stack(T t) {
        stack = new ArrayList<>();
        stack.add(t);
    }

    private boolean isNull() {
        return stack.size() == 0;
    }

    public T pop() {
        T temp = null;
        if(stack.size() == 1) {
            temp = stack.get(0);
            stack.remove(temp);
        } else if(stack.size() > 1) {
            temp = stack.get(0);
            for(int i = 1; i < stack.size(); i++) {
                stack.set(i-1, stack.get(i));
            }
            stack.remove(stack.size()-1);
        }
        return temp;
    }

    public void push(T t) {
        if(isNull()) {
            stack.add(t);
        } else {
            for(int i = stack.size()-1; i >= 0; i--) {
                if(i == stack.size()-1) {
                    stack.add(stack.get(i));
                } else {
                    stack.set(i+1, stack.get(i));
                }   
            }
            stack.set(0, t);
        }
    }

    public T peek() {
        if(!isNull()) {
            return stack.get(0);
        }
        return null;
    }

    public int getCount() {
        return stack.size();
    }

    @Override
    public String toString() {
        String s = "Elements:";
        for(T t : stack) {
            s += " " + t;
        }
        return s;
    }
}