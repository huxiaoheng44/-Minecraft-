package peace.minecraftserver.EventListener;

import org.bukkit.Effect;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import peace.minecraftserver.MinecraftServer;

public class AttackEffectEvent implements Listener {
    @EventHandler
    public void attacckeffect(EntityDamageByEntityEvent event){

        if(event.getDamager().getType().equals(EntityType.PLAYER)&&event.getDamager().hasPermission("attackon")){
            MinecraftServer.plugin.getLogger().info("inattackevent");
            Vector vectorAB = event.getEntity().getLocation().clone().subtract(event.getDamager().getLocation()).toVector();
            double vectorLength = vectorAB.length();
            vectorAB.normalize();
            for (double i = 0; i < vectorLength; i += 0.1) {
                Vector vector = vectorAB.clone().multiply(i);
                event.getDamager().getLocation().add(vector);
                event.getDamager().getLocation().getWorld().playEffect(event.getDamager().getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
                event.getDamager().getLocation().subtract(vector);


            }

        }
    }
}
