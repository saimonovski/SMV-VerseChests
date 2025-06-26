package io.github.saimonovski.versechests.object;

import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ItemInChest {
    private final UUID id = UUID.randomUUID();
    private final ItemStack itemStack;

    public ItemInChest(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
