package io.github.saimonovski.versechest.old.code.configs.rarity;

import lombok.Setter;

@Setter
public class Legendary  implements RarityConfigurer {

    private int chance;

    private int maxItems;

    private int minItems;

    @Override
    public int getChance() {
        return chance;
    }

    @Override
    public int getMaxItems() {
        return maxItems;
    }

    @Override
    public int getMinItems() {
        return minItems;
    }
}
