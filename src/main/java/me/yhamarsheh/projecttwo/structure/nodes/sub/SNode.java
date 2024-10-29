package me.yhamarsheh.projecttwo.structure.nodes.sub;

import me.yhamarsheh.projecttwo.structure.nodes.Node;

public class SNode<T extends Comparable<T>> extends Node<T> {
    public SNode(T data) {
        super(data);
    }

    @Override
    public String toString() {
        return null;
    }
}
