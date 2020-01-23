package com.medstat.med.service;

import com.medstat.med.domain.Mylike;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class LikesService {

    private final UserRepo userRepo;
    private final NoteRepo noteRepo;

    @Autowired
    public LikesService(UserRepo userRepo, NoteRepo noteRepo) {
        this.userRepo = userRepo;
        this.noteRepo = noteRepo;
    }

    //note implemented
    public boolean addLike(User user, Note note) {
        try {
            note.getMylikes().add(Mylike.builder().authorId(user.getId()).build());
            noteRepo.save(note);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int addUserLike(User user, String id) {
        Optional<User> byIdUser = userRepo.findById(user.getId());
        Optional<Note> byIdNote = noteRepo.findById(id);

        Note note = byIdNote.get();


//        Mylike mylike_test = myLikeRepo.findByAuthor_IdAndNote_Id(user.getId(), id);
        Mylike myLikeTest = note.getMylikes().stream()
                .filter(l -> l.getAuthorId().equals(user.getId()))
                .findAny().orElse(null);


        if (byIdUser.isPresent() && myLikeTest == null) {
            note.getMylikes().add(Mylike.builder().authorId(user.getId()).build());
            noteRepo.save(note);
            log.info("mylike have been successfully saved");
        } else if (myLikeTest != null) {
            deleteLike(myLikeTest, note);
        }
        return note.getMylikes().size();
    }

    @Transactional
    public void deleteLike(Mylike myLikeTest, Note note) {
        note.getMylikes().remove(myLikeTest);
        noteRepo.save(note);
    }

    public boolean isLikeExists(User user, String noteId) {
        Optional<User> byIdUser = userRepo.findById(user.getId());
        Optional<Note> byIdNote = noteRepo.findById(noteId);
        if (byIdNote.isPresent() && byIdUser.isPresent()) {
            Note note = byIdNote.get();
            User userFound = byIdUser.get();

            Mylike mylike = note.getMylikes().stream()
                    .filter(l -> l.getAuthorId().equals(userFound.getId()))
                    .findAny().orElse(null);
            return mylike != null;
        }
        return false;
    }
}
