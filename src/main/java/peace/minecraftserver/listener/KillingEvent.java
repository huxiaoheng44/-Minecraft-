package peace.minecraftserver.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.VaultUtil;

import javax.sound.sampled.LineListener;

public class KillingEvent implements Listener {
    @EventHandler
    public void killEntityEvent(EntityDeathEvent event){
        MinecraftServer.plugin.getLogger().info(event.getEntity().getName());
        if(event.getEntity().getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
            MinecraftServer.plugin.getLogger().info(damageEvent.getDamager().getName());
            if (damageEvent.getDamager().getType().equals(EntityType.PLAYER) && !damageEvent.getEntity().getType().equals(EntityType.PLAYER)) {
                damageEvent.getDamager().sendMessage("§6击杀了" + damageEvent.getEntity().getName() + "§9获得奖励：10金币  " + "§6现有金币：" + VaultUtil.seemoney(damageEvent.getDamager().getUniqueId()));
                VaultUtil.give(damageEvent.getDamager().getUniqueId(), 10);

            }
        }
    }
}
