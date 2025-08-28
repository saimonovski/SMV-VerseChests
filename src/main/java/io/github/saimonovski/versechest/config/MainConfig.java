package io.github.saimonovski.versechest.config;

import dev.dejvokep.boostedyaml.YamlDocument;
import io.github.saimonovski.versechest.config.ConfigPaths;
import io.github.saimonovski.versechest.entity.Rarity;
import io.github.saimonovski.versechest.service.ChestItemService;
import io.github.saimonovski.versechest.service.RarityService;
import io.github.saimonovski.versechest.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainConfig {

    private final YamlDocument configFile;

    private final BlockCreator blockCreator;
    private final ChestConfiguration chestConfiguration;
    private final Rarities rarities;

    public MainConfig(YamlDocument configFile) {
        this.configFile = configFile;
        this.blockCreator = new BlockCreator();
        this.chestConfiguration = new ChestConfiguration();
        this.rarities = new Rarities();
    }

    public BlockCreator getBlockCreator() {
        return blockCreator;
    }

    public ChestConfiguration getChestConfiguration() {
        return chestConfiguration;
    }

    public Rarities getRarities() {
        return rarities;
    }

    public class BlockCreator {
        private final Material material;
        private final String name;
        private final List<String> lore;
        private final ItemStack itemstack;
        private BlockCreator() {
            this.material = configFile.getEnum(ConfigPaths.BlockCreator.MATERIAL,Material.class,Material.BEDROCK);
            this.name = configFile.getString(ConfigPaths.BlockCreator.NAME, "CreatorBlock");
            this.lore = configFile.getStringList(ConfigPaths.BlockCreator.LORE);
            this.itemstack = constructItem();
        }

        public Material getMaterial() { return material; }
        public String getName() { return name; }
        public List<String> getLore() { return lore; }
        private ItemStack constructItem(){
            var item =  new ItemStack(getMaterial());
            item.editMeta(meta -> {
                meta.displayName(ChatUtil.fix(getName()));
                meta.lore(ChatUtil.fix(getLore()));
            });
            return item;
        }
        public ItemStack getItemStack(){
            return this.itemstack;
        }
    }

    public class ChestConfiguration {
        private final Material material;
        private final String title;
        private final int minItems, maxItems, size;

        private ChestConfiguration() {
            this.material = configFile.getEnum(ConfigPaths.ChestConfiguration.MATERIAL,Material.class, Material.CHEST);
            this.title = configFile.getString(ConfigPaths.ChestConfiguration.TITLE, "VerseChests");
            this.minItems = configFile.getInt(ConfigPaths.ChestConfiguration.MIN_ITEMS, 10);
            this.maxItems = configFile.getInt(ConfigPaths.ChestConfiguration.MAX_ITEMS, 25);
            this.size = configFile.getInt(ConfigPaths.ChestConfiguration.CHEST_SIZE, 27);
        }

        public Material getMaterial() { return material; }
        public String getTitle() { return title; }
        public int getMinItems() { return minItems; }
        public int getMaxItems() { return maxItems; }
        public int getSize() { return size; }
    }

    public class Rarities {
        RarityService service;

        private Rarities() {

        }
        private void loadRarityService(){
            this.service = new RarityService(loadRarityMap());
        }
        private Map<String, Rarity> loadRarityMap(){
              Map<String, Rarity> rarityMap = new HashMap<>();
            if (configFile.contains(ConfigPaths.Rarity.SECTION)) {
                for (Object key : configFile.getSection(ConfigPaths.Rarity.SECTION).getKeys()) {
                    String path = ConfigPaths.Rarity.SECTION + "." + key;
                    int min = configFile.getInt(path + ".minItems");
                    int max = configFile.getInt(path + ".maxItems");
                    int chance = configFile.getInt(path + ".chanceToDrop");
                    rarityMap.put((String) key, new Rarity(new RarityConfig(MainConfig.this,min,max,chance), (String) key));
                }
            }
            return rarityMap;
        }
        public RarityService getService(){
            return this.service;
        }

    }


}
