package com.senlainc.task2;

/**
 * Создать 2 потока, которые будут по очереди выводить свое имя в консоль.
 */

public class Main {

    public static void main(String[] args) {
        Name name = new Name();

        Thread1 thread1 = new Thread1(name);
        thread1.setName("First");

        Thread2 thread2 = new Thread2(name);
        thread2.setName("Second");

        thread1.start();
        thread2.start();
    }
}

class Name {
    private String name = "";

    public synchronized void setNameFirst(String name) {
        while (this.name.equals(name)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = "First";
        System.out.println("Thread name - " + name);
        notify();
    }

    public synchronized void setNameSecond(String name) {
        while (this.name.equals(name)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = "Second";
        System.out.println("Thread name - " + name);
        notify();
    }
}

class Thread1 extends Thread {
    Name name;

    public Thread1(Name name) {
        this.name = name;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            name.setNameFirst(Thread1.currentThread().getName());
        }
    }
}

class Thread2 extends Thread {
    Name name;

    public Thread2(Name name) {
        this.name = name;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            name.setNameSecond(Thread2.currentThread().getName());
        }
    }
}
