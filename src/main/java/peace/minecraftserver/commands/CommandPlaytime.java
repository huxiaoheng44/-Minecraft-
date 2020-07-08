package peace.minecraftserver.commands;



import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.MysqlUtils;

public class CommandPlaytime extends Command {
    public CommandPlaytime() {
        super("null", "null", 0, 1);
    }

    public void onCommand() {
        MysqlUtils utils = MinecraftServer.utils;
        if ((getArgs()).length == 0) {
            if (getPlayer().hasPermission("playtime.use")) {
                List list = new ArrayList();
                if (!MinecraftServer.config.getString("message-playtime").isEmpty())
                    list.addAll(MinecraftServer.config.getList("message-playtime"));
                for (int i = 0; i < list.size(); i++)
                    getPlayer().sendMessage(list.get(i).toString()
                            .replaceAll("%prefix%", MinecraftServer.prefix)
                            .replaceAll("&", "§")
                            .replaceAll("%seconds%", Integer.toString(utils.getSeconds((OfflinePlayer)getPlayer())))
                            .replaceAll("%minutes%", Integer.toString(utils.getMinutes((OfflinePlayer)getPlayer())))
                            .replaceAll("%hours%", Integer.toString(utils.getHours((OfflinePlayer)getPlayer())))
                            .replaceAll("%player%", getPlayer().getName()));
            } else {
                getPlayer().sendMessage(utils.getMessages("message-no-permission", "", 0, 0, 0));
            }
        } else if ((getArgs()).length == 1) {
            if (getPlayer().hasPermission("playtine.use.other")) {
                OfflinePlayer p = Bukkit.getOfflinePlayer(getArgs()[0]);
                if (utils.playedBefore(p.getUniqueId().toString())) {
                    List list = new ArrayList();
                    if (!MinecraftServer.config.getString("message-playtime-other").isEmpty())
                        list.addAll(MinecraftServer.config.getList("message-playtime-other"));
                    for (int i = 0; i < list.size(); i++)
                        getPlayer().sendMessage(list.get(i).toString()
                                .replaceAll("%prefix%", MinecraftServer.prefix)
                                .replaceAll("&", "§")
                                .replaceAll("%seconds%", Integer.toString(utils.getSeconds(p)))
                                .replaceAll("%minutes%", Integer.toString(utils.getMinutes(p)))
                                .replaceAll("%hours%", Integer.toString(utils.getHours(p)))
                                .replaceAll("%player%", p.getName()));
                } else {
                    getPlayer().sendMessage(utils.getMessages("message-player-has-never-played", p.getName(), 0, 0, 0));
                }
            } else {
                getPlayer().sendMessage(utils.getMessages("message-no-permission", "", 0, 0, 0));
            }
        }
    }
}
