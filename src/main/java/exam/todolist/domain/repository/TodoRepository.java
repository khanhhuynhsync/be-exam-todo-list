package exam.todolist.domain.repository;

import exam.todolist.domain.object.TodoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TodoRepository implements PanacheRepository<TodoEntity> {

}
