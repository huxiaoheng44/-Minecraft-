package peace.minecraftserver.utils;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import peace.minecraftserver.MinecraftServer;

import java.io.File;
import java.io.IOException;

public class Timer {
    private OfflinePlayer player;

    private Location playerLocation;

    private int seconds;

    private int afkTimer;

    private boolean afk;

    public Timer(OfflinePlayer p) {
        this.player = p;
        if (p.isOnline()) {
            this.playerLocation = ((Player)p).getLocation();
        } else {
            this.playerLocation = null;
        }
        this.seconds = MinecraftServer.utils.getSecondsFromDatabase(p);
        this.afkTimer = 0;
        this.afk = false;
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public void setPlayer(OfflinePlayer player) {
        this.player = player;
    }

    public Location getPlayerLocation() {
        return this.playerLocation;
    }

    public void setPlayerLocation(Location playerLocation) {
        this.playerLocation = playerLocation;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getAfkTimer() {
        return this.afkTimer;
    }

    public void setAfkTimer(int afkTimer) {
        this.afkTimer = afkTimer;
    }

    public boolean isAfk() {
        return this.afk;
    }

    public void setAfk(boolean afk) {
        this.afk = afk;
    }

    public void save() {
        File file = new File("plugins//PlaytimePlus//players.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(this.player.getUniqueId().toString(), String.valueOf(this.player.getName()) + " " + this.seconds);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (MinecraftServer.mysql.isConnected())
            MinecraftServer.mysql.setTime(this.player, this.seconds);
    }
}
