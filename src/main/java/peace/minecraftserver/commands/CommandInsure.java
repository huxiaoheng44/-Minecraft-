package peace.minecraftserver.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.InsureUtils;

public class CommandInsure implements CommandExecutor {
    String []insures={"wood","stone","iron","gold","diamond","monster_kill","accident_death"};
    InsureUtils insureUtils=new InsureUtils();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MinecraftServer.plugin.getLogger().info(label + "======");
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("insure")) {
            sender.sendMessage("========保险列表========");
            for (int i = 0; i < insures.length; i++) {
                if (insureUtils.is_insur_out(player, insures[i])==1) {
                    sender.sendMessage("§1"+insures[i] + "§1保险已到期");
                } else if(insureUtils.is_insur_out(player,insures[i])==0) {

                    int seconds = MinecraftServer.mysql.getSeconds(player.getUniqueId().toString());
                    int value = MinecraftServer.mysql.getGuarantee(player, insures[i]) - seconds;
                    sender.sendMessage(insures[i] + "保险已购买   剩余时间：" + value/60+"分钟");
                }else {
                    sender.sendMessage(insures[i]+"保险未购买");
                }
            }
        }
            return false;

    }
}
