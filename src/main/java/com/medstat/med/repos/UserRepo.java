package com.medstat.med.repos;

import com.medstat.med.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepo extends CrudRepository<User, String> {
    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
