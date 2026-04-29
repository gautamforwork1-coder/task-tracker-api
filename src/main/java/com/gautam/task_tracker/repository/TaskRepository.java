package com.gautam.task_tracker.repository;

import com.gautam.task_tracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByStatus(Task.Status status);
    List<Task> findByTitleContainingIgnoreCase(String keyword);
}
