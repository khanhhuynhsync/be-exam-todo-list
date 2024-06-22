package exam.todolist.application.resource;

import exam.todolist.application.dto.TodoDto;
import exam.todolist.domain.object.TodoEntity;
import exam.todolist.domain.service.TodoService;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/todos")
@DenyAll
public class TodoResource {

    @Context
    SecurityContext securityContext;

    @Inject
    TodoService todoService;

    @Path("/listall")
    @GET
    @RolesAllowed("admin")
    public Response getAllTodos() {
        List<TodoEntity> todoEntities = todoService.getAllTodos();
        List<TodoDto> todoDtos = todoEntities.stream().map(TodoDto::fromEntity).toList();
        return Response.ok(todoDtos).build();
    }

    @Path("/{id}")
    @GET
    @RolesAllowed("admin")
    public Response getTodoById(@PathParam("id") Long id) {
        TodoEntity todoEntity = todoService.getTodoBy(id);
        return Response.ok(TodoDto.fromEntity(todoEntity)).build();
    }

    @Path("/{id}")
    @DELETE
    @RolesAllowed("admin")
    public Response updateTodo(@PathParam("id") Long id) {
        todoService.deleteTodoBy(id);
        return Response.ok().build();
    }

    @Path("/create")
    @POST
    @RolesAllowed({"admin", "user"})
    public Response createTodo(TodoDto todoDto) {
        String username = securityContext.getUserPrincipal().getName();
        TodoEntity todoEntity = todoService.createTodo(todoDto.toEntity(username));
        return Response.ok(TodoDto.fromEntity(todoEntity)).build();
    }

    @Path("/me")
    @GET
    @RolesAllowed({"admin", "user"})
    public Response getAllTodosByUser() {
        String username = securityContext.getUserPrincipal().getName();
        List<TodoEntity> todoEntities = todoService.getAllTodosBy(username);
        return Response.ok(todoEntities.stream().map(TodoDto::fromEntity).toList()).build();
    }

    @Path("/me/{id}")
    @GET
    @RolesAllowed({"admin", "user"})
    public Response getTodoByUserAndId(@PathParam("id") Long id) {
        String username = securityContext.getUserPrincipal().getName();
        TodoEntity todoEntity = todoService.getTodoBy(username, id);
        return Response.ok(TodoDto.fromEntity(todoEntity)).build();
    }

    @Path("/me/{id}")
    @DELETE
    @RolesAllowed({"admin", "user"})
    public Response deleteTodoByUserAndId(@PathParam("id") Long id) {
        String username = securityContext.getUserPrincipal().getName();
        todoService.deleteTodoBy(username, id);
        return Response.ok().build();
    }
}
