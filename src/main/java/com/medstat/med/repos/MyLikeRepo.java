package com.medstat.med.repos;

import com.medstat.med.domain.Mylike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.DoubleStream;

public interface MyLikeRepo extends JpaRepository<Mylike, Long> {
    Mylike findByAuthor_Id(Long id);

    Mylike findByAuthor_IdAndNote_Id(Long authorId, Long noteId);

    int countAllByNote_Id(Long id);
}
