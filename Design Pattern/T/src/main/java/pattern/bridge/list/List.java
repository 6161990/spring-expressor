package pattern.bridge.list;

import pattern.bridge.impl.AbstractList;

// Abstraction : 추상화 개념의 상위 클래스이고 객체 구현자(Implemntor)에 대한 참조자를 관리
public abstract class List<T> {

    AbstractList<T> impl;

    public List(AbstractList<T> list) {
        this.impl = list;
    }

    public void add(T obj){
        impl.addElement(obj);
    }

    public T remove(int i){
        return impl.deleteElement(i);
    }

    public T get(int i){
        return impl.getElement(i);
    }

    public int getSize(){
        return impl.getElementSize();
    }

    @Override
    public String toString() {
        return impl.toString();
    }
}
