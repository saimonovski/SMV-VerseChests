package io.github.saimonovski.versechest.entity;

import org.bukkit.Location;

import java.util.List;

public record Chest(Location chestLocation, int chestId, List<ChestItem> chestItems) {
}
