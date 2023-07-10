package org.example.api;

import org.example.model.Directory;
import org.example.model.MyFile;
import org.example.util.Data;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {
    @Test
    void addFile() {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);

        systemApi.addFile(directory, myFile1);
        assertNotNull(systemApi.getFile("name.txt"));
    }

    @Test
    void removeFile() {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);
        systemApi.addFile(directory, myFile1);

        systemApi.removeFile("name.txt");
        assertNull(systemApi.getFile("name.txt"));
    }

    @Test
    void testRemoveFileFromDirectory() {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);
        systemApi.addFile(directory, myFile1);

        systemApi.removeFile(directory,"name.txt");
        assertNull(systemApi.getFile("name.txt"));
    }

    @Test
    void getFile() {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);

        directory.addFile(myFile1);
        assertNotNull(systemApi.getFile("name.txt"));
    }

    @Test
    void readFile() {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);

        directory.addFile(myFile1);
        assertEquals("Hello world",systemApi.getFile("name.txt").showContent());
    }

    @Test
    void copyFile() {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Directory directory1 = new Directory("path");
        Data.getSystem().add(directory);
        Data.getSystem().add(directory1);
        directory.addFile(myFile1);
        systemApi.copyFile(directory, directory1, "name.txt");
        assertNotNull(directory1.getFile("name.txt"));
    }

    @Test
    void moveFile() {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Directory directory1 = new Directory("path");
        Data.getSystem().add(directory);
        Data.getSystem().add(directory1);
        directory.addFile(myFile1);
        systemApi.moveFile(directory, directory1, "name.txt");
        assertNotNull(directory1.getFile("name.txt"));
        assertNull(directory.getFile("name.txt"));
    }
}