package peace.minecraftserver.EventListener;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FollowEffectEvent implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // 得到玩家当前的坐标.
        if(player.hasPermission("follow")) {
            Location location = player.getLocation();

            location.getWorld().playEffect(location, Effect.POTION_BREAK, 1);
        }
    }
}
