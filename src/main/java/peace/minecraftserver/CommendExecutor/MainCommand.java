package peace.minecraftserver.CommendExecutor;

import lk.vexview.api.VexViewAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.VexView.FunctionGui;
import peace.minecraftserver.VexView.InsureGui;
import peace.minecraftserver.VexView.MainGui;
import peace.minecraftserver.VexView.achievementGui;

public class MainCommand implements CommandExecutor {
    private final MinecraftServer plugin;

    public MainCommand(MinecraftServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        if(commandSender instanceof Player){
            if(command.getName().equalsIgnoreCase("main")){
                Player player = (Player)commandSender;

                //以下为参数
                if(strings.length==0) {
                    //打开主界面
                    VexViewAPI.openGui(player, MainGui.main(player));
                    return true;
                }else if(strings[0].equalsIgnoreCase("achievement")){
                    VexViewAPI.openGui(player, achievementGui.achievement(player));
                    return true;
                }else if(strings[0].equalsIgnoreCase("function")){
                    VexViewAPI.openGui(player, FunctionGui.FunctionMain(player));
                    return true;
                }else if(strings[0].equalsIgnoreCase("insure")){
                    VexViewAPI.openGui(player, InsureGui.InsureMainGui(player));
                    return true;
                }
            }
        }else {
            commandSender.sendMessage("You must be a player!");
            return false;
        }
        return false;
    }
}