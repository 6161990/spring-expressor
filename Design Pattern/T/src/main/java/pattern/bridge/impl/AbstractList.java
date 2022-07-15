package pattern.bridge.impl;

public interface AbstractList<T> {
    void addElement(T obj);
    T deleteElement(int i);
    int insertElement(T obj, int i);
    T getElement(int i);
    int getElementSize();
}
