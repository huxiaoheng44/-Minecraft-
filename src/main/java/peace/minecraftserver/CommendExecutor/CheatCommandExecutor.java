package peace.minecraftserver.CommendExecutor;

import com.connorlinfoot.titleapi.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.permissions.PermissionAttachment;
import peace.minecraftserver.Entity.TeleHorse;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.PeaceAreaUtil;

import java.util.ArrayList;
import java.util.List;


public class CheatCommandExecutor implements CommandExecutor {
    private final MinecraftServer plugin;

    public CheatCommandExecutor(MinecraftServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if(command.getName().equalsIgnoreCase("cheat")){
                for(int i=0;i<strings.length;i++){
                    if(strings[i].equalsIgnoreCase("fly")){
                        //plugin.getLogger().info("一个玩家会飞了");
                        commandSender.sendMessage("你现在能飞了");
                        ((Player) commandSender).setAllowFlight(true);
                        ((Player) commandSender).setFlying(true);
                    }else if(strings[i].equalsIgnoreCase("recover")){
                        if (commandSender.hasPermission("vip.v1")) {
                            commandSender.sendMessage("钻石之路启动");
                            //Bukkit.getPluginManager().registerEvents(new addEventListener(), MinecraftServer.plugin);
                        }
                    }else if(strings[i].equalsIgnoreCase("money")){
                        PermissionAttachment attachment = commandSender.addAttachment(MinecraftServer.plugin);
                        if(!commandSender.hasPermission("vip.v1")){
                            attachment.setPermission("vip.v1",true);
                        }else if(!commandSender.hasPermission("vip.v2")){
                            attachment.setPermission("vip.v2",true);
                        }else if(!commandSender.hasPermission("vip.v3")){
                            attachment.setPermission("vip.v3",true);
                        }
                        Player player = (Player)commandSender;

                    }else if(strings[i].equalsIgnoreCase("bag")){

                        Inventory inv1 = Bukkit.createInventory(null, InventoryType.CHEST, "§2 !seesaw!");
                        ItemStack itemStack = new ItemStack(Material.APPLE,13);
                        ItemStack MineCart = new ItemStack(Material.MINECART,3);
                        ItemStack rail = new ItemStack(Material.BED,20);
                        String itemname = "JinPingGuo";
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName(itemname);
                        itemStack.setItemMeta(itemMeta);
                        List<String> lores = new ArrayList<String>();
                        //添加物品的具体信息
                        lores.add("+5攻击力");  //第一行lore
                        lores.add("abc");  //这条lore在123之后
                        ItemStack myItem = new ItemStack(Material.DIAMOND_SWORD);  //你的物品
                        itemMeta.setLore(lores); //add the lores of course
                        itemStack.setItemMeta(itemMeta); //give the item the new itemmeta
                        inv1.addItem(itemStack);
                        inv1.addItem(MineCart);
                        inv1.addItem(rail);
                        inv1.getHolder();
                        ((Player) commandSender).closeInventory();
                        ((Player) commandSender).openInventory(inv1);

                    }else if(strings[i].equalsIgnoreCase("lao")){
                        PermissionAttachment attachment = commandSender.addAttachment(MinecraftServer.plugin);
                        attachment.setPermission("lao",true);
                    }else if(strings[i].equalsIgnoreCase("effect")){
                        //注册监听
                        //Bukkit.getPluginManager().registerEvents(new EffectEvent(),MinecraftServer.plugin);
                    }else if(strings[i].equalsIgnoreCase("title")){
                        //title test
                        TitleAPI.sendTitle((Player)commandSender,10,40,10,"sendTitle","fi");
                    }else if(strings[i].equalsIgnoreCase("tele")){
                        Location l = ((Player) commandSender).getLocation();
                        World world = ((Player) commandSender).getWorld();
                        l = l.add(0,3,-1);
                        MaterialData materialData = new MaterialData(Material.STONE);
                        FallingBlock b = world.spawnFallingBlock(l,new MaterialData(Material.GOLD_BLOCK));
                        b.setDropItem(false);
                        world.spawnEntity(l.add(2,0,1), EntityType.SNOWMAN);
                        Entity entity = world.spawnEntity(l.add(l.getDirection()), EntityType.HORSE);
                        TeleHorse teleHorse = new TeleHorse((Player) commandSender,entity,world.getSpawnLocation());
                        entity.setCustomName("TeleHorse");
                        //Entity entity1 = world.spawnEntity(l.add(2,0,1), EntityType.POLAR_BEAR);
                        //commandSender.sendMessage("UUID"+entity.getUniqueId());
                        //commandSender.sendMessage("chaxun Loca"+TeleHorse.getTargetLoaction(entity.getUniqueId()));

                        entity.setCustomNameVisible(true);

                    }else if(strings[i].equalsIgnoreCase("op")){
                        PermissionAttachment attachment = commandSender.addAttachment(MinecraftServer.plugin);
                        attachment.setPermission("op",true);
                        commandSender.sendMessage("你是钻石管理员");
                    }else if(strings[i].equalsIgnoreCase("deop")){
                        PermissionAttachment attachment = commandSender.addAttachment(MinecraftServer.plugin);
                        attachment.setPermission("op",false);
                        commandSender.sendMessage("你取消了钻石管理员");
                    }else if(strings[i].equalsIgnoreCase("chestShop")){
                        PermissionAttachment attachment = commandSender.addAttachment(MinecraftServer.plugin);
                        attachment.setPermission("setShopChest",true);
                        commandSender.sendMessage("你获得了开店权限");
                    }


                }
                return true;
            }
        }else {
            if(strings[0].equalsIgnoreCase("setArea") ){
                PeaceAreaUtil.setArea();
                commandSender.sendMessage("已经设置边界");
            }else {
                commandSender.sendMessage("You must be a player!");
                return false;
            }
        }
        return false;
    }
}
