package exam.todolist.application.dto;

import exam.todolist.domain.object.TodoEntity;

public record TodoDto(
        String id,
        String username,
        String title,
        String content,
        boolean isDone
)
{
    public TodoDto {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content must not be blank");
        }
    }

    public TodoEntity toEntity(String username) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setUsername(username);
        todoEntity.setTitle(this.title);
        todoEntity.setContent(this.content);
        todoEntity.setDone(this.isDone);
        return todoEntity;
    }

    public static TodoDto fromEntity(TodoEntity todoEntity) {
        return new TodoDto(
                String.valueOf(todoEntity.getId()),
                todoEntity.getUsername(),
                todoEntity.getTitle(),
                todoEntity.getContent(),
                todoEntity.isDone()
        );
    }
}
