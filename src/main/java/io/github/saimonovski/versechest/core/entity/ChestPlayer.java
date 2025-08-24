package io.github.saimonovski.versechest.core.entity;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

public class ChestPlayer {
    UUID playerUUID;
    int chestOpenedCount;
    Map<Chest, Duration> chestCooldowns;
}
