package peace.minecraftserver;

import lk.vexview.api.VexViewAPI;
import lk.vexview.tag.TagDirection;
import lk.vexview.tag.components.VexImageTag;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.scheduler.BukkitRunnable;
import peace.minecraftserver.CommendExecutor.*;

import peace.minecraftserver.Entity.ShopItem;

import peace.minecraftserver.EventListener.*;
import peace.minecraftserver.command.TestCommand;
import peace.minecraftserver.commands.CommandBase;
import peace.minecraftserver.commands.CommandInsure;
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
        //初始化一些东西，具体函数在下面
        init();

        getLogger().info("___________________");
        getLogger().info("平安组Minecraft插件");
        getLogger().info("___________________");
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
        Bukkit.getServer().getPluginManager().registerEvents((Listener)new ShopEvent(), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)new LotteryEvent(), (Plugin)this);
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
        startSchedulerForToday();
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

    private void startSchedulerForToday() {
        (new BukkitRunnable() {
            public void run() {
                for (Timer timer : MinecraftServer.tm.getTimer()) {
//                    if(timer.getLastdayseconds()>60*60 && timer.getLastdayseconds()<60*62){
//                        ((Player)timer.getPlayer()).sendMessage("游玩时间超过一小时");
//                    }
                    //((Player)timer.getPlayer()).sendMessage("test-定时器被调用，时间为"+Integer.toString(timer.getLastdayseconds()));
                    if((timer.getLastdayseconds()>60*60 && timer.getLastdayseconds()<60*60+2*60)||
                            (timer.getLastdayseconds()>2*60*60 && timer.getLastdayseconds()<2*60*60+2*60)||
                            (timer.getLastdayseconds()>3*60*60 && timer.getLastdayseconds()<3*60*60+2*60)||
                            (timer.getLastdayseconds()>4*60*60 && timer.getLastdayseconds()<4*60*60+2*60)||
                            (timer.getLastdayseconds()>5*60*60 && timer.getLastdayseconds()<5*60*60+2*60)){
                        ((Player)timer.getPlayer()).sendMessage("游玩时间已经超过"+ MinecraftServer.utils.getHours(timer.getPlayer())+"小时了，建议合理安排时间");
                        ((Player)timer.getPlayer()).sendMessage("健康游戏忠告：\n" +
                                "抵制不良游戏，拒绝盗版游戏。\n" +
                                "注意自我保护，谨防受骗上当。\n" +
                                "适度游戏益脑，沉迷游戏伤身。\n" +
                                "合理安排时间，享受健康生活。\n");
                    }else if(timer.getLastdayseconds()>6*60*60){
                        ((Player)timer.getPlayer()).sendMessage("游玩时间已经六小时了，建议合理安排时间");
                        ((Player)timer.getPlayer()).kickPlayer("游玩时间超过六小时，请明天再来玩吧");
                    }
                }
            }
        }).runTaskTimer((Plugin)this, 0L, 20L*60);
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

    //在enable之前的一些启动项
    private void init(){
        plugin = this;
        //玩家加入监听器
        Bukkit.getPluginManager().registerEvents(new addEventListener(),this);
        //粒子特效监听器
        Bukkit.getPluginManager().registerEvents(new EffectEvent(),this);
        //坐骑监听器
        Bukkit.getPluginManager().registerEvents(new TeleHorseListener(),this);
        //GUI监听器
        Bukkit.getPluginManager().registerEvents(new VexViewListener(),this);
        //安全区域监听器
        Bukkit.getPluginManager().registerEvents(new SaveAreaEvent(),this);
        //保险模块监听
        Bukkit.getPluginManager().registerEvents(new PlayerInsureListener(),this);
        //注册FunctionGUI监听器
        Bukkit.getPluginManager().registerEvents(new FunctionButtonEvent(),this);
        //攻击特效监听器
        Bukkit.getPluginManager().registerEvents(new AttackEffectEvent(),this);
        //落地特效监听器
        Bukkit.getPluginManager().registerEvents(new RoundEffectEvent(),this);
        //跟随特效
        Bukkit.getPluginManager().registerEvents(new FollowEffectEvent(),this);


        this.getCommand("money").setExecutor(new EconomyCommandExecutor(this));
        this.getCommand("show").setExecutor(new ShowCommandExecutor(this));
        this.getCommand("Cheat").setExecutor(new CheatCommandExecutor(this));

        this.getCommand("attack").setExecutor(new AttackCommandExecutor(this));
        this.getCommand("round").setExecutor(new RoundCommandExecutor(this));
        this.getCommand("follow").setExecutor(new FollowCommandExecutor(this));
        this.getCommand("insureshow").setExecutor(new CommandInsure());


        this.getCommand("main").setExecutor(new MainCommand(this));
        this.getCommand("function").setExecutor(new FunctionCommand(this));
        this.getCommand("insure").setExecutor(new InsureCommand(this));
        this.getCommand("shop").setExecutor(new ShopCommand(this));
        this.getCommand("achievement").setExecutor(new AchievementCommand(this));

        ShopItem.init();
        //初始化安全区域
        //PeaceAreaUtil.setArea();




        //getLogger().info("----------第一个plugin启动------------");
//        if (getServer().getPluginManager().getPlugin("Vault") == null) {
//            getLogger().info("----------没有发现Vault，插件无法继续使用！------------");
//            //禁用插件
//            getServer().getPluginManager().disablePlugin(this);
//        }
//        //vaultUtil used for economy
//        if(VaultUtil.setEconomy()){
//            plugin.getLogger().info("--------------vaultutil启动成功------------------");
//        }else{
//            plugin.getLogger().info("----------------vaultutil启动失败--------------");
//        }
//        //vexGUI
//        if(Bukkit.getPluginManager().isPluginEnabled("VexView")){
//            plugin.getLogger().info("----------成功启动VexView---------");
//        }else{
//            plugin.getLogger().info("----------VexView启动失败----------");
//        }
    }
}
