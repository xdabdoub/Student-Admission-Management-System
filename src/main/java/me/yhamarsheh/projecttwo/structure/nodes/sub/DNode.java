package me.yhamarsheh.projecttwo.structure.nodes.sub;

import me.yhamarsheh.projecttwo.structure.nodes.Node;

public class DNode<T extends Comparable<T>> extends Node<T> {

    private DNode<T> previous;

    public DNode(T data) {
        super(data);
    }

    public T getData() {
        return data;
    }

    public DNode<T> getPrevious() {
        return previous;
    }

    public DNode<T> getNext() {
        return (DNode<T>) next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(DNode<T> next) {
        this.next = next;
    }

    public void setPrevious(DNode<T> previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "[" + data + "] ->";
    }
}