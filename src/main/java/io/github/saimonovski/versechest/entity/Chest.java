package io.github.saimonovski.versechest.entity;

import io.github.saimonovski.versechest.config.MainConfig;
import org.bukkit.Location;

import java.util.List;

public record Chest(Location chestLocation, MainConfig config) {
   public void updateBlockMaterial(){
       chestLocation.getBlock().setType(config.getBlockCreator().getMaterial());
   }

}
