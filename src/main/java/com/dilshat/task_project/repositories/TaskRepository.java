package com.dilshat.task_project.repositories;


import com.dilshat.task_project.models.Task;
import com.dilshat.task_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByTitle(String title);

    List<Task> findAllByAuthor(User user);


}
