package peace.minecraftserver.EventListener;

import com.google.common.collect.Lists;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class RoundEffectEvent implements Listener {
    @EventHandler
    public void environment_damage(EntityDamageEvent event){
        if(event.getEntity().hasPermission("round")) {
            if (event.getEntity().getType().equals(EntityType.PLAYER) && event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                Location playerLocation = event.getEntity().getLocation();
                List<Location> locations = Lists.newArrayList();
                for (int i = 0; i < 360; i += 72) {
                    // 转弧度制
                    double radians = Math.toRadians(i);
                    locations.add(playerLocation.clone().add(3 * Math.cos(radians), 0D, 3 * Math.sin(radians)));
                }

                buildLine(locations.get(0), locations.get(2));
                buildLine(locations.get(0), locations.get(3));
                buildLine(locations.get(1), locations.get(3));
                buildLine(locations.get(1), locations.get(4));
                buildLine(locations.get(2), locations.get(4));
            }
        }
    }
    public static void buildLine(Location locA, Location locB) {
        Vector vectorAB = locB.clone().subtract(locA).toVector();
        double vectorLength = vectorAB.length();
        vectorAB.normalize();
        for (double i = 0; i < vectorLength; i += 0.1) {
            Vector vector = vectorAB.clone().multiply(i);
            locA.add(vector);
            locA.getWorld().playEffect(locA, Effect.ENDER_SIGNAL, 1);
            locA.subtract(vector);
        }
    }
}
