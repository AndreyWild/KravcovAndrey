package com.senlainc.task3;

import java.util.Arrays;
import java.util.Objects;

/**
 * Написать программу, содержащую два потока – производитель и потребитель.
 * Производитель будет генерировать рандомные числа. Потребитель потреблять их.
 * Два потока разделяют общий буфер данных, размер которого ограничен. Если буфер пуст,
 * потребитель должен ждать, пока там появятся данные. Если буфер заполнен полностью,
 * производитель должен ждать, пока потребитель заберёт данные и место освободится.
 */

public class Main {
    public static void main(String[] args) {
        Numbers numbers = new Numbers();

        Thread threadProd = new Thread(new Producer(numbers));
        Thread threadCons = new Thread(new Consumer(numbers));

        threadProd.start();
        threadCons.start();
    }
}

class Numbers {
    private Integer[] numbers = new Integer[5];

    public synchronized void getNumbers() {

        while (Arrays.stream(numbers).allMatch(Objects::isNull)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Забрал числа: " + Arrays.toString(numbers));
        numbers = new Integer[5];
        notify();
    }

    public synchronized void putNumbers() {
        while (!Arrays.stream(numbers).allMatch(Objects::isNull)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 100 + 1);
        }
        System.out.println("Заполнил числами: " + Arrays.toString(numbers));
        notify();
    }
}

class Producer implements Runnable {
    Numbers numbers;

    public Producer(Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            numbers.putNumbers();
        }
    }
}

class Consumer implements Runnable {
    Numbers numbers;

    public Consumer(Numbers numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            numbers.getNumbers();
        }
    }
}
