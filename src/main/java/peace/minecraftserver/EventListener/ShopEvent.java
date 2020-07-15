package peace.minecraftserver.EventListener;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.Entity.ShopItem;
import peace.minecraftserver.VexView.lotteryGui;
import peace.minecraftserver.VexView.shopGui;
import peace.minecraftserver.utils.VaultUtil;

public class ShopEvent implements Listener {
    @EventHandler
    public void ShopGuiClick(InventoryClickEvent event){
        Inventory inventory =event.getInventory();
        String title = inventory.getTitle();
        if(title.equalsIgnoreCase(shopGui.PlayershopGui)){
            HumanEntity whoClicked = event.getWhoClicked();
            Player player = (Player)whoClicked;

            ItemStack itemStack12 = inventory.getItem(event.getRawSlot());
            Material material= itemStack12.getType();

            if(event.getRawSlot()<=8){
                event.setCancelled(true);
                if(VaultUtil.pay(player.getUniqueId(), ShopItem.getPrice(material))==true){
                    player.getInventory().addItem(itemStack12);
                    player.sendMessage("您本次消费"+ShopItem.getPrice(material));
                    player.sendMessage("您的余额还有："+VaultUtil.seemoney(player.getUniqueId()));
                }else{
                    player.sendMessage(("余额不足"));
                }
            }else{
                event.setCancelled(true);
            }
        }

    }

}
