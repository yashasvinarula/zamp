package org.example.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.services.AddressBookImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class DataLoader {

    private DataLoader() { }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> List<T> loadData(String filePath, Class<T> clazz) {
        InputStream inputStream = AddressBookImpl.class.getClassLoader().getResourceAsStream(filePath);
        List<T> list = new ArrayList<>();
        if (inputStream == null) {
            return new ArrayList<>();
        }
        try {
            JsonNode jsonNode = MAPPER.readTree(inputStream);
            for (JsonNode node : jsonNode) {
                list.add(MAPPER.convertValue(node, clazz));
            }
        } catch (IOException e) {
            // do nothing
        }
        return list;
    }


}
