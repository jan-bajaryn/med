package com.medstat.med.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Comment {

    @CreatedDate
    private LocalDateTime createdDate;

    @NonNull
    private String authorId;
    @NonNull
    private String text;
}
