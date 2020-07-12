package peace.minecraftserver.commands;

import peace.minecraftserver.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class CommandHelp extends Command {
    public CommandHelp() {
        super("help", "playtime.help", 1, 1);
    }

    public void onCommand() {
        List list = new ArrayList<String>();
        if (!MinecraftServer.config.getString("message-playtime").isEmpty())
            list.addAll(MinecraftServer.config.getList("message-help"));
        for (int i = 0; i < list.size(); i++)
            getPlayer().sendMessage(list.get(i).toString().replaceAll("%prefix%", MinecraftServer.prefix).replaceAll("&", "§"));
    }
}
