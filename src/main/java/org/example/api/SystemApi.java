package org.example.api;
import org.example.model.Directory;
import org.example.model.MyFile;

public class SystemApi {
    private final IFileApi api = new FileService();

    public MyFile addFile(Directory directory, MyFile myFile) {
        return api.addFile(directory, myFile);
    }

    public MyFile removeFile(Directory directory, String fileName) {

        return api.removeFile(directory, fileName);
    }

    public MyFile removeFile(String fileName) {
        return api.removeFile(fileName);
    }

    public MyFile getFile(String fileName) {
        return api.getFile(fileName);
    }

    public String readFile(String fileName) {
        return api.readFile(fileName);
    }

    public MyFile copyFile(Directory from, Directory target, String fileName) {
        return api.copyFile(from, target, fileName);
    }

    public MyFile moveFile(Directory from, Directory target, String fileName) {
        return api.moveFile(from, target, fileName);
    }
}
