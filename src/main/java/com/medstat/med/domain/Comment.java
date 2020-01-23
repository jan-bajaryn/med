package com.medstat.med.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Comment {
    @Id
    @com.mongodb.lang.NonNull
    private String id;

    @NonNull
    private String authorId;
    @NonNull
    private String text;
}
