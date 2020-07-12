package peace.minecraftserver.commands;


import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.MysqlUtils;

public class CommandBase implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmd, String label, String[] args) {
        String cmd2;
        Player p = (Player) cs;
        MysqlUtils utils = MinecraftServer.utils;
        if (args.length == 0) {
            cmd2 = "";
        } else {
            cmd2 = args[0];
        }
        if (cmd.getName().equalsIgnoreCase("playtime")) {
            for (Command command : MinecraftServer.cm.getCommands()) {
                if (command.getCommand().equals("null") || command.getCommand().equalsIgnoreCase(cmd2)) {
                    if (args.length == command.getLength1() || args.length == command.getLength2()) {
                        if (command.getPermission().equals("null") || p.hasPermission(command.getPermission())) {
                            command.setArgs(args);
                            command.setPlayer(p);
                            command.onCommand();
                            break;
                        }
                        p.sendMessage(utils.getMessages("message-no-permission", "", 0, 0, 0));
                        break;
                    }
                    p.sendMessage(utils.getMessages("message-unknown-command", "", 0, 0, 0));
                    break;
                }
            }
        }
        return false;
    }
}
