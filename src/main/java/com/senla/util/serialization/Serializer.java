package com.senla.util.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.model.AEntity;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Serializer<T extends AEntity> {

    public void saveToJsonFile(File file, List<T> list) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> getFromJsonFile(File file, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
//            Type type = new TypeToken<ArrayList<Class>>() {}.getType();
            // Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, ArrayList.class, clazz); // - рабочий
            Type type = new ListParameterizedType(clazz);
            list = new Gson().fromJson(reader, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

