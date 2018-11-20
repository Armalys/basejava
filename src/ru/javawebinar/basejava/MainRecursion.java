package ru.javawebinar.basejava;

import java.io.File;

public class MainRecursion {
    public static void main(String[] args) {
        File dir = new File("../basejava/src");
        String space = "";
        printDirectoryDeeply(dir, space);
    }

    private static void printDirectoryDeeply(File directory, String space) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    System.out.println(space + "File: " + file.getName());
                } else {
                    System.out.println(space + "Directory: " + file.getName());
                    printDirectoryDeeply(file, space + "\t");
                }
            }
        }
    }
}
