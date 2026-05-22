package com.strajja.training5.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    Long id;
    String title;
    String genre;
    Integer releaseYear;
}
