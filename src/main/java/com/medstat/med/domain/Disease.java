package com.medstat.med.domain;

import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@RequiredArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class Disease {


    @NonNull
    private String name;

}
