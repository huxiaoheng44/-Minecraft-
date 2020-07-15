package peace.minecraftserver.EventListener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.VexView.lotteryGui;
import peace.minecraftserver.VexView.shopGui;
import peace.minecraftserver.utils.VaultUtil;

public class LotteryEvent implements Listener {

    public int markl[]={383,278,276,279,322,352,297,50,16};
    @EventHandler
    public void LotteryGuiClick(InventoryClickEvent event){
        Inventory inventory =event.getInventory();
        String title = inventory.getTitle();
        if(title.equalsIgnoreCase(lotteryGui.PlayerlottryGui)){
            HumanEntity whoClicked = event.getWhoClicked();
            Player player = (Player)whoClicked;
            if(event.getRawSlot()==49){
                event.setCancelled(true);
                if(VaultUtil.pay(player.getUniqueId(),500)==true){
                    double a=Math.random();
                    player.sendMessage("aaaaa"+a);
                    int lucky;
                    lucky = (int)(a*10000);
                    player.sendMessage("lucky"+lucky);
                    ItemStack itemStack = new ItemStack(markl[lucky%10]);
                    player.getInventory().addItem(itemStack);
                    //加声音
                    player.sendMessage("您本次消费500");
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
