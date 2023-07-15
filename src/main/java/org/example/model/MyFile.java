package org.example.model;

import org.example.model.Directory;

import java.util.UUID;

public class MyFile {
    private static long idCounter = 0;
    private long id;


    private String fileName;
    private Directory directory;
    private byte[] content;

    public MyFile(String name, Directory directory, String content) {
        this.id = ++idCounter;
        this.fileName = name;
        this.directory = directory;
        this.content = content.getBytes();
    }

    public MyFile(String name, String content) {
        this.id = ++idCounter;
        this.fileName = name;
        directory = null;
        this.content = content.getBytes();
    }

    public String showContent() {
        return new String(content);
    }
    public String getDirectory() {
        return directory.getPath();
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setDirectory(Directory directory) {
        this.directory = directory;
    }
    public Long getID() {return this.id;}
    public void setID(Long id) {this.id = id;}

}
