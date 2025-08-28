package io.github.saimonovski.versechest.config;

public class RarityConfig {
    private final MainConfig mainConfig;
    private final int minItems, maxItems, maxItemsInChest, minItemsInChest;

    public RarityConfig(MainConfig mainConfig, int minItems, int maxItems, int maxItemsInChest, int minItemsInChest) {
        this.mainConfig = mainConfig;

        this.minItems = minItems;
        this.maxItems = maxItems;
        this.maxItemsInChest = maxItemsInChest;
        this.minItemsInChest = minItemsInChest;
    }
}
