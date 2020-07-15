package peace.minecraftserver.CommendExecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.VaultUtil;

public class FollowCommandExecutor implements CommandExecutor {
    private final MinecraftServer plugin;

    public FollowCommandExecutor(MinecraftServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            PermissionAttachment attachment = sender.addAttachment(MinecraftServer.plugin);
            if(command.getName().equalsIgnoreCase("follow") && !sender.hasPermission("op")){
                if(args.length==0) {
                    if (VaultUtil.seemoney(((Player) sender).getUniqueId()) > 20000) {
                        sender.sendMessage("跟随特效开启");
                        attachment.setPermission("follow", true);
                    } else {
                        sender.sendMessage("你没有权限，需要金币大于20000");
                    }
                } else if(args[0].equalsIgnoreCase("cancel")){
                    sender.sendMessage("跟随特效关闭");
                    attachment.setPermission("follow",false);
                }
            }

        }
        return false;
    }
}
