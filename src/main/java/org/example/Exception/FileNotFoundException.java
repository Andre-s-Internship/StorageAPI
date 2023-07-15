package org.example.Exception;

public class FileNotFoundException extends InvalidFileException {
    public FileNotFoundException() {
        super("File not found!");
    }
}