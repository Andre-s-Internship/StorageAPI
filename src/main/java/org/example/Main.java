package org.example;

import org.example.model.Directory;
import org.example.model.MyFile;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Directory> directories = new LinkedList<>();
        Directory root = new Directory("C:");
        directories.add(root);
        directories.add(new Directory("/users"));
        directories.add(new Directory("/andre"));
        root.addFile(new MyFile("file.txt", "Hello world!"));
        System.out.println(root.getFile("file.txt").showContent());
    }
}
