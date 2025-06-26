package io.github.saimonovski.versechests.object;

import java.util.UUID;

public class User {
    private final UUID uuid;
    private int chestsOpened;

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public synchronized void increaseCount(){
    this.chestsOpened = this.chestsOpened+1;
}

    public UUID getId() {
        return this.uuid;
    }
}
