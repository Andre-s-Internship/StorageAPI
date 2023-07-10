package org.example.api;

import org.example.model.Directory;
import org.example.model.MyFile;

public interface IFileApi {
    MyFile addFile(Directory directory, MyFile myFile);
    MyFile removeFile(Directory directory, String fileName);
    MyFile removeFile(String fileName);
    MyFile getFile(String fileName);
    String readFile(String fileName);
    MyFile copyFile(Directory from, Directory target, String fileName);
    MyFile moveFile(Directory from, Directory target, String fileName);
}