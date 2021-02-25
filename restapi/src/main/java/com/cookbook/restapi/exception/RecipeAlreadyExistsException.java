package com.cookbook.restapi.exception;

public class RecipeAlreadyExistsException extends Exception{

    public RecipeAlreadyExistsException() {
        super("Recipe with this name already exists");
    }

    public RecipeAlreadyExistsException(String message) {
        super(message);
    }

}
