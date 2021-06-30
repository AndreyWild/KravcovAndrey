package com.senla.util.id_generator;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Scanner;

public class IDControlInFile {

    private static final Logger LOGGER = Logger.getLogger(IDControlInFile.class.getName());

    public static Long receiveId(File file) {
        Long id = null;
        try (FileReader reader = new FileReader(file);
             Scanner scanner = new Scanner(reader)) {
            id = Long.parseLong(scanner.nextLine());
        } catch (FileNotFoundException e) {
            LOGGER.warn(e.getMessage(), e);
            e.printStackTrace();
        } catch (IOException ex) {
            LOGGER.warn(ex.getMessage(), ex);
            ex.printStackTrace();
        }
        return id;
    }

    public static void saveId(Long id, File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(String.valueOf(id));
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}


