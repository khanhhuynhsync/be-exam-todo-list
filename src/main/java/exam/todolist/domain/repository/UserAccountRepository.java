package exam.todolist.domain.repository;

import exam.todolist.domain.object.UserAccountEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserAccountRepository implements PanacheRepository<UserAccountEntity> {

}
