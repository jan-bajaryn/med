package com.medstat.med.service;

import com.medstat.med.domain.Mylike;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import com.medstat.med.repos.MyLikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    private final MyLikeRepo myLikeRepo;

    @Autowired
    public LikesService(MyLikeRepo myLikeRepo) {
        this.myLikeRepo = myLikeRepo;
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
}
