package io.github.saimonovski.versechest.entity;

import io.github.saimonovski.versechest.config.RarityConfig;

import java.util.Random;

public record Rarity(RarityConfig conf) {
    private final int minItems =
    private final boolean isInvalidRange = minItems == maxItems || minItems > maxItems || maxItems < 0 || minItems <0
    public int itemAmount(){
        if(isInvalidRange) return maxItems;
        Random random = new Random();
        return random.nextInt(minItems,maxItems);
    }
}
