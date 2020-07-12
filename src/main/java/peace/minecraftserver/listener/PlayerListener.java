package peace.minecraftserver.listener;

import java.io.File;
import java.io.IOException;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.MLogger;
import peace.minecraftserver.utils.Mysql;
import peace.minecraftserver.utils.MysqlUtils;
import peace.minecraftserver.utils.Timer;

public class PlayerListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        File file = new File("plugins//PlaytimePlus//players.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (!MinecraftServer.utils.playedBefore(p.getUniqueId().toString())) {
            MLogger.info("name: "+String.valueOf(p.getName()) + " " + 0);
            cfg.set(p.getUniqueId().toString(), String.valueOf(p.getName()) + " " + 0);
            try {
                cfg.save(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (MinecraftServer.tm.getTimer((OfflinePlayer)p) == null) {
            Timer timer = new Timer((OfflinePlayer)p);
            MinecraftServer.tm.registerTimer(timer);
        }
        //签到
        int conday = MinecraftServer.mysql.checkin((OfflinePlayer)p);

        //if (p.getName().equals("Nipqo"))
            //p.sendMessage(String.valueOf(MinecraftServer.prefix) + " diesem Server ist PlaytimePlus installiert! ");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (MinecraftServer.tm.getTimer((OfflinePlayer)p) != null) {
            Timer timer = MinecraftServer.tm.getTimer((OfflinePlayer)p);
            timer.save();
            MinecraftServer.tm.unregister(timer);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (MinecraftServer.tm.getTimer((OfflinePlayer)p) != null) {
            Timer timer = MinecraftServer.tm.getTimer((OfflinePlayer)p);
            if (timer.isAfk())
                timer.setAfk(false);
        }
    }
}
