package peace.minecraftserver.listener;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getRawSlot() > -1 && e.getView().getTitle().equals("§6§ltop10"))
            e.setCancelled(true);
    }
}

