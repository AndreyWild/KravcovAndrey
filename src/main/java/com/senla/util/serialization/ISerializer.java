package com.senla.util.serialization;

import com.senla.model.AEntity;

import java.io.File;
import java.util.List;

public interface ISerializer<T extends AEntity> {

    void saveToJsonFile(File file, List<T> list);

    List<T> getFromJsonFile(File file, Class<T> clazz);

}
