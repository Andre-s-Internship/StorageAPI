package org.example.Exception;

public class MyFileException extends Exception {
    public MyFileException(ExceptionMessage e) {
        super(e.toString());
    }
}
