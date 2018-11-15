package ru.javawebinar.basejava;

import java.io.File;

public class MainRecursion {
    public static void main(String[] args) {
        File dir = new File("../basejava");
        printDirectoryDeeply(dir);
    }

    private static void printDirectoryDeeply(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("\n" + "Directory: " + file.getName());
                    printDirectoryDeeply(file);
                } else System.out.println("\t" + "File: " + file.getName());
            }
        }
    }
}
