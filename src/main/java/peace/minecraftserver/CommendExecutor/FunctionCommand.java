package peace.minecraftserver.CommendExecutor;

import lk.vexview.api.VexViewAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.VexView.FunctionGui;

public class FunctionCommand implements CommandExecutor {
    private final MinecraftServer plugin;

    public FunctionCommand(MinecraftServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        if(commandSender instanceof Player){
            if(command.getName().equalsIgnoreCase("function")){
                Player player = (Player)commandSender;

                //以下为参数
                if(strings.length==0) {
                    //打开function主界面
                    VexViewAPI.openGui(player, FunctionGui.FunctionMain(player));
                    return true;
                }else if(strings[0].equalsIgnoreCase("free")){
                    //免费界面
                    VexViewAPI.openGui(player, FunctionGui.FunctionFree(player));
                    return true;
                }else if(strings[0].equalsIgnoreCase("pay")){
                    //付费界面
                    VexViewAPI.openGui(player, FunctionGui.FunctionCharge(player));
                    return true;
                }
//                else if(strings[0].equalsIgnoreCase("remove")){
//                    //打开商店购物界面
//                    return true;
//                }
            }
        }else {
            commandSender.sendMessage("You must be a player!");
            return false;
        }
        return false;
    }
}
