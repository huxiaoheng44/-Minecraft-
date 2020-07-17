package peace.minecraftserver.utils;



import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import peace.minecraftserver.MinecraftServer;

public class MysqlUtils {



    public int getTimeInSeconds(OfflinePlayer p) {
        if (p != null)
            return getSeconds(p) + 3600 * getHours(p) + 60 * getMinutes(p);
        return -1;
    }

    public void setTime(OfflinePlayer p, int seconds, int minutes, int hours) {
        if (MinecraftServer.tm.getTimer(p) == null) {
            Timer timer = new Timer(p);
            timer.setSeconds(seconds + 3600 * hours + 60 * minutes);
            timer.save();
        } else {
            Timer timer = MinecraftServer.tm.getTimer(p);
            timer.setSeconds(seconds + 3600 * hours + 60 * minutes);
            timer.save();
        }
    }

    public String getMessages(String section, String player, int hours, int minutes, int seconds) {
        return MinecraftServer.config.getString(section)
                .replaceAll("%prefix%", MinecraftServer.prefix)
                .replaceAll("&", "§")
                .replaceAll("%player%", player)
                .replaceAll("%hours%", Integer.toString(hours))
                .replaceAll("%minutes%",Integer.toString(minutes))
                .replaceAll("%seconds%", Integer.toString(seconds));
    }

    public boolean playedBefore(String uuid) {
        if (!MinecraftServer.mysql.isConnected()) {
            File file = new File("plugins//PlaytimePlus//players.yml");
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            if (cfg.contains(uuid))
                return true;
            return false;
        }
        if (MinecraftServer.mysql.isUserExist(uuid))
            return true;
        return false;
    }

    public int getSecondsFromDatabase(OfflinePlayer p) {
        if (p != null) {
            if (MinecraftServer.tm.getTimer(p) == null) {
                if (!MinecraftServer.mysql.isConnected())
                    return getSecondsFromConfig(p);
                return MinecraftServer.mysql.getSeconds(p.getUniqueId().toString()).intValue();
            }
            return MinecraftServer.tm.getTimer(p).getSeconds();
        }
        return -1;
    }

    private int getSecondsFromConfig(OfflinePlayer p) {
        if (p != null) {
            if (MinecraftServer.tm.getTimer(p) == null) {
                File file = new File("plugins//PlaytimePlus//players.yml");
                YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                String[] s = cfg.getString(p.getUniqueId().toString()).split(" ");
                return Integer.valueOf(s[1]).intValue();
            }
            return MinecraftServer.tm.getTimer(p).getSeconds();
        }
        return -1;
    }

    public int getSeconds(OfflinePlayer p) {
        if (MinecraftServer.tm.getTimer(p) == null) {
            if (!MinecraftServer.mysql.isConnected())
                return getSecondsFromConfig(p) % 60;
            return MinecraftServer.mysql.getSeconds(p.getUniqueId().toString()).intValue() % 60;
        }
        return MinecraftServer.tm.getTimer(p).getSeconds() % 60;
    }

    public int getMinutes(OfflinePlayer p) {
        if (MinecraftServer.tm.getTimer(p) == null) {
            if (!MinecraftServer.mysql.isConnected())
                return getSecondsFromConfig(p) % 3600 / 60;
            return MinecraftServer.mysql.getSeconds(p.getUniqueId().toString()).intValue() % 3600 / 60;
        }
        return MinecraftServer.tm.getTimer(p).getSeconds() % 3600 / 60;
    }

    public int getHours(OfflinePlayer p) {
        if (MinecraftServer.tm.getTimer(p) == null) {
            if (!MinecraftServer.mysql.isConnected())
                return getSecondsFromConfig(p) / 3600;
            return MinecraftServer.mysql.getSeconds(p.getUniqueId().toString()).intValue() / 3600;
        }
        return MinecraftServer.tm.getTimer(p).getSeconds() / 3600;
    }

    public ItemStack getSkull(OfflinePlayer p, int place) {
        if (p != null) {
            SkullMeta skullMeta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
            skullMeta.setOwner(p.getName());
            skullMeta.setDisplayName("+ place + " + p.getName());
            ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
            itemStack.setItemMeta((ItemMeta)skullMeta);
            return itemStack;
        }
        SkullMeta meta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        meta.setDisplayName("+ place + " );
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        item.setItemMeta((ItemMeta)meta);
        return item;
    }

    public OfflinePlayer[] getTop10() {
        OfflinePlayer[] top10 = new OfflinePlayer[10];
        File file = new File("plugins//PlaytimePlus//players.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection cs = cfg.getConfigurationSection("");
        for (int i = 0; i < 10; i++) {
            if (!MinecraftServer.mysql.isConnected()) {
                for (String uuid : cs.getKeys(false)) {
                    OfflinePlayer p = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
                    if ((top10[i] == null && !containsPlayer(p, top10)) || (getSecondsFromConfig(p) > getSecondsFromConfig(top10[i]) && !containsPlayer(p, top10)))
                        top10[i] = p;
                }
            } else {
                for (String uuid : MinecraftServer.mysql.getUsers()) {
                    OfflinePlayer p = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
                    if ((top10[i] == null && !containsPlayer(p, top10)) || (getSecondsFromDatabase(p) > getSecondsFromDatabase(top10[i]) && !containsPlayer(p, top10)))
                        top10[i] = p;
                }
            }
        }
        return top10;
    }

    private boolean containsPlayer(OfflinePlayer p, OfflinePlayer[] top10) {
        boolean b = false;
        for (int i = 0; i < 10; i++) {
            if (top10[i] != null &&
                    top10[i].equals(p))
                b = true;
        }
        return b;
    }

}
