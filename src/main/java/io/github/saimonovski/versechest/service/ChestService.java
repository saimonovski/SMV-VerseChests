package io.github.saimonovski.versechest.service;

import io.github.saimonovski.versechest.entity.Chest;
import org.bukkit.Location;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ChestService {
    private final Map<Location, Chest> locationChestMap = new HashMap<>();

    @Nullable
    public Chest getChestFromLocation(Location location){
        return this.locationChestMap.get(location);
    }
}
