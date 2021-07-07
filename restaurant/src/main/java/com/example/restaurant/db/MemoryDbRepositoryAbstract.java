package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
        //filter : db.stream에 대한 type에 대한 부분
        //getIndex() : memory db에 정의된 index
        //제네릭 타입에 와일드 카드를 걸었기 때문에(T extends MemoryDbEntity)
        // 제네릭 타입에 해당되는 변수(it)에서 getIndex로 index에 접근이 가능
        //MemoryDbEntity을 상속받은 객체라면 모두 getIndex를 가질 수 있게 되고
        //어떠한 타입(T)을 가지든지 db의 해당 index를 찾아서 첫번쨰꺼(findFirst())를 optional하게, 있을수도 없을수도
        //있는 데이터를 리턴하게 된다.
    }

    @Override
    public T save(T entity) {

        var optionalEntity = db.stream().filter(it-> it.getIndex()== entity.getIndex()).findFirst();

        if(optionalEntity.isEmpty()){
            //db에 데이터가 없는 경우
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        } else {
            //db에 이미 데이터가 있는 경우
            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            deleteById(preIndex);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex()==index).findFirst();
        if(optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
