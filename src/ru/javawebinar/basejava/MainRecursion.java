package ru.javawebinar.basejava;

import java.io.File;

public class MainRecursion {
    public static void main(String[] args) {
        File dir = new File("../basejava");
        readAllFiles(dir);
    }

    private static void readAllFiles(File directory) {
        File[] files = directory.listFiles();
//        Objects.requireNonNull(files, " directory is empty");
        for (File file : files) {
            if (file.isDirectory()) {
                readAllFiles(file);
            } else System.out.println(file.getName());
        }
    }
}
