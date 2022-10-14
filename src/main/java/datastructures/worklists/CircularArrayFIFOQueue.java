package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {

    private int size;

    private E[] array;

    private int front;
    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        this.size = 0;
        this.array = (E[]) new Object[capacity];
        this.front = 0;
    }

    @Override
    public void add(E work) {
        if(this.isFull()) {
            throw new IllegalStateException();
        }
        array[(this.size + this.front) % this.array.length] = work;


        this.size++;
    }

    @Override
    public E peek() {
        if(!this.hasWork()){
            throw new NoSuchElementException();
        }
        return this.peek(0);
    }

    @Override
    public E peek(int i) {
        if(!this.hasWork()){
            throw new NoSuchElementException();
        }
        if(i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        return this.array[(this.front + i) % this.array.length];

    }

    @Override
    public E next() {
        if(!this.hasWork()) {
            throw new NoSuchElementException();
        }
        E frontVar = array[this.front];
        this.front = (this.front + 1) % this.array.length;
        this.size--;
        return frontVar;
    }

    @Override
    public void update(int i, E value) {
        if(!this.hasWork()) {
            throw new NoSuchElementException();
        }
        if(i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        array[(this.front + i) % this.array.length] = value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.array = (E[]) new Object[this.capacity()];
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        } else {
            // Uncomment the line below for p2 when you implement equals
            // FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here

            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }
}
