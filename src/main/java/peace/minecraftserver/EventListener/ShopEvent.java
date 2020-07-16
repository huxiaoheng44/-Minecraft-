package peace.minecraftserver.EventListener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.Entity.ShopChest;
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

            if(event.getRawSlot()<=14){
                event.setCancelled(true);
//<<<<<<< HEAD
//                if(VaultUtil.pay(player.getUniqueId(),pri[event.getRawSlot()])){
//                    ItemStack itemStack = new ItemStack(mark[event.getRawSlot()]);
//                    player.getInventory().addItem(itemStack);
//                    //加声音
//                    player.sendMessage("您本次消费"+pri[event.getRawSlot()]);
//                    player.sendMessage("您的余额还有："+VaultUtil.seemoney(player.getUniqueId()));
//                }else{
//                    player.sendMessage(("余额不足"));
//                }
//            }else{
//                event.setCancelled(true);
//            }
//        }else if(title.equalsIgnoreCase("地摊")){
//            HumanEntity whoClicked = event.getWhoClicked();
//            Player player = (Player)whoClicked;
//            Location location = player.getLocation().subtract(3,3,3);
//            Location t_location = location.clone();
//            //遍历查找拥有者
//            Player owner = null;
//            for(int x=0;x<5;x++){
//                for(int y=0;y<5;y++){
//                    for(int z=0;z<5;z++){
//                        t_location = location.clone().add(x,y,z);
//                        if(t_location.getBlock().getType() == Material.CHEST){
//                            owner = ShopChest.getOwner(t_location);
//                        }
//                    }
//                }
//            }
//            if(event.getRawSlot()<=8){
//                event.setCancelled(true);
//                if(VaultUtil.pay(player.getUniqueId(),pri[event.getRawSlot()])){
//                    ItemStack itemStack = new ItemStack(mark[event.getRawSlot()]);
//                    player.getInventory().addItem(itemStack);
//                    //加声音
//                    player.sendMessage("您本次消费"+pri[event.getRawSlot()]);
//=======
                if(VaultUtil.pay(player.getUniqueId(), ShopItem.getPrice(material))==true){
                    player.getInventory().addItem(itemStack12);
                    player.sendMessage("您本次消费"+ShopItem.getPrice(material));
//>>>>>>> 1c97a6cc4d1899af72dc0bb2aedc9ba92b10f415
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
