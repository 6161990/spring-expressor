package pattern.bridge.impl;

import java.util.LinkedList;

// ConcreteImplementor : Implementor 에 선언된 기능을 실제로 구현한다. 여러 구현 방싯ㄱ의 클래스가 만들어 질 수 잇다.
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
    public int insertElement(T obj, int i) {
        linkedList.add(i, obj);
        return i;
    }

    @Override
    public T deleteElement(int i) {
        return linkedList.remove(i);
    }

    @Override
    public T getElement(int i) {
        return linkedList.get(i);
    }

    @Override
    public int getElementSize() {
        return linkedList.size();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}
