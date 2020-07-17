package peace.minecraftserver.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;
import java.util.logging.Logger;



public class VaultUtil {
    private static final Logger log = Logger.getLogger("Minecraft");

    public static Economy economy=null;
    public static boolean setEconomy(){
        //log.info("test1");
        RegisteredServiceProvider<Economy> economyProvide = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        //log.info("test2");
        if(economyProvide!=null){
            economy=economyProvide.getProvider();
        }
        return (economy!=null);
    }

    public static boolean give(UUID PlayerUUid, double price){
        //log.info("uuid"+PlayerUUid.toString());
        OfflinePlayer offplayer= Bukkit.getOfflinePlayer(PlayerUUid);
        //log.info("player姓名"+offplayer.getName());
        return economy.depositPlayer(offplayer,price).transactionSuccess();
    }

    public static double seemoney(UUID PlayerUUid){
        OfflinePlayer offplayer=Bukkit.getOfflinePlayer(PlayerUUid);
        return economy.getBalance(offplayer);
    }

    public static boolean pay(UUID PlayerUUid,double price){
        OfflinePlayer offplayer=Bukkit.getOfflinePlayer(PlayerUUid);
        return economy.has(offplayer,price)&&economy.withdrawPlayer(offplayer,price).transactionSuccess();
    }

    public static void goPay(Player player,double price) {
        if (VaultUtil.pay(player.getUniqueId(), price) == true) {
            player.getWorld().playEffect(player.getLocation(), Effect.ANVIL_LAND, 1);
            //加声音
            player.sendMessage("您本次消费" + price);
            player.sendMessage("您的余额还有：" + VaultUtil.seemoney(player.getUniqueId()));
        } else {
            player.sendMessage(("余额不足"));
        }
    }
}
