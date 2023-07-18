package org.example.model;

import java.util.UUID;

public class MyFile {
    private UUID id;


    private String fileName;
    private Directory directory;
    private byte[] content;

    public MyFile(String name, Directory directory, String content) {
        this.id = UUID.randomUUID();
        this.fileName = name;
        this.directory = directory;
        this.content = content.getBytes();
    }

    public MyFile(String name, String content) {
        this.id = UUID.randomUUID();
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
    public UUID getID() {return this.id;}
    public void setID(UUID id) {this.id = id;}

}
