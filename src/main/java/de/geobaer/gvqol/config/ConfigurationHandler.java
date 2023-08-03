package de.geobaer.gvqol.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigurationHandler {

    private static ConfigurationObject config;

    public static void loadConfig() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File configFile = getConfigFile();
        try {
            if(!configFile.exists()) {
                configFile.createNewFile();
                FileWriter writer = new FileWriter(configFile);
                writer.write(gson.toJson(new ConfigurationObject()));
                writer.close();
            }
            FileReader reader = new FileReader(configFile);
            config = gson.fromJson(IOUtils.toString(reader), ConfigurationObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isVoidTravel() {
        return config.voidTravel;
    }
    public static boolean isChestGraves() {
        return config.chestGraves;
    }
    public static boolean isBowLooting() {
        return config.bowLooting;
    }
    public static boolean isAnvilCost() {
        return config.anvilCost;
    }
    public static boolean isAnvilRename() {
        return config.anvilRename;
    }

    private static File getConfigFile() {
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), "gvqol.json");
    }

}

class ConfigurationObject {
    boolean voidTravel = true;
    boolean chestGraves = true;
    boolean bowLooting = true;
    boolean anvilCost = true;
    boolean anvilRename = true;
}
