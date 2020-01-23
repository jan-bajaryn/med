package com.medstat.med.domain;

import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
public class Drug {
    private String name;
}
