package peace.minecraftserver.EventListener;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.VexView.lotteryGui;
import peace.minecraftserver.VexView.shopGui;
import peace.minecraftserver.utils.VaultUtil;

public class ShopEvent implements Listener {
    public int pri[]={10,30,100,50,6,8,1,2,100};
    public int mark[]={265,266,264,346,366,364,20,50,261};
    @EventHandler
    public void ShopGuiClick(InventoryClickEvent event){
        Inventory inventory =event.getInventory();
        String title = inventory.getTitle();
        if(title.equalsIgnoreCase(shopGui.PlayershopGui)){
            HumanEntity whoClicked = event.getWhoClicked();
            Player player = (Player)whoClicked;
            if(event.getRawSlot()==49){
                event.setCancelled(true);
                lotteryGui.PlayerLotteryGui(player);//显示抽奖界面
            }else if(event.getRawSlot()<=8){
                event.setCancelled(true);
                if(VaultUtil.pay(player.getUniqueId(),pri[event.getRawSlot()])==true){
                    ItemStack itemStack = new ItemStack(mark[event.getRawSlot()]);
                    player.getInventory().addItem(itemStack);
                    //加声音
                    player.sendMessage("您本次消费"+pri[event.getRawSlot()]);
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
