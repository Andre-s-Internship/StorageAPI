package org.example.Exception;

public class InvalidDirectoryException extends Exception{
    public InvalidDirectoryException() {
        super("Directory not found!");
    }
}
