package peace.minecraftserver.CommendExecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import peace.minecraftserver.MinecraftServer;

public class InsureCommand implements CommandExecutor {
    private final MinecraftServer plugin;

    public InsureCommand(MinecraftServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {
        if(commandSender instanceof Player){
            if(command.getName().equalsIgnoreCase("insure")){
                Player player = (Player)commandSender;

                //以下为参数
                if(strings.length==0) {
                    //打开insure主界面
                    //VexViewAPI.openGui(player,);
                    return true;
                }else if(strings[0].equalsIgnoreCase("auction")){
                    //打开商店拍卖界面
                    return true;
                }else if(strings[0].equalsIgnoreCase("lottery")){
                    //打开商店抽奖界面
                    return true;
                }else if(strings[0].equalsIgnoreCase("remove")){
                    //打开商店购物界面
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
