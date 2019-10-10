package com.medstat.med.repos;

import com.medstat.med.domain.Mylike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyLikeRepo extends JpaRepository<Mylike, Long> {
    Mylike findByAuthor_Id(Long id);
}
