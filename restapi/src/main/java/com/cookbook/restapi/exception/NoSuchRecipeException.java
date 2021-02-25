package com.cookbook.restapi.exception;

public class NoSuchRecipeException extends Exception{

    public NoSuchRecipeException() {
        super("Recipe with this id wasn't found");
    }

    public NoSuchRecipeException(String message) {
        super(message);
    }

}
