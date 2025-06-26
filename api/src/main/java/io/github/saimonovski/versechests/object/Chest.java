package io.github.saimonovski.versechests.object;

import io.github.saimonovski.versechests.data.ConfigInstance;
import org.bukkit.Location;


import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public abstract class Chest {
   private final Location location;
    private final Map<UUID, Instant> durationMap = new HashMap<>();

    protected Chest(Location location) {
        this.location = location;
    }
    public Chest(){
        this.location = null;
    }



   public abstract void chestOpen(User user);
   public void setUserOnCooldown(UUID user, ConfigInstance configInstance){
       Instant instant = Instant.now().plus(configInstance.chestCooldownTime());
       this.durationMap.put(user,instant);
   }


   public boolean isUserOnCooldown(UUID user){
       Instant durationInstance = this.durationMap.get(user);
       if(durationInstance == null) return false;
       Instant now = Instant.now();
      return now.isBefore(durationInstance);
   }

   public void removeExpiredCooldown(){
       List<UUID> playersToRemove = this.durationMap.keySet().stream().filter(user -> !isUserOnCooldown(user)).collect(Collectors.toList());
        playersToRemove.forEach(this.durationMap::remove);
   }
   public String getFormattedEstimatedTime(UUID user){
       Instant instant = this.durationMap.get(user);
       Duration duration  = Duration.between(Instant.now(), instant);
       long milis = duration.toMillis();

       long hours = TimeUnit.MILLISECONDS.toHours(milis);
       long minutes =
               TimeUnit.MILLISECONDS.toMinutes(milis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milis));
       long seconds =
               TimeUnit.MILLISECONDS.toSeconds(milis) - (TimeUnit.HOURS.toSeconds(TimeUnit.MILLISECONDS.toHours(milis)) + TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milis)));
       StringBuilder formattedString = new StringBuilder();
       if(hours >  0) formattedString.append(hours).append(" h ");
       if(minutes >  0) formattedString.append(minutes).append(" min ");
       if(seconds >  0) formattedString.append(seconds).append(" s ");

       return formattedString.toString();

   }
   public void setUserToCooldown(User user, Duration duration){
       Instant instant = Instant.now().plus(duration);
       this.durationMap.put(user.getId(),instant);
   }



}
