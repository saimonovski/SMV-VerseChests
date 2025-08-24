package io.github.saimonovski.versechest.messages.replacers;

import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Replacer {
    public static TextReplacementConfig replacePlayer(Player player){
        return TextReplacementConfig.builder()
                .match("%player%")
                .replacement(player.getName())
                .build();
    }
    public static TextReplacementConfig replacePlayer(String player){
        return TextReplacementConfig.builder()
                .match("%player%")
                .replacement(player)
                .build();
    }
    public static TextReplacementConfig replaceAmount(double amount){
        return TextReplacementConfig.builder()
                .match("%amount%")
                .replacement(amount+"")
                .build();
    }
    public static TextReplacementConfig replaceItemName(ItemStack itemStack){
        return TextReplacementConfig.builder()
                .match("%item-name%")
                .replacement(itemStack.displayName())
                .build();
    }
}
