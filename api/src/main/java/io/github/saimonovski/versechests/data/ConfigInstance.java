package io.github.saimonovski.versechests.data;

import java.time.Duration;

public interface ConfigInstance {
    Duration chestCooldownTime();

    String getFormattedTime(long seconds, long minutes, long hours, long days);
}
