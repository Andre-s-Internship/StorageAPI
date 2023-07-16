package org.example.api;

import org.example.Exception.FileAlreadyExistsException;
import org.example.Exception.InvalidDirectoryException;
import org.example.Exception.FileNotFoundException;
import org.example.Exception.InvalidFileException;
import org.example.model.Directory;
import org.example.model.MyFile;
import org.example.util.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {
    @Test
    @DisplayName("Add file to given directory")
    void addFile() throws InvalidFileException, InvalidDirectoryException {
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
    void addFileToInvalidDirectory() throws InvalidFileException, InvalidDirectoryException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Exception exception = assertThrows(InvalidDirectoryException.class, () -> {
            systemApi.addFile("root", myFile1);
        });
        String expected = "Directory not found!";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
        Data.clearSystem();
    }

    @Test
    @DisplayName("Add file with duplicate name to directory")
    void addFileDuplicate() throws InvalidFileException, InvalidDirectoryException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);
        systemApi.addFile("root", myFile1);
        Exception exception = assertThrows(FileAlreadyExistsException.class, () -> systemApi.addFile("root", myFile1));
        String expectedMessage = "File with given name already exists in the directory";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Data.clearSystem();
    }

    @Test
    @DisplayName("Remove file")
    void removeFile() throws InvalidFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Data.getSystem().add(directory);
        directory.addFile(myFile1);
        assertNotNull(systemApi.removeFile(myFile1.getID()));
        Exception exception = assertThrows(FileNotFoundException.class, () -> systemApi.getFile(myFile1.getID()));

        String expectedMessage = "File not found!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Data.clearSystem();

    }


    @Test
    @DisplayName("Remove file from wrong directory")
    void removeFileException() throws InvalidFileException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Directory directory1 = new Directory("path");
        Data.getSystem().add(directory);
        directory.addFile(myFile1);
        assertNotNull(systemApi.removeFile(myFile1.getID()));
        Exception exception = assertThrows(FileNotFoundException.class, () -> systemApi.getFile(myFile1.getID()));

        String expectedMessage = "File not found!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Data.clearSystem();

    }

    @Test
    @DisplayName("Get file")
    void getFile() throws FileNotFoundException {
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
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            MyFile myFile1 = new MyFile("name.txt", "Hello world");
            SystemApi systemApi = new SystemApi();
            Directory directory = new Directory("root");
            Data.getSystem().add(directory);

            directory.addFile(myFile1);
            systemApi.getFile(100L);
        });

        String expectedMessage = "File not found!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Data.clearSystem();

    }



    @Test
    @DisplayName("Read given file")
    void readFile() throws FileNotFoundException {
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
    void copyFile() throws InvalidFileException, InvalidDirectoryException {
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
        Exception exception = assertThrows(InvalidDirectoryException.class, () -> {
            systemApi.copyFile("root", "path", myFile1.getID());

        });
        assertNotNull(directory.getFile("name.txt"));
        assertNull(directory1.getFile("name.txt"));

        String expectedMessage = "Directory not found!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Data.clearSystem();
    }

    @Test
    @DisplayName("Move file")
    void moveFile() throws InvalidFileException, InvalidDirectoryException {
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
    void moveFileException() throws InvalidFileException, InvalidDirectoryException {
        MyFile myFile1 = new MyFile("name.txt", "Hello world");
        SystemApi systemApi = new SystemApi();
        Directory directory = new Directory("root");
        Directory directory1 = new Directory("path");
        Data.getSystem().add(directory);
        directory.addFile(myFile1);
        Exception exception = assertThrows(InvalidDirectoryException.class, () -> {
            systemApi.moveFile("root", "path", myFile1.getID());

        });
        assertNotNull(directory.getFile("name.txt"));

        String expectedMessage = "Directory not found!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Data.clearSystem();
    }

}