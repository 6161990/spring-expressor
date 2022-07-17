package pattern.bridge.impl;

import java.util.ArrayList;

// ConcreteImplementor : Implementor 에 선언된 기능을 실제로 구현한다. 여러 구현 방싯ㄱ의 클래스가 만들어 질 수 잇다.
public class ArrayImpl<T> implements AbstractList<T> {

    ArrayList<T> array;

    public ArrayImpl() {
        this.array = new ArrayList<>();
    }

    @Override
    public void addElement(T obj) {
        array.add(obj);
    }

    @Override
    public T deleteElement(int i) {
        return array.remove(i);
    }

    @Override
    public int insertElement(T obj, int i) {
        array.add(i, obj);
        return i;
    }

    @Override
    public T getElement(int i) {
        return array.get(i);
    }

    @Override
    public int getElementSize() {
        return array.size();
    }

    @Override
    public String toString() {
        return array.toString();
    }
}
