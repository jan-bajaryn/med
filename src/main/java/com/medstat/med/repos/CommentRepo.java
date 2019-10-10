package com.medstat.med.repos;

import com.medstat.med.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Transactional
    void deleteByAuthor_Id(Long id);
}
