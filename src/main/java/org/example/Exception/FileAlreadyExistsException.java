package org.example.Exception;

public class FileAlreadyExistsException extends InvalidFileException{
    public FileAlreadyExistsException(){
        super("File with given name already exists in the directory");
    }
}
