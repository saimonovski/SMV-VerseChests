package io.github.saimonovski.versechest.objects;

import java.time.Duration;
import java.util.UUID;

public interface CoolDownChestMember {
    UUID uuid();
    Duration remainingTime();
}
