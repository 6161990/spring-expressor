package pattern.bridge.impl;

import java.util.LinkedList;

public class LinkedListImpl<T> implements AbstractList<T> {

    LinkedList<T> linkedList;

    public LinkedListImpl() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public void addElement(T obj) {
        linkedList.add(obj);
    }

    @Override
    public T deleteElement(int i) {
        return linkedList.remove(i);
    }

    @Override
    public int insertElement(T obj, int i) {
        linkedList.add(i, obj);
        return i;
    }

    @Override
    public T getElement(int i) {
        return linkedList.get(i);
    }

    @Override
    public int getElementSize() {
        return linkedList.size();
    }
}
