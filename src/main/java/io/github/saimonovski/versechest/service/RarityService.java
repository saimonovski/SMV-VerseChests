package io.github.saimonovski.versechest.service;

import io.github.saimonovski.versechest.entity.Rarity;

import java.util.*;
import java.util.stream.Collectors;

public class RarityService {
    private final Set<Rarity> rarities = new TreeSet<>();
    private final Map<String,Rarity> stringRarityMap;
    public RarityService(Map<String, Rarity> stringRarityMap) {
        this.stringRarityMap = stringRarityMap;
        this.chestItemService = new ChestItemService();
    }
    private final ChestItemService chestItemService;
    public void registerRarity(Rarity rarity){
         this.rarities.add(rarity);
     }
     public Map<Rarity,Integer> getRarityValues(){
        return rarities.stream().collect(Collectors.toMap(rarity -> rarity, Rarity::itemAmount));
     }




}
