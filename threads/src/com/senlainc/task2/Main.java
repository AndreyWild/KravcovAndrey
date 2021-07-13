package com.senlainc.task2;

import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
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

    @SneakyThrows
    public synchronized void setNameFirst(String name) {
        while (this.name.equals(name)) {
            wait();
        }
        this.name = "First";
        System.out.println("Thread name - " + name);
        notify();
    }

    @SneakyThrows
    public synchronized void setNameSecond(String name) {
        while (this.name.equals(name)) {
            wait();
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

    @lombok.SneakyThrows
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

    @lombok.SneakyThrows
    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            name.setNameSecond(Thread2.currentThread().getName());
        }
    }
}
