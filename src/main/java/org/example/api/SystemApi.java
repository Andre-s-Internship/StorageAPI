package org.example.api;
import org.example.Exception.InvalidDirectoryException;
import org.example.Exception.FileNotFoundException;
import org.example.Exception.InvalidFileException;
import org.example.Service.FileServiceImpl;
import org.example.model.Directory;
import org.example.model.MyFile;

public class SystemApi {
    private final FileServiceImpl service = new FileServiceImpl();

    public MyFile addFile(String pathToDirectory, MyFile myFile) throws InvalidFileException, InvalidDirectoryException {
        return service.addFile(service.findDirectory(pathToDirectory), myFile);
    }


    public MyFile removeFile(Long fileID) throws FileNotFoundException {
        MyFile file = service.findFileByID(fileID);
        return service.removeFile(file);
    }

    public MyFile getFile(Long fileID) throws FileNotFoundException {
        return service.getFile(fileID);
    }

    public String readFile(Long fileID) throws FileNotFoundException {
        return service.readFile(service.findFileByID(fileID));
    }

    public MyFile copyFile(String from, String target, Long fileID) throws InvalidFileException, InvalidDirectoryException {
        Directory directoryFrom = service.findDirectory(from);
        Directory directoryTarget = service.findDirectory(target);
        MyFile file = service.findFileByID(fileID);
        return service.copyFile(directoryFrom,directoryTarget,file);
    }

    public MyFile moveFile(String from, String target, Long fileID) throws InvalidFileException, InvalidDirectoryException {
        Directory directoryFrom = service.findDirectory(from);
        Directory directoryTarget = service.findDirectory(target);
        MyFile file = service.findFileByID(fileID);
        return service.moveFile(directoryFrom,directoryTarget,file);
    }
}
