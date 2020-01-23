package com.medstat.med.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Builder(toBuilder = true)
@AllArgsConstructor
@Data
public class Mylike {
    public Mylike() {
    }

    @Id
    private String id;

    private String authorId;

}

