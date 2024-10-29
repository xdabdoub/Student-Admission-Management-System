package me.yhamarsheh.projecttwo.structure;


import me.yhamarsheh.projecttwo.structure.nodes.Node;

public interface Listable<T extends Comparable<T>> {
    void insert(T obj);
    boolean delete(T obj);
    Node<T> find(T obj);
    void print();
    void clear();
    int size();
}