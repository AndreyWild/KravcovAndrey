package com.senla.util;

import java.util.Objects;
import java.util.Scanner;

public class GlobalScanner {

    private static Scanner instance;

    public GlobalScanner() {
    }

    public static Scanner getInstance() {
        return Objects.requireNonNullElse(instance, new Scanner(System.in));
    }

    public static void close() {
        instance.close();
    }

}
