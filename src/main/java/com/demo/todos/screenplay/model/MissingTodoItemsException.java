package com.demo.todos.screenplay.model;

public class MissingTodoItemsException extends AssertionError {
    public MissingTodoItemsException(String message) {
        super(message);
    }

    public MissingTodoItemsException(String message, Throwable cause) {
        super(message, cause);
    }
}
