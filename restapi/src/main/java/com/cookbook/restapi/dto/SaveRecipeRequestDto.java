package com.cookbook.restapi.dto;

import lombok.Data;

@Data
public class SaveRecipeRequestDto {
    private String name;
    private String description;
    private Long parentId;
}
