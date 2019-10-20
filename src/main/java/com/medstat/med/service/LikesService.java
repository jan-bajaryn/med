package com.medstat.med.service;

import com.medstat.med.domain.Mylike;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import com.medstat.med.repos.MyLikeRepo;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikesService {

    private final MyLikeRepo myLikeRepo;
    private final UserRepo userRepo;
    private final NoteRepo noteRepo;

    @Autowired
    public LikesService(MyLikeRepo myLikeRepo, UserRepo userRepo, NoteRepo noteRepo) {
        this.myLikeRepo = myLikeRepo;
        this.userRepo = userRepo;
        this.noteRepo = noteRepo;
    }

    //note implemented
    public boolean addLike(User user, Note note) {
        try {
            myLikeRepo.save(Mylike.builder().author(user).note(note).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int addUserLike(User user, Long id) {
        Optional<User> byIdUser = userRepo.findById(user.getId());
        Optional<Note> byIdNote = noteRepo.findById(id);

        Mylike mylike_test = myLikeRepo.findByAuthor_IdAndAndNote_Id(user.getId(), id);
        if (byIdUser.isPresent() && byIdNote.isPresent() && mylike_test == null) {
            Mylike mylike = Mylike.builder().author(byIdUser.get()).note(byIdNote.get()).build();
            myLikeRepo.save(mylike);
        } else if (mylike_test != null) {
            myLikeRepo.delete(mylike_test);
        }

        return myLikeRepo.countAllByNote_Id(id);
    }

    public boolean isLikeExists(User user, Long note_id) {
        Optional<User> byIdUser = userRepo.findById(user.getId());
        Optional<Note> byIdNote = noteRepo.findById(note_id);
        if (byIdNote.isPresent() && byIdUser.isPresent()) {
            Note note = byIdNote.get();
            User user_get = byIdUser.get();

            if (myLikeRepo.findByAuthor_IdAndAndNote_Id(user_get.getId(), note.getId()) != null)
                return true;
        }
        return false;
    }
}
