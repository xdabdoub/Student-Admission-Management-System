package me.yhamarsheh.projecttwo.structure;

import me.yhamarsheh.projecttwo.structure.nodes.sub.DNode;
import me.yhamarsheh.projecttwo.structure.nodes.sub.SNode;

public class DoublyLinkedList<T extends Comparable<T>> {

    private DNode<T> head;
    // No tail node. (Professor requirement)

    public void insert(T data) {
        DNode<T> newNode = new DNode<T>(data);

        if (head == null) {
            head = newNode;
            return;
        }

        if (newNode.getData().compareTo(head.getData()) <= 0) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
            return;
        }

        DNode<T> current = head;

        while (current.getNext() != null && newNode.getData().compareTo(current.getNext().getData()) > 0) {
            current = current.getNext();
        }

        if (current.getNext() == null) {
            current.setNext(newNode);
            newNode.setPrevious(current);
        } else {
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
        }
    }

    public void delete(T data) {
        DNode<T> current = head;

        if (head == null) return;
        if (head.getData().compareTo(data) == 0 && head.getNext() == null) { // Delete at head
            head = null;
            return;
        }

        if (head.getData().compareTo(data) == 0 && head.getNext() != null) { // Delete at head when having more than one item in the list
            head = head.getNext();
            head.setPrevious(null);

            return;
        }

        while (current != null) {
            if (current.getData().compareTo(data) > 0) return; // Not found
            if (current.getData().compareTo(data) == 0 && current.getNext() == null) { // Delete at end
                current.getPrevious().setNext(null);
                return;
            }

            if (current.getData().compareTo(data) == 0) {
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());
                return;
            }

            current = current.getNext();
        }
    }

    /*
     * Lab Work. Not needed in this project
     */
    public void deleteDuplicates() {
        DNode<T> current = head;

        if (head == null) return;

        if (head.getNext() != null && head.getNext().getData().compareTo(head.getData()) == 0) { // Delete at head when having more than one item in the list
            head = head.getNext();
            head.setPrevious(null);
        }

        while (current != null) {
            if (current.getNext() != null) {
                if (current.getPrevious() == null && current.getData().compareTo(current.getNext().getData()) == 0) {
                    current.getNext().setPrevious(null);
                } else
                if (current.getData().compareTo(current.getNext().getData()) == 0) {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                }
            }

            current = current.getNext();
        }
    }

    public DNode<T> getHead() {
        return head;
    }

    /*
     * Lab Work. Not needed in this project
     */
    public void merge(DoublyLinkedList<T> list) {

    }

    public boolean search(T data) {
        DNode<T> current = head;
        while (current != null) {
            if (current.getData().compareTo(data) > 0) return false;
            if (current.getData().compareTo(data) == 0) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void clear() {
        head = null;
    }

    public void display() {
        DNode<T> current = head;
        System.out.print("Head -> ");

        while (current != null) {
            System.out.print(current.toString());
            current = current.getNext();
        }

        System.out.println("Null");
    }

    public int size() {
        int length = 0;
        DNode<T> curr = head;
        while (curr != null) {
            length++;
            curr = curr.getNext();
        }
        return length;
    }

    /*
     * Lab Work. Not needed in this project
     */
    public int getFrequency(T data) {
        return getFrequency(data, head);
    }

    /*
     * Lab Work. Not needed in this project
     */
    private int getFrequency(T data, DNode<T> current) {
        if (current == null) return 0;
        if (current.getData().compareTo(data) > 0) return 0;

        return (current.getData().compareTo(data) != 0 ? 0 : 1) + getFrequency(data, current.getNext());
    }
}