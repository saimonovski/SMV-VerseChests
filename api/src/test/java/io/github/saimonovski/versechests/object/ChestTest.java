package io.github.saimonovski.versechests.object;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.UUID;

class ChestTest {
    private Chest chest;

    @BeforeEach
    void setUp() {
        chest = new Chest() {
            @Override
            public void chestOpen(User user) {
                Duration duration = Duration.ofMinutes(3).plusSeconds(35);
                setUserToCooldown(user,duration);
            }
        };
    }

    @Test
    void getFormattedEstimatedTime() {
        User user1 = new User(UUID.randomUUID());
        User user2 = new User(UUID.randomUUID());
        User user3 = new User(UUID.randomUUID());
        this.chest.chestOpen(user1);
        this.chest.chestOpen(user2);
        this.chest.chestOpen(user3);

        System.out.println("User o id: "+user1.getId() + "\n pozostaly cooldown: "+chest.getFormattedEstimatedTime(user1.getId()) + "\n gracz na cooldownie: "+chest.isUserOnCooldown(user1.getId()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User o id: "+user2.getId() + "\n pozostaly cooldown: "+chest.getFormattedEstimatedTime(user2.getId()) + "\n gracz na cooldownie: "+chest.isUserOnCooldown(user2.getId()));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User o id: "+user3.getId() + "\n pozostaly cooldown: "+chest.getFormattedEstimatedTime(user3.getId()) + "\n gracz na cooldownie: "+chest.isUserOnCooldown(user3.getId()));

    }
}