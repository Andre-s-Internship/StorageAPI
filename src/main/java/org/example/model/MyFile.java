package org.example.model;

import org.example.model.Directory;

public class MyFile {

    private String fileName;
    private Directory directory;
    private byte[] content;

    public MyFile(String name, Directory directory, String content) {
        this.fileName = name;
        this.directory = directory;
        this.content = content.getBytes();
    }

    public MyFile(String name, String content) {
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

}
