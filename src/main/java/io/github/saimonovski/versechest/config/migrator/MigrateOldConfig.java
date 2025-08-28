package io.github.saimonovski.versechest.config.migrator;

import dev.dejvokep.boostedyaml.YamlDocument;
import io.github.saimonovski.versechest.config.MainConfig;
import io.github.saimonovski.versechest.config.RarityConfig;
import io.github.saimonovski.versechest.entity.Chest;
import io.github.saimonovski.versechest.entity.ChestItem;
import io.github.saimonovski.versechest.entity.Rarity;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MigrateOldConfig {

    private final YamlDocument dataFile;
    private final YamlDocument mainConfigFile;
    private final MainConfig config;

    public MigrateOldConfig(YamlDocument dataFile, YamlDocument mainConfigFile, MainConfig config) {
        this.dataFile = dataFile;
        this.mainConfigFile = mainConfigFile;
        this.config = config;
    }

    public List<Chest> migratedChests(){
        List<Chest> chests = new ArrayList<>();
        for (Map<?, ?> chest : dataFile.getMapList("chestList")) {
            Location chestLocation = Location.deserialize((Map<String, Object>) chest.get("location"));
            chests.add(new Chest(chestLocation,config));
        }
        return chests;
    }
    public Map<Rarity,List<ChestItem>> migrateChestItems(){

    }
}
