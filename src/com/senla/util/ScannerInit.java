package com.senla.util;

public class ScannerInit {

    private ScannerInit() {
    }

    private static java.util.Scanner INSTANCE;

    public static java.util.Scanner getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new java.util.Scanner(System.in);
        }
        return INSTANCE;
    }

//    public static void closeScanner(){
//        INSTANCE.close();
//    }
}
