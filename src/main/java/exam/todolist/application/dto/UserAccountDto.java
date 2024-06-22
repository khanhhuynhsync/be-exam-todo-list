package exam.todolist.application.dto;

import exam.todolist.domain.object.UserAccountEntity;
import io.quarkus.elytron.security.common.BcryptUtil;

public record UserAccountDto(
        String username,
        String password,
        String role
)
{
    public UserAccountDto {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username must not be blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password must not be blank");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role must not be blank");
        }
    }

    public UserAccountEntity toEntity() {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setUsername(this.username);
        userAccountEntity.setPassword(BcryptUtil.bcryptHash(this.password));
        userAccountEntity.setRole(this.role);
        return userAccountEntity;
    }

    public static UserAccountDto fromEntity(UserAccountEntity userAccountEntity) {
        return new UserAccountDto(
                userAccountEntity.getUsername(),
                userAccountEntity.getPassword(),
                userAccountEntity.getRole()
        );
    }
}
