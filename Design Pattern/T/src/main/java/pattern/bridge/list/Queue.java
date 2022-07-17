package pattern.bridge.list;

import pattern.bridge.impl.AbstractList;

// RefinedAbstraction : 추상화 개념의 확장된 기능을 정의
public class Queue<T> extends List<T> {

    public Queue(AbstractList<T> list) {
        super(list);
        System.out.println("Queue 를 구현합니다.");
    }

    public void enQueue(T obj){
        impl.addElement(obj);
    }

    public T deQueue(){
        return impl.deleteElement(0);
    }
}
