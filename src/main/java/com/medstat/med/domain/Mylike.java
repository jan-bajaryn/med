package com.medstat.med.domain;

import com.medstat.med.domain.keys.MylikeKey;
import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "mylike")
@Builder(toBuilder = true)
@AllArgsConstructor
@Data
public class Mylike {
    public Mylike() {
    }
    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @EmbeddedId
    public MylikeKey mylikeKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("author_id")
    public User author;

    @ManyToOne
    @JoinColumn(name = "note_id")
    @MapsId("note_id")
    public Note note;

//    @CreationTimestamp
//    private LocalDateTime createDateTime;

}

