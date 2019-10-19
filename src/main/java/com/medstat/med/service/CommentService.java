package com.medstat.med.service;

import com.medstat.med.domain.Comment;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import com.medstat.med.repos.CommentRepo;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final NoteRepo noteRepo;
    private final UserRepo userRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo, NoteRepo noteRepo, UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
    }

    public boolean addComment(Note note, User author, String text) {
        try {
            commentRepo.save(Comment.builder().author(author).text(text).note(note).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addCommentToNote(User user, String text, Long id) {
        Note note;
        Optional<Note> byId = noteRepo.findById(id);
        if (byId.isPresent())
            note = byId.get();
        else
            throw new IllegalArgumentException("there are no note with so id.");

        User userToAdd;
        Optional<User> byIdUser;
        try {
            byIdUser = userRepo.findById(user.getId());
        } catch (NullPointerException e) {
            return false;
        }
        if (byIdUser.isPresent())
            userToAdd = byIdUser.get();
        else
            throw new IllegalArgumentException("there no so user");


        return addComment(note, userToAdd, text);
    }
}
