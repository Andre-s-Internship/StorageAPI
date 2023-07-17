package org.example.api;

import org.example.Exception.MyFileException;
import org.example.model.Directory;
import org.example.model.MyFile;
import org.example.util.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {
    @Test
    @DisplayName("Add file to given directory")
    void addFile() throws MyFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);
        systemApi.addFile("root", myFile1);
        assertNotNull(systemApi.getFile(myFile1.getID()));
        Data.clearSystem();
    }

    @Test
    @DisplayName("Add file to non-existing directory")
    void addFileToInvalidDirectory() throws MyFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Exception exception = assertThrows(MyFileException.class, () -> {
            systemApi.addFile("root", myFile1);
        });
        String expected = "DirectoryNotFound";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
        Data.clearSystem();
    }

    @Test
    @DisplayName("Add file with duplicate name to directory")
    void addFileDuplicate() throws MyFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);
        systemApi.addFile("root", myFile1);
        Exception exception = assertThrows(MyFileException.class, () -> systemApi.addFile("root", myFile1));
        String expectedMessage = "FileAlreadyExists";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Data.clearSystem();
    }

    @Test
    @DisplayName("Remove file")
    void removeFile() throws MyFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);
        directory.addFile(myFile1);
        assertNotNull(systemApi.removeFile(myFile1.getID()));
        Exception exception = assertThrows(MyFileException.class, () -> systemApi.getFile(myFile1.getID()));

        String expectedMessage = "FileNotFound";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Data.clearSystem();

    }


    @Test
    @DisplayName("Remove file from wrong directory")
    void removeFileException() throws MyFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Directory directory1 = new Directory("path");
        Data.getSystem().add(directory);
        directory.addFile(myFile1);
        assertNotNull(systemApi.removeFile(myFile1.getID()));
        Exception exception = assertThrows(MyFileException.class, () -> systemApi.getFile(myFile1.getID()));

        String expectedMessage = "FileNotFound!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Data.clearSystem();

    }

    @Test
    @DisplayName("Get file")
    void getFile() throws MyFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);

        directory.addFile(myFile1);
        assertNotNull(systemApi.getFile(myFile1.getID()));
        Data.clearSystem();

    }

    @Test
    @DisplayName("Get file which is not added to system")
    void getFileException()  {
        Exception exception = assertThrows(MyFileException.class, () -> {
            MyFile myFile1 = new MyFile("name.txt", "Hello world");
            SystemApi systemApi = new SystemApi();
            Directory directory = new Directory("root");
            Data.getSystem().add(directory);

            directory.addFile(myFile1);
            systemApi.getFile(100L);
        });

        String expectedMessage = "FileNotFound!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Data.clearSystem();

    }



    @Test
    @DisplayName("Read given file")
    void readFile() throws MyFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);
        directory.addFile(myFile1);

        assertEquals("Hello world",systemApi.readFile(myFile1.getID()));
        Data.clearSystem();

    }

    @Test
    @DisplayName("Copy file")
    void copyFile() throws MyFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Directory directory1 = new Directory("path");
        Data.getSystem().add(directory);
        Data.getSystem().add(directory1);
        directory.addFile(myFile1);
        systemApi.copyFile("root", "path", myFile1.getID());
        assertNotNull(directory1.getFile("name.txt"));
        Data.clearSystem();

    }

    @Test
    @DisplayName("Copy file to wrong directory")
    void copyFileException() {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Directory directory1 = new Directory("path");
        Data.getSystem().add(directory);
        directory.addFile(myFile1);
        Exception exception = assertThrows(MyFileException.class, () -> {
            systemApi.copyFile("root", "path", myFile1.getID());

        });
        assertNotNull(directory.getFile("name.txt"));
        assertNull(directory1.getFile("name.txt"));

        String expectedMessage = "DirectoryNotFound";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Data.clearSystem();
    }

    @Test
    @DisplayName("Move file")
    void moveFile() throws MyFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Directory directory1 = new Directory("path");
        Data.getSystem().add(directory);
        Data.getSystem().add(directory1);
        directory.addFile(myFile1);
        systemApi.moveFile("root", directory1.getPath(), myFile1.getID());
        assertNotNull(directory1.getFile("name.txt"));
        assertNull(directory.getFile("name.txt"));
        Data.clearSystem();
    }

    @Test
    @DisplayName("Move file to wrong directory")
    void moveFileException() {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Directory directory1 = new Directory("path");
        Data.getSystem().add(directory);
        directory.addFile(myFile1);
        Exception exception = assertThrows(MyFileException.class, () -> {
            systemApi.moveFile("root", "path", myFile1.getID());

        });
        assertNotNull(directory.getFile("name.txt"));

        String expectedMessage = "DirectoryNotFound";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Data.clearSystem();
    }

}