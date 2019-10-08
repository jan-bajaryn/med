package com.medstat.med.repos;

import com.medstat.med.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    //    void deleteByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
