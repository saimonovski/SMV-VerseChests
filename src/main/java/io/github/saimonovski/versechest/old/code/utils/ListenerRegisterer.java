package io.github.saimonovski.versechest.old.code.utils;

import io.github.saimonovski.versechest.core.VerseChest;
import io.github.saimonovski.versechest.old.code.cmd.ItemCreateCMD;
import io.github.saimonovski.versechest.old.code.listeners.ListenerInitalizer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.stream.Stream;

public class ListenerRegisterer {
  @Getter
  private static final Stream<Listener> listenerStream = Stream.of(
            new ItemCreateCMD(),
            new ListenerInitalizer()
    );

    public static void initalize(){
        listenerStream.forEach(value -> Bukkit.getPluginManager().registerEvents(value, VerseChest.getInstance()));
    }
}
