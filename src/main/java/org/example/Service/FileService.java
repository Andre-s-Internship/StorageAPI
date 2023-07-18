package org.example.Service;
import org.example.Exception.MyFileException;
import org.example.model.Directory;
import org.example.model.MyFile;

import java.util.UUID;

public interface FileService {
    MyFile addFile(Directory directory, MyFile myFile) throws MyFileException;
    MyFile removeFile(MyFile file) throws MyFileException;
    MyFile getFile(UUID fileID) throws MyFileException;
    String readFile(MyFile file) throws MyFileException;
    MyFile copyFile(Directory from, Directory target, MyFile file) throws MyFileException;
    MyFile moveFile(Directory from, Directory target, MyFile file) throws MyFileException;
}