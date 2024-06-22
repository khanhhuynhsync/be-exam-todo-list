package exam.todolist.domain.service;

import exam.todolist.application.common.DupplicateException;
import exam.todolist.domain.object.UserAccountEntity;
import exam.todolist.domain.repository.UserAccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UserAccountService {

    @Inject
    UserAccountRepository userAccountRepository;

    public UserAccountEntity createUserAccount(UserAccountEntity userAccountEntity) {
        UserAccountEntity existedUserAccount = getUserAccountBy(userAccountEntity.getUsername());
        if (existedUserAccount != null) {
            throw new DupplicateException("User account existed: " + userAccountEntity.getUsername());
        }
        userAccountRepository.persist(userAccountEntity);
        return userAccountEntity;
    }

    public UserAccountEntity getUserAccountBy(String username) {
        return userAccountRepository.find("username = ?1", username).firstResult();
    }
}
