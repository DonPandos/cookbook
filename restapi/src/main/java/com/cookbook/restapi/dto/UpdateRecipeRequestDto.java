package com.cookbook.restapi.dto;

import lombok.Data;

@Data
public class UpdateRecipeRequestDto {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
}
