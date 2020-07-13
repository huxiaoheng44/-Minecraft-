package peace.minecraftserver;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.scheduler.BukkitRunnable;
import peace.minecraftserver.command.TestCommand;
import peace.minecraftserver.commands.CommandBase;
import peace.minecraftserver.commands.CommandManager;
import peace.minecraftserver.listener.InventoryListener;
import peace.minecraftserver.listener.PlayerInsureListener;
import peace.minecraftserver.listener.PlayerListener;
import peace.minecraftserver.utils.*;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class MinecraftServer extends JavaPlugin {
    public static JavaPlugin plugin;

    private static final Logger log = Logger.getLogger("Minecraft");
    public static YamlConfiguration config;

    public static Mysql mysql;

    public static MysqlUtils utils;

    public static CommandManager cm;

    public static TimerManager tm;

    public static String prefix;

    private int afkDelay;

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
        // Plugin shutdown logic
        if (mysql.isEnabled()) {
            mysql.disconnect();
        }
        System.out.println("我的第一个插件结束");
        super.onDisable();
    }

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info("_____________");
        getLogger().info("实训MC插件");
        getLogger().info("_____________");
        Bukkit.getPluginManager().registerEvents(new PlayerInsureListener(),this);
        economy_init();
        //mysql_init();
        playtime_init();
        super.onEnable();
    }

    public void mysql_init(){

    }

    public void economy_init(){
        //log.info("economy_init");
        if (!VaultUtil.setEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }else{
            getLogger().info("Vault初始化成功");
        }
        this.getCommand("getMoney").setExecutor(new TestCommand());
    }

    public void playtime_init(){
        getCommand("playtime").setExecutor((CommandExecutor)new CommandBase());
        Bukkit.getServer().getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)new InventoryListener(), (Plugin)this);
        saveDefaultConfig();
        config = (YamlConfiguration)getConfig();
        prefix = config.getString("prefix").replaceAll("&", "§");
        utils = new MysqlUtils();
        cm = new CommandManager();
        tm = new TimerManager();
        mysql = new Mysql();
        if (mysql.isEnabled()) {
            mysql.connect();
            mysql.createTable();
        }
        this.afkDelay = config.getInt("afk-delay");
        createFiles();
        Bukkit.getServer().getConsoleSender().sendMessage("by ForcyCode");
        startScheduler();
        startAutoSave();
    }


    private void startAutoSave() {
        (new BukkitRunnable() {
            public void run() {
                MinecraftServer.tm.saveAll();
            }
        }).runTaskTimer((Plugin)this, 0L, config.getInt("autosave-interval"));
    }

    private void startScheduler() {
        (new BukkitRunnable() {
            public void run() {
                for (Timer timer : MinecraftServer.tm.getTimer()) {
                    if (!timer.isAfk()) {
                        timer.setSeconds(timer.getSeconds() + 1);
                        if (timer.getPlayer().isOnline()) {
                            Player p = (Player)timer.getPlayer();
                            if (p.getLocation() == timer.getPlayerLocation())
                                timer.setAfkTimer(timer.getAfkTimer() + 1);
                            if (timer.getAfkTimer() >= MinecraftServer.this.afkDelay) {
                                timer.setAfk(true);
                                timer.setAfkTimer(0);
                            }
                        }
                    }
                }
            }
        }).runTaskTimer((Plugin)this, 0L, 20L);
    }

    private void createFiles() {
        File folder = new File("plugins//PlaytimePlus");
        File file1 = new File("plugins//PlaytimePlus//players.yml");
        if (!folder.exists())
            folder.mkdirs();
        if (!file1.exists())
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
