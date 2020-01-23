package com.medstat.med.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Builder(toBuilder = true)
@AllArgsConstructor
@Data
public class Mylike {
    public Mylike() {
    }

    @Id
    private String id;

    @NonNull
    private String authorId;

}

