package com.medstat.med.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private String id;

    private String  authorId;

    private String text;
}
