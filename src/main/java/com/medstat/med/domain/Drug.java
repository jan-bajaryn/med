package com.medstat.med.domain;

import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Drug {
    @NonNull
    private String name;
}
