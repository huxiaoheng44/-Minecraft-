/*    */ package peace.minecraftserver.commands;
/*    */
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.MysqlUtils;

/*    */
/*    */ public class CommandSet
/*    */   extends Command
/*    */ {
/*    */   public CommandSet() {
/* 12 */     super("set", "playtime.settime", 5, 5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onCommand() {
/* 19 */     MysqlUtils utils = MinecraftServer.utils;
/* 20 */     OfflinePlayer p = Bukkit.getOfflinePlayer(getArgs()[1]);
/* 21 */     if (utils.playedBefore(p.getUniqueId().toString())) {
/* 22 */       utils.setTime(p, Integer.valueOf(getArgs()[4]).intValue(), Integer.valueOf(getArgs()[3]).intValue(), Integer.valueOf(getArgs()[2]).intValue());
/* 23 */       getPlayer().sendMessage(utils.getMessages("message-set-playtime", p.getName(), utils.getHours(p), utils.getMinutes(p), utils.getSeconds(p)));
/*    */     } else {
/* 25 */       getPlayer().sendMessage(utils.getMessages("message-player-has-never-played", p.getName(), 0, 0, 0));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/wzh/Downloads/PlaytimePlus.jar!/de/forcycode/playtimeplus/commands/CommandSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */