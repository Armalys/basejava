package ru.javawebinar.basejava;

public class MainDeadLock {
    public static void main(String[] args) {
        final Object FIRST_OBJECT = new Object();
        final Object SECOND_OBJECT = new Object();

        deadLock(FIRST_OBJECT, SECOND_OBJECT);
        deadLock(SECOND_OBJECT, FIRST_OBJECT);
    }


    private static void deadLock(Object firstObject, Object secondObject) {
        new Thread(() -> {
            synchronized (firstObject) {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + ": got " + secondObject + " monitor");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(threadName + ": trying to get " + firstObject + " monitor");
                synchronized (secondObject) {
                    System.out.println(threadName + ": got " + firstObject + " monitor");
                }
            }
        }).start();
    }
}

