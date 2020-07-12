/*    */ package peace.minecraftserver.commands;
/*    */
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.MysqlUtils;

/*    */
/*    */ public class CommandTop10
/*    */   extends Command
/*    */ {
/*    */   public CommandTop10() {
/* 14 */     super("top10", "playtime.top10", 1, 1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onCommand() {
/* 20 */     MysqlUtils utils = MinecraftServer.utils;
/* 21 */     ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
/* 22 */     Inventory inv = Bukkit.createInventory(null, 27, "§6§lTop10");
/* 23 */     inv.setItem(0, utils.getSkull(utils.getTop10()[0], 1));
/* 24 */     inv.setItem(1, item);
/* 25 */     inv.setItem(2, utils.getSkull(utils.getTop10()[1], 2));
/* 26 */     inv.setItem(3, item);
/* 27 */     inv.setItem(4, utils.getSkull(utils.getTop10()[2], 3));
/* 28 */     inv.setItem(5, item);
/* 29 */     inv.setItem(6, utils.getSkull(utils.getTop10()[3], 4));
/* 30 */     inv.setItem(7, item);
/* 31 */     inv.setItem(8, utils.getSkull(utils.getTop10()[4], 5));
/* 32 */     for (int i = 9; i < 18; i++) {
/* 33 */       inv.setItem(i, item);
/*    */     }
/* 35 */     inv.setItem(18, utils.getSkull(utils.getTop10()[5], 6));
/* 36 */     inv.setItem(19, item);
/* 37 */     inv.setItem(20, utils.getSkull(utils.getTop10()[6], 7));
/* 38 */     inv.setItem(21, item);
/* 39 */     inv.setItem(22, utils.getSkull(utils.getTop10()[7], 8));
/* 40 */     inv.setItem(23, item);
/* 41 */     inv.setItem(24, utils.getSkull(utils.getTop10()[8], 9));
/* 42 */     inv.setItem(25, item);
/* 43 */     inv.setItem(26, utils.getSkull(utils.getTop10()[9], 10));
/*    */     
/* 45 */     getPlayer().openInventory(inv);
/*    */   }
/*    */ }


/* Location:              /Users/wzh/Downloads/PlaytimePlus.jar!/de/forcycode/playtimeplus/commands/CommandTop10.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */