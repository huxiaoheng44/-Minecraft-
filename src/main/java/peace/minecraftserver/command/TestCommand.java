package peace.minecraftserver.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.VaultUtil;

import java.util.Arrays;
import java.util.logging.Logger;

public class TestCommand implements CommandExecutor {
    private static final Logger log = Logger.getLogger("Minecraft");


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MinecraftServer.plugin.getLogger().info(label+"----"+ Arrays.toString(args));
        if (command.getName().equalsIgnoreCase("getMoney")) {
            if ((sender instanceof Player)) {
                Player p = (Player) sender;
                VaultUtil.give(p.getUniqueId(),300);
                p.sendMessage("当前金钱："+VaultUtil.seemoney(p.getUniqueId()));
            }
            return true;
        }
        return false;
    }
}
