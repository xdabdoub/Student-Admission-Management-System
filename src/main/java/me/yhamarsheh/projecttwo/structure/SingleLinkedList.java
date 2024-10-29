package me.yhamarsheh.projecttwo.structure;

import me.yhamarsheh.projecttwo.structure.nodes.sub.SNode;

public class SingleLinkedList<T extends Comparable<T>> implements Listable<T> {

    private SNode<T> head;

    public SingleLinkedList() {
        this.head = null;
    }

    public SNode<T> getHead() {
        return head;
    }

    public void setHead(SNode<T> head) {
        this.head = head;
    }

    @Override
    public void insert(T obj) {
        SNode<T> newNode = new SNode<>(obj);
        if (head == null) {
            head = newNode; // Case 0: To insert a node at the beginning of a
            // linked list
            return;
        }

        SNode<T> current = head;
        SNode<T> prev = null;
        for (prev = null, current = head; (current != null)
                && (newNode.getData().compareTo(current.getData()) > 0); prev = current, current = (SNode<T>) current.getNext())
            ;

        if (current == head) { // Case 1: Add at head
            newNode.setNext(head);
            head = newNode;
            return;
        }

        if (current == null) { // Case 3: Add at the end
            newNode.setNext(current);
            prev.setNext(newNode);
            return;
        }

        newNode.setNext(current);
        prev.setNext(newNode);
    }

    public void insertAtHead(T data) {
        SNode<T> newNode = new SNode<>(data);

        newNode.setNext(head);
        head = newNode;
    }

    @Override
    public boolean delete(T obj) {
        if (head == null) return false;
        if (head.getData().compareTo(obj) == 0) {
            head = (SNode<T>) head.getNext();
            return true;
        }

        SNode<T> curr = head;
        SNode<T> prev = null;
        while (curr != null) {
            if (curr.getData().compareTo(obj) == 0) {
                prev.setNext(curr.getNext());
                return true;
            }

            curr = (SNode<T>) curr.getNext();
            prev = curr;
        }

        return false;
    }

    public void removeDuplicates() {
        SNode<T> current = head;
        while (current != null && current.getNext() != null) {
            if (current.getData().compareTo(current.getNext().getData()) == 0)
                current.setNext(current.getNext().getNext());
            else current = (SNode<T>) current.getNext();
        }
    }

    public SNode<T> getAtFromLast(int i) {
        SNode<T> current = head;
        while (current != null) {
            SNode<T> check = current;
            for (int j = 0; j < i; j++) {
                check = (SNode<T>) check.getNext();
                if (check == null && i != j) continue;
            }

            if (check == null) {
                return current;
            }

            current = (SNode<T>) current.getNext();
        }

        return null;
    }

    @Override
    public SNode<T> find(T obj) {
        SNode<T> curr = head;
        while (curr != null) {
            if (curr.getData().compareTo(obj) == 0) // if (curr.getData().equals(data))
                return curr;
            curr = (SNode<T>) curr.getNext();
        }

        return null;
    }

    public SNode<T> findRecursively(SNode<T> current, T obj) {
        if (current == null) return null;
        if (current.getData().compareTo(obj) > 0) return null;
        if (current.getData().compareTo(obj) == 0) return current;

        return findRecursively((SNode<T>) current.getNext(), obj);
    }

    public SNode<T> getAtRecursively(int index) {
        if (index > size() || index < 0) throw new IndexOutOfBoundsException();

        return getAtRecursively(head, 0, index);
    }

    private SNode<T> getAtRecursively(SNode<T> current, int i, int index) {
        if (current == null) return null;
        if (i > index) return null;
        if (i == index) return current;

        return getAtRecursively((SNode<T>) current.getNext(), ++i, index);
    }

    public int getFrequencySorted(T data) {
        return getFrequencySorted(data, head);
    }

    private int getFrequencySorted(T data, SNode<T> current) {
        if (current == null) return 0;
        if (current.getData().compareTo(data) > 0) return 0;
        if (current.getData().compareTo(data) != 0) return 0;

        return 1 + getFrequencySorted(data, (SNode<T>) current.getNext());
    }

    public int getFrequencyNotSorted(T data) {
        return getFrequencyNotSorted(data, head);
    }

    private int getFrequencyNotSorted(T data, SNode<T> current) {
        if (current == null) return 0;
        return ((current.getData().compareTo(data) != 0) ? 0 : 1) + getFrequencyNotSorted(data, (SNode<T>) current.getNext());
    }


    @Override
    public void print() {
        SNode<T> current = head;
        System.out.print("Head -> ");
        while (current != null) {
            System.out.print(current.toString());
            current = (SNode<T>) current.getNext();
        }

        System.out.println("Null");
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public int size() {
        int length = 0;
        SNode<T> curr = head;
        while (curr != null) {
            length++;
            curr = (SNode<T>) curr.getNext();
        }
        return length;
    }

    public int lengthRecursively() {
        return lengthRecursively(head);
    }

    private int lengthRecursively(SNode<T> current) {
        if (current == null) return 0;

        return 1 + lengthRecursively((SNode<T>) current.getNext());
    }

}