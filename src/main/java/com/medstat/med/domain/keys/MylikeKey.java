package com.medstat.med.domain.keys;

import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class MylikeKey implements Serializable{
    static final long serialVersionUID = 1L;
    public MylikeKey() {
    }


    public Long author_id;

    public Long note_id;

}
