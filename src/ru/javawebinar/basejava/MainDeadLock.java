package ru.javawebinar.basejava;

public class MainDeadLock {
    public static void main(String[] args) {
        final Object FIRST_OBJECT = new Object();
        final Object SECOND_OBJECT = new Object();

        new Thread(() -> {
            synchronized (FIRST_OBJECT) {
                System.out.println(Thread.currentThread().getName() + ": got FIRST_OBJECT monitor");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ": trying to get SECOND_OBJECT monitor");
                synchronized (SECOND_OBJECT) {
                    System.out.println(Thread.currentThread().getName() + ": got SECOND_OBJECT monitor");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (SECOND_OBJECT) {
                System.out.println(Thread.currentThread().getName() + ": got SECOND_OBJECT monitor");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ": trying to get FIRST_OBJECT monitor");
                synchronized (FIRST_OBJECT) {
                    System.out.println(Thread.currentThread().getName() + ": got FIRST_OBJECT monitor");
                }
            }
        }).start();

    }
}

