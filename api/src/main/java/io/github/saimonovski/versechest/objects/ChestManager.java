package io.github.saimonovski.versechest.objects;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ChestManager {
    DropChest findChest(int x, int y, int z, String worldName);
    void registerChest(DropChest chest);
    void unregisterChest(DropChest chest);
    void unregisterChest(int x, int y, int z, String worldName);
    void checkMember(CoolDownChestMember member);
    Map<UUID,CoolDownChestMember> members();
    void registerMember(UUID player);
    void unregisterMember(UUID player);
}
