package org.example.api;

import org.example.model.Directory;
import org.example.model.MyFile;
import org.example.util.Data;

public class FileService implements IFileApi {
    public FileService() {}

    @Override
    public MyFile addFile(Directory directory, MyFile myFile) {
        if(!Data.getSystem().contains(directory)) {
            System.out.println("Directory doesn't exist");
            return null;
        } else if(myFile == null) {
            System.out.println("File is not valid");
            return null;
        } else return directory.addFile(myFile);
    }

    @Override
    public MyFile removeFile(Directory directory, String fileName) {
        if(!Data.getSystem().contains(directory)){
            System.out.println("Directory doesn't exist");
            return null;
        }
        MyFile temp = directory.removeFile(fileName);
        if (temp == null) {
            System.out.println("File not found");
            return null;
        } else return directory.removeFile(fileName);
    }

    @Override
    public MyFile removeFile(String fileName) {
        MyFile removedFile = null;
        for(Directory directory: Data.getSystem()) {
            removedFile = directory.removeFile(fileName);
            if(removedFile != null) {
                return removedFile;
            }
        }
        System.out.println("File not found");
        return null;
    }

    @Override
    public MyFile getFile(String fileName) {
        for (Directory directory: Data.getSystem()) {
            if(directory.getFile(fileName) != null) {
                return directory.getFile(fileName);
            }
        }
        return null;
    }

    @Override
    public String readFile(String fileName) {
        return new String(getFile(fileName).showContent());
    }

    @Override
    public MyFile copyFile(Directory from, Directory target, String fileName) {
        MyFile file = getFile(fileName);
        addFile(target, file);
        return file;
    }

    public MyFile moveFile(Directory from, Directory target, String fileName) {
        MyFile file = from.getFile(fileName);
        addFile(target, file);
        removeFile(from, fileName);
        return file;
    }
}
