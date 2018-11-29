package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10;
    private static int counter;
    private static final Object LOCK = new Object();



    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                double a = Math.sin(13.);
                synchronized (this) {
                    counter++;
                }
            }
        });
        thread1.start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        final Object object1 = new Object();
        final Object object2 = new Object();

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    mainConcurrency.deadlock(object1, object2);
                    mainConcurrency.deadlock(object2,object1);
                }
            });
            thread.start();
            threads.add(thread);
        }

        System.out.println(counter);
    }

    private void deadlock(Object object1, Object object2) {
        synchronized (object1) {
            System.out.println("d1");
            synchronized (object2) {
                System.out.println("d2");
            }
        }

    }

    private synchronized void inc() {
        counter++;
    }
}

