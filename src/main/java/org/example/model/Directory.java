package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Directory {

    private String path;
    private List<MyFile> files;

    public Directory(String path) {
        this.path = path;
        files = new ArrayList<>();
    }
    public List<MyFile> getFiles() {
        return files;
    }

    public MyFile getFile(String fileName) {
        MyFile temp = null;
        for(MyFile file: files) {
            if(file.getFileName().equals(fileName)) {
                temp = file;
                break;
            }
        }
        return temp;
    }

    public String getPath() {
        return path;
    }

    public MyFile addFile(MyFile myFile) {
        files.add(myFile);
        myFile.setDirectory(this);
        return myFile;
    }

    public MyFile removeFile(String fileName) {
        MyFile temp = null;
        for(MyFile myFile: files) {
            if(myFile.getFileName().equals(fileName)) {
                temp = myFile;
                files.remove(myFile);
                myFile.setDirectory(null);
                break;
            }
        }
        if(temp == null) {

        }
        return temp;
    }

}
