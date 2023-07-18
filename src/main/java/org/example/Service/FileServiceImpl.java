package org.example.Service;

import org.example.Exception.ExceptionMessage;
import org.example.Exception.MyFileException;
import org.example.model.Directory;
import org.example.model.MyFile;
import org.example.util.Data;

import java.util.UUID;

public class FileServiceImpl implements FileService {
    @Override
    public MyFile addFile(Directory directory, MyFile myFile) throws MyFileException {
        if(!Data.getSystem().contains(directory)) {
            throw new MyFileException(ExceptionMessage.DirectoryNotFound);
        } else if(myFile == null) {
            throw new MyFileException(ExceptionMessage.InvalidFile);
        } else
            for(MyFile file: directory.getFiles()) {
                if(file.getFileName().equals(myFile.getFileName())){
                    throw new MyFileException(ExceptionMessage.FileAlreadyExists);
                }
            }
         return directory.addFile(myFile);
    }

    @Override
    public MyFile removeFile(MyFile file) throws MyFileException {
        MyFile removedFile;
        for(Directory directory: Data.getSystem()) {
            removedFile = directory.removeFile(file.getFileName());
            if(removedFile != null) {
                return removedFile;
            }
        }
        throw new MyFileException(ExceptionMessage.FileNotFound);
    }

    @Override
    public MyFile getFile(UUID fileID) throws MyFileException {
        return findFileByID(fileID);
    }

    @Override
    public String readFile(MyFile file) throws MyFileException {
        return getFile(file.getID()).showContent();
    }

    @Override
    public MyFile copyFile(Directory from, Directory target, MyFile file) throws MyFileException {
        addFile(target, file);
        return file;
    }

    public MyFile moveFile(Directory from, Directory target, MyFile file) throws MyFileException {
        addFile(target, file);
        removeFile(file);
        return file;
    }

    public Directory findDirectory(String path) throws MyFileException {
        for(Directory directory: Data.getSystem()) {
            if(directory.getPath().equals(path)) {
                return directory;
            }
        }
        throw new MyFileException(ExceptionMessage.DirectoryNotFound);
    }

    public MyFile findFileByID(UUID id) throws MyFileException {
        for(Directory directory: Data.getSystem()) {
            for(MyFile myFile : directory.getFiles()) {
                if(myFile.getID().equals(id)){
                    return myFile;
                }
            }
        }
        throw new MyFileException(ExceptionMessage.FileNotFound);
    }
}
