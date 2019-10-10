package com.medstat.med.service;

import com.medstat.med.domain.Mylike;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import com.medstat.med.repos.MyLikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    @Autowired
    MyLikeRepo myLikeRepo;

    //note implemented
    public boolean addLike(User user, Note note) {
        try {
            Mylike myLike = new Mylike();
            myLike.setAuthor(user);
            myLike.setNote(note);
            myLikeRepo.save(myLike);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
