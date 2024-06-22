package exam.todolist.domain.service;

import exam.todolist.application.common.NotFoundException;
import exam.todolist.domain.object.TodoEntity;
import exam.todolist.domain.repository.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class TodoService {

    @Inject
    TodoRepository todoRepository;

    public TodoEntity createTodo(TodoEntity todoEntity) {
        todoRepository.persist(todoEntity);
        return todoEntity;
    }

    public List<TodoEntity> getAllTodos() {
        return todoRepository.listAll();
    }

    public List<TodoEntity> getAllTodosBy(String username) {
        return todoRepository.find("username = ?1", username).list();
    }

    public TodoEntity getTodoBy(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id);
        if (todoEntity == null) {
            throw new NotFoundException("Todo not found: " + id);
        }
        return todoEntity;
    }

    public TodoEntity getTodoBy(String username, Long id) {
        TodoEntity todoEntity = todoRepository.find("username = ?1 and id = ?2", username, id).firstResult();
        if (todoEntity == null) {
            throw new NotFoundException("Todo not found: " + id);
        }
        return todoEntity;
    }

    public void deleteTodoBy(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteTodoBy(String username, Long id) {
        todoRepository.delete("username = ?1 and id = ?2", username, id);
    }
}
