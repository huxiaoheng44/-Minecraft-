/*    */ package peace.minecraftserver.commands;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CommandManager
/*    */ {
/*  8 */   private List<Command> commands = new ArrayList<>();
/*    */   
/*    */   public CommandManager() {
/* 11 */     addCommand(new CommandTop10());
/* 12 */     addCommand(new CommandHelp());
/* 13 */     addCommand(new CommandSet());
/* 14 */     addCommand(new CommandPlaytime());
/*    */   }
/*    */   
/*    */   public void addCommand(Command cmd) {
/* 18 */     if (!this.commands.contains(cmd)) {
/* 19 */       this.commands.add(cmd);
/*    */     }
/*    */   }
/*    */   
/*    */   public List<Command> getCommands() {
/* 24 */     return this.commands;
/*    */   }
/*    */ }
