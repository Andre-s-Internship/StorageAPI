package org.example.Service;

import org.example.Exception.InvalidDirectoryException;
import org.example.Exception.FileAlreadyExistsException;
import org.example.Exception.FileNotFoundException;
import org.example.Exception.InvalidFileException;
import org.example.model.Directory;
import org.example.model.MyFile;
import org.example.util.Data;

public class FileServiceImpl implements FileService {
    @Override
    public MyFile addFile(Directory directory, MyFile myFile) throws InvalidDirectoryException, InvalidFileException {
        if(!Data.getSystem().contains(directory)) {
            throw new InvalidDirectoryException();
        } else if(myFile == null) {
            throw new InvalidFileException();
        } else
            for(MyFile file: directory.getFiles()) {
                if(file.getFileName().equals(myFile.getFileName())){
                    throw new FileAlreadyExistsException();
                }
            }
         return directory.addFile(myFile);
    }

    @Override
    public MyFile removeFile(MyFile file) throws FileNotFoundException {
        MyFile removedFile;
        for(Directory directory: Data.getSystem()) {
            removedFile = directory.removeFile(file.getFileName());
            if(removedFile != null) {
                return removedFile;
            }
        }
        throw new FileNotFoundException();
    }

    @Override
    public MyFile getFile(Long fileID) throws FileNotFoundException {
        return findFileByID(fileID);
    }

    @Override
    public String readFile(MyFile file) throws FileNotFoundException {
        return getFile(file.getID()).showContent();
    }

    @Override
    public MyFile copyFile(Directory from, Directory target, MyFile file) throws InvalidFileException, InvalidDirectoryException {
        addFile(target, file);
        return file;
    }

    public MyFile moveFile(Directory from, Directory target, MyFile file) throws InvalidFileException, InvalidDirectoryException {
        addFile(target, file);
        removeFile(file);
        return file;
    }

    public Directory findDirectory(String path) throws InvalidDirectoryException {
        for(Directory directory: Data.getSystem()) {
            if(directory.getPath().equals(path)) {
                return directory;
            }
        }
        throw new InvalidDirectoryException();
    }

    public MyFile findFileByID(Long id) throws FileNotFoundException {
        for(Directory directory: Data.getSystem()) {
            for(MyFile myFile : directory.getFiles()) {
                if(myFile.getID().equals(id)){
                    return myFile;
                }
            }
        }
        throw new FileNotFoundException();
    }
}
