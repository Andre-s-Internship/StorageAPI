package org.example.Service;

import org.example.Exception.InvalidDirectoryException;
import org.example.Exception.FileNotFoundException;
import org.example.Exception.InvalidFileException;
import org.example.model.Directory;
import org.example.model.MyFile;

public interface FileService {
    MyFile addFile(Directory directory, MyFile myFile) throws InvalidDirectoryException, InvalidFileException;
    MyFile removeFile(MyFile file) throws FileNotFoundException;
    MyFile getFile(Long fileID) throws FileNotFoundException;
    String readFile(MyFile file) throws FileNotFoundException;
    MyFile copyFile(Directory from, Directory target, MyFile file) throws InvalidFileException, InvalidDirectoryException;
    MyFile moveFile(Directory from, Directory target, MyFile file) throws InvalidFileException, InvalidDirectoryException;
}