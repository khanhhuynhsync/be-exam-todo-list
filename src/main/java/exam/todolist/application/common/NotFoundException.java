package exam.todolist.application.common;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundException extends RuntimeException implements ExceptionMapper<NotFoundException> {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .build();
    }
}
