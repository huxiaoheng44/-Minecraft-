package peace.minecraftserver.EventListener;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class EffectEvent implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // 得到玩家当前的坐标.
        if(player.hasPermission("vip.v3")) {
            Location location = player.getLocation();
            location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES,1);
            //location.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 1);
        }
    }



    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.FISHING_ROD) {
            // 在给定坐标中生成一道闪电. 在本例中, 这个坐标是玩家准星瞄准的地方.
            // 只能指向200格以内的坐标.
            player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
        }
    }
}
