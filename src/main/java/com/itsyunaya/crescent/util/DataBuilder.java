package com.itsyunaya.crescent.util;

import com.google.gson.*;
import com.itsyunaya.crescent.Crescent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataBuilder {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Paths.get("config", "crescent-data.json");

    public static void init() {
        try {
            // Ensure config directory exists
            Files.createDirectories(CONFIG_PATH.getParent());

            // If file doesn't exist, create it with an empty structure
            if (Files.notExists(CONFIG_PATH)) {
                JsonObject root = new JsonObject();
                root.add("pairs", new JsonArray()); // default empty array
                save(root);
                Crescent.LOGGER.info("Initialized crescent-data.json");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Crescent.LOGGER.error("Failed to initialize crescent-data.json");
        }
    }

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

    // Add a new press/release pair
    public static void addPair(int press, int release) {
        JsonObject root = load();

        JsonArray pairs = root.has("pairs") ? root.getAsJsonArray("pairs") : new JsonArray();

        JsonObject pair = new JsonObject();
        pair.addProperty("press", press);
        pair.addProperty("release", release);

        pairs.add(pair);
        root.add("pairs", pairs);

        save(root);
    }

    public static void removePair(int a, int b) {
        JsonObject root = load();

        if (!root.has("pairs")) {
            return; // nothing to remove
        }

        JsonArray pairs = root.getAsJsonArray("pairs");
        JsonArray updated = new JsonArray();

        for (JsonElement elem : pairs) {
            JsonObject pair = elem.getAsJsonObject();
            int press = pair.get("press").getAsInt();
            int release = pair.get("release").getAsInt();

            if (!((press == a && release == b) || (press == b && release == a))) {
                updated.add(pair);
            }
        }

        root.add("pairs", updated);
        save(root);
    }

    public static boolean pairExists(int a, int b) {
        JsonObject root = load();

        if (!root.has("pairs")) {
            return false;
        }

        JsonArray pairs = root.getAsJsonArray("pairs");

        for (JsonElement elem : pairs) {
            JsonObject pair = elem.getAsJsonObject();
            int press = pair.get("press").getAsInt();
            int release = pair.get("release").getAsInt();

            // Check both orders
            if ((press == a && release == b) || (press == b && release == a)) {
                return true;
            }
        }

        return false;
    }
}