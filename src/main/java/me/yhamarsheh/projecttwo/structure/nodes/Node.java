package me.yhamarsheh.projecttwo.structure.nodes;

public abstract class Node<T extends Comparable<T>> {

    protected Node<T> next;
    protected T data;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public abstract String toString();
}
