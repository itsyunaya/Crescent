package com.itsyunaya.crescent.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itsyunaya.crescent.Crescent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataBuilder {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Paths.get("config", "crescent-data.json");

    public static void save(JsonObject data) {
        try (FileWriter writer = new FileWriter(CONFIG_PATH.toFile())) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
            Crescent.LOGGER.error("An unknown error occurred while trying to save data to the file.");
        }
    }

    public static JsonObject load() {
        try (FileReader reader = new FileReader(CONFIG_PATH.toFile())) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
            Crescent.LOGGER.error("An unknown error occurred while trying to load data from the file.");
            return new JsonObject();
        }
    }
}
