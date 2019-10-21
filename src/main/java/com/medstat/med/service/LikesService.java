package com.medstat.med.service;

import com.medstat.med.domain.Mylike;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import com.medstat.med.domain.keys.MylikeKey;
import com.medstat.med.repos.MyLikeRepo;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
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
            MylikeKey mylikeKey = new MylikeKey(user.getId(),note.getId());
            myLikeRepo.save(Mylike.builder().mylikeKey(mylikeKey)
                    .author(user)
                    .note(note)
                    .build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int addUserLike(User user, Long id) {
        Optional<User> byIdUser = userRepo.findById(user.getId());
        Optional<Note> byIdNote = noteRepo.findById(id);

        Mylike mylike_test = myLikeRepo.findByAuthor_IdAndNote_Id(user.getId(), id);
        if (byIdUser.isPresent() && byIdNote.isPresent() && mylike_test == null) {
            MylikeKey mylikeKey = new MylikeKey(byIdUser.get().getId(),byIdNote.get().getId());
            Mylike mylike = Mylike.builder().mylikeKey(mylikeKey)
                    .author(byIdUser.get())
                    .note(byIdNote.get())
                    .build();
            myLikeRepo.save(mylike);
            log.info("mylike have been huccessfully saved");
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

            if (myLikeRepo.findByAuthor_IdAndNote_Id(user_get.getId(), note.getId()) != null)
                return true;
        }
        return false;
    }
}
