package com.example.springproductionready.springproductionready.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    private String title;

    private String description;

}
