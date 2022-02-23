package com.todo2.repository;

import com.todo2.dao.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
