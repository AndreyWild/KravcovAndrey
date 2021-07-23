package com.senlainc.task1;

/**
 * Необходимо создать новый поток и воспроизвести все его состояния, выведя их в консоль.
 * Необходимые состояния:
 */


public class Main implements Runnable {
    public static Thread thread1;
    public static Main obj;

    public static void main(String[] args) {
        obj = new Main();
        thread1 = new Thread(obj);

        // thread1 created and is currently in the NEW state.
        System.out.println("Thread1 creating - \t\t\t" + thread1.getState());
        thread1.start();

        // thread1 moved to Runnable state
        System.out.println("Thread1 start() - \t\t\t" + thread1.getState());
    }

    public void run() {
        thread myThread = new thread();
        Thread thread2 = new Thread(myThread);

        System.out.println("Thread2 creating - \t\t\t" + thread2.getState());
        thread2.start();

        System.out.println("Thread2 start() - \t\t\t" + thread2.getState());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread2 sleep() - \t\t\t" +
                thread2.getState());
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread2 finished - \t\t\t" +
                thread2.getState());
    }

}

class thread implements Runnable {
    public void run() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread1 join() Thread2 - \t" + Main.thread1.getState());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}