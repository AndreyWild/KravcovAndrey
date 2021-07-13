package com.senlainc.task4;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Создать служебный поток, который будет каждые n секунд выводить системное время.
 * n задается через конструктор потока.
 */
import static java.lang.Thread.sleep;

public class Main extends Thread{
    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread(1000));
        thread.setDaemon(true);
        thread.start();
        try {
            Main.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class MyThread implements Runnable{

    private Integer seconds;

    public MyThread(Integer seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
            try {
                sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}