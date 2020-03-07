package com.live.vladislav.Services;

import com.live.vladislav.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    @Transactional
    void deleteByDone(boolean done);
}
