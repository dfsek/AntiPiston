package com.dfsek.antipiston;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ConfigUtil {
    public static List<String> masterList;
    public static boolean enableMasterList;
    public static boolean masterListIsWhitelist;
    public static List<Material> masterMaterialList = new ArrayList<>();
    public static boolean enableCoreProtect;
    public static boolean debug;

    public static void load(Main main, Logger logger) {
        long start = System.nanoTime();
        logger.info("Loading configuration values...");
        main.reloadConfig();
        FileConfiguration config = main.getConfig();

        masterList = config.getStringList("master-list");
        enableMasterList = config.getBoolean("enable-master-list", true);
        masterListIsWhitelist = config.getBoolean("master-list-whitelist", false);
        enableCoreProtect = config.getBoolean("coreprotect", false);
        debug = config.getBoolean("debug", false);

        for(String m : masterList) {
            try {
                logger.info("Adding type: " + m);
                masterMaterialList.add(Material.valueOf(m.toUpperCase()));
            } catch(IllegalArgumentException e) {
                logger.severe("Invalid type: " + m);
            }
        }

        logger.info("Complete. Time elapsed: " + ((double) (System.nanoTime() - start)) / 1000000 + "ms");
    }
}
