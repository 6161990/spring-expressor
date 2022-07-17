package pattern.bridge.impl;

// Implementor : 구현 클래스에 대한 선언을 제공. 하위 클래스가 구현해야 하는 기능들 선언
public interface AbstractList<T> {
    void addElement(T obj);
    T deleteElement(int i);
    int insertElement(T obj, int i);
    T getElement(int i);
    int getElementSize();
}
