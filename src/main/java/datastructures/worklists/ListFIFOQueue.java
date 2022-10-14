package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private Node first;
    private Node last;
    private int size;

    private class Node<E> {
        E work;
        Node next;

        public Node(E node) {
            this.work = node;
            this.next = null;
        }

        public Node(E node, Node next) {
            this.work = node;
            this.next = next;
        }
    }

    public ListFIFOQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void add(E work) {
        Node nodeToAdd = new Node(work);

        if(this.hasWork()) {
            this.last.next = nodeToAdd;
            this.last = this.last.next;
        } else {
            this.first = nodeToAdd;
            this.last = nodeToAdd;
        }
        this.size++;
    }

    @Override
    public E peek() {
        if(this.hasWork()) {
            return (E) this.first.work;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E next() {
        if(this.hasWork()) {
            Node valueToReturn = this.first;
            this.first = this.first.next;
            this.size--;
            return (E) valueToReturn.work;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }
}
