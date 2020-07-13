package peace.minecraftserver.Entity;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TeleHorse {
    private Entity entity;
    private Location location;
    private int times;
    // UUID-location
    private static HashMap<UUID, Location> locationMap = new HashMap<UUID, Location>();
    private static HashMap<UUID, Integer> teleTimesMap = new HashMap<UUID, Integer>();
    //entityUUID - PlayerUUID
    private static HashMap<UUID, UUID> playerMap = new HashMap<UUID, UUID>();

    public static void spawnTelehorse(Player player,Location location){
        World world = player.getWorld();
        Location l = player.getLocation();
        Entity entity = world.spawnEntity(l.add(l.getDirection()).add(0,1,0), EntityType.HORSE);
        TeleHorse teleHorse = new TeleHorse(player,entity,location);
        entity.setCustomName("TeleHorse");
    }

    public TeleHorse(Player player, Entity entity, Location location) {
        this.entity = entity;
        this.location = location;
        this.times = 2;
        locationMap.put(entity.getUniqueId(),location);
        teleTimesMap.put(entity.getUniqueId(),times);
        playerMap.put(entity.getUniqueId(),player.getUniqueId());
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int time) {
        this.times = time;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static Location getTargetLoaction(UUID id){
        Location location = locationMap.get(id);
        return location;
    }

    public static void setLocationMap(UUID id, Location location){
        locationMap.replace(id,location);
    }

    public static int getLeftTimes(UUID id){
        return teleTimesMap.get(id);
    }

    public static int SubtractLeftTimes(UUID id){
        if(teleTimesMap.get(id)!=null) {
            int ti = teleTimesMap.get(id);
            ti--;
            teleTimesMap.replace(id, ti);
            return ti;
        }else{
            return -1;
        }
    }

    public static UUID getPlayer(UUID id){
        return playerMap.get(id);
    }

    public static void removeEntity(UUID id){
        playerMap.remove(id);
        locationMap.remove(id);
        teleTimesMap.remove(id);
    }


}
