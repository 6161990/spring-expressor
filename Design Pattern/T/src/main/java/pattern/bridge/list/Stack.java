package pattern.bridge.list;

import pattern.bridge.impl.AbstractList;

public class Stack<T> extends List<T> {

    public Stack(AbstractList<T> list) {
        super(list);
    }

    public void push(T obj){
        impl.insertElement(obj, 0);
    }

    public T pop(){
        return impl.deleteElement(0);
    }
}
