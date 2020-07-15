 package peace.minecraftserver.commands;

 import org.bukkit.entity.Player;

 public class Command
 {
   private String command;
   private String permission;
   private String[] args;
   private Player player;
   private int length1;
   private int length2;

   public Command(String command, String permission, int length1, int lenght2) {
     this.command = command;
     this.permission = permission;
     this.length1 = length1;
     this.length2 = lenght2;
   }

   public String getCommand() {
/* 22 */     return this.command;
/*    */   }

   public void setCommand(String command) {
/* 26 */     this.command = command;
/*    */   }

   public String getPermission() {
/* 30 */     return this.permission;
/*    */   }

   public void setPermission(String permission) {
/* 34 */     this.permission = permission;
/*    */   }

   public int getLength1() {
/* 38 */     return this.length1;
/*    */   }

   public void setLength1(int length) {
/* 42 */     this.length1 = length;
/*    */   }

   public int getLength2() {
/* 46 */     return this.length2;
/*    */   }

   public void setLength2(int length) {
/* 50 */     this.length2 = length;
/*    */   }

   public String[] getArgs() {
/* 54 */     return this.args;
/*    */   }

   public void setArgs(String[] args) {
/* 58 */     this.args = args;
/*    */   }

   public Player getPlayer() {
/* 62 */     return this.player;
/*    */   }

   public void setPlayer(Player player) {
/* 66 */     this.player = player;
/*    */   }

   public void onCommand() {}
 }


/* Location:              /Users/wzh/Downloads/PlaytimePlus.jar!/de/forcycode/playtimeplus/commands/Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */