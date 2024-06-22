package exam.todolist.application.common;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DupplicateException extends RuntimeException implements ExceptionMapper<DupplicateException> {

    public DupplicateException() {
    }

    public DupplicateException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(DupplicateException exception) {
        return Response.status(Response.Status.CONFLICT)
                .entity(exception.getMessage())
                .build();
    }
}
