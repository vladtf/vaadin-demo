package com.live.vladislav.ui.services;

import com.live.vladislav.ui.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    @Transactional
    void deleteByDone(boolean done);
}
