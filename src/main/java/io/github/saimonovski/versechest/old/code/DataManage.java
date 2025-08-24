package io.github.saimonovski.versechest.old.code;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import dev.dejvokep.boostedyaml.spigot.SpigotSerializer;
import io.github.saimonovski.versechest.core.VerseChest;
import io.github.saimonovski.versechest.old.code.configs.Config;
import io.github.saimonovski.versechest.old.code.configs.MessageConfig;
import io.github.saimonovski.versechest.old.code.configs.rarity.Epic;
import io.github.saimonovski.versechest.old.code.configs.rarity.Legendary;
import io.github.saimonovski.versechest.old.code.configs.rarity.Mythic;
import io.github.saimonovski.versechest.old.code.configs.rarity.Rare;
import io.github.saimonovski.versechest.old.code.data.storage.DropChest;
import io.github.saimonovski.versechest.old.code.data.storage.ItemsInChests;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


@Data
public class DataManage {
    private VerseChest plugin = VerseChest.getInstance();
    private ItemStack creatorBlock;
    private List<ItemStack> rareItems;
    private List<ItemStack> epicItems;
    private List<ItemStack> mythicItems;
    private List<ItemStack> legendaryItems;

    private Rare rare;
    private Epic epic;
    private Mythic mythic;
    private Legendary legendary;



    private Material chestMaterial;


    private Config config;
    private MessageConfig messages;
    private io.github.saimonovski.versechest.old.code.data.storage.Data data;
static{
    Instance = new DataManage();
}
public static   DataManage Instance;
   public static YamlDocument create(String path){
       try {
           return YamlDocument.create(new File(VerseChest.getInstance().getDataFolder(),path), VerseChest.getInstance().getResource(path), GeneralSettings.builder().setSerializer(SpigotSerializer.getInstance()).build(), LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
       }catch (IOException exception){
           Bukkit.getLogger().severe("failed initalizing config");
           throw new RuntimeException("failed initalizing config");
       }
   }


    private void loadBlock(){
        creatorBlock = config.getItem();
        if(creatorBlock == null || !creatorBlock.getType().isBlock()) {
            creatorBlock = new ItemStack(Material.BEDROCK);
            ItemMeta meta = creatorBlock.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_PURPLE+"CreatorBlock");
            meta.addEnchant(Enchantment.OXYGEN, 10, true);
            meta.setLore(Collections.singletonList("place in to create a chest"));
            creatorBlock.setItemMeta(meta);
            config.setItem(creatorBlock);
            config.save();
        }
    }
    private void loadChestData() {

        chestMaterial = config.getChest();
        if (!chestMaterial.isBlock() || chestMaterial.isAir()) {
            chestMaterial = Material.CHEST;
        }
            for (DropChest chest : Instance.data.getChestList()) {
                Location location = chest.getChestLocation();

                Block block = location.getWorld().getBlockAt(location);
                if (!block.getType().equals(chestMaterial)) {
                    block.setType(chestMaterial);
                }
            }

        }
    private void loadItems(){
        ItemsInChests it =  data.getItems();
        rareItems = it.getRare();
        epicItems = it.getEpic();
        mythicItems = it.getMythic();
        legendaryItems = it.getLegendary();
    }
    public  void reloadAll(){
        loadAllConfigs();
        loadBlock();
        loadChestData();
        loadItems();
        loadRarity();
    }

    private void loadAllConfigs() {
       config = new Config(create("config.yml"));
       messages = new MessageConfig(create("messages.yml"));
       data = new io.github.saimonovski.versechest.old.code.data.storage.Data();
    }

    public void saveAll(){
        data.save();
    }

    private void loadRarity(){
       rare = config.getRare();
       epic = config.getEpic();
       mythic = config.getMythic();
       legendary = config.getLegendary();
    }

}
