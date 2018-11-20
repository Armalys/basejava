package ru.javawebinar.basejava;

import java.io.File;

public class MainRecursion {
    public static void main(String[] args) {
        File dir = new File("../basejava/src");
        System.out.println("Directory: " + dir.getName());
        printDirectoryDeeply(dir);
    }

    private static void printDirectoryDeeply(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    System.out.println("\t\t" + "File: " + file.getName());
                } else {
                    System.out.println("\t" + "Directory: " + file.getName());
                    printDirectoryDeeply(file);
                }
            }
        }
    }
}
