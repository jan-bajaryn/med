package com.medstat.med.domain;

import lombok.*;


@Data
@RequiredArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
public class Symptom {

    private String name;

}
