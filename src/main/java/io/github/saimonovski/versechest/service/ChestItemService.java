package io.github.saimonovski.versechest.service;

import io.github.saimonovski.versechest.entity.ChestItem;
import io.github.saimonovski.versechest.entity.Rarity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ChestItemService {
    private final Map<Rarity, List<ChestItem>> rarityChestItemMap = new TreeMap<>();

}
