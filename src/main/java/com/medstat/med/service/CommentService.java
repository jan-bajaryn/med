package com.medstat.med.service;

import com.medstat.med.domain.Comment;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import com.medstat.med.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;

    public boolean addComment(Note note, User author, String text) {
        try {
            commentRepo.save(Comment.builder().author(author).text(text).note(note).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
