package io.github.saimonovski.versechest.objects;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Database {
    List<DropChest> getChests();
    List<RarityCategory> getRarities();
    void connect();
    void disconnect();
    Map<UUID,CoolDownChestMember> getChestMembers();
    CoolDownChestManager getManager(int x, int y, int z, String worldName);

    void updateMember(CoolDownChestMember member);
}
