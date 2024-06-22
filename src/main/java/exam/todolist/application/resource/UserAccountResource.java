package exam.todolist.application.resource;

import exam.todolist.application.dto.UserAccountDto;
import exam.todolist.domain.service.UserAccountService;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/useraccount")
@DenyAll
public class UserAccountResource {

    @Inject
    UserAccountService userAccountService;

    @Path("/create")
    @POST
    @PermitAll
    public Response createUserAccount(UserAccountDto userAccountDto) {
        userAccountService.createUserAccount(userAccountDto.toEntity());
        return Response.ok(String.format("User (%s) with role (%s) is created.", userAccountDto.username(), userAccountDto.role())).build();
    }
}
