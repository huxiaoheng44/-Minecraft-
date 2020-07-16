package peace.minecraftserver.EventListener;


import lk.vexview.api.VexViewAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.Entity.ShopChest;
import peace.minecraftserver.Entity.ShopItem;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.VexView.InsureGui;
import peace.minecraftserver.VexView.shopGui;
import peace.minecraftserver.utils.PeaceAreaUtil;
import peace.minecraftserver.utils.TitleApi;
import peace.minecraftserver.utils.VaultUtil;

import static org.bukkit.entity.EntityType.HORSE;
import static org.bukkit.entity.EntityType.ITEM_FRAME;


public class SaveAreaEvent implements Listener {

    @EventHandler
    public void SaveAreaInteractEntity(PlayerInteractEntityEvent event) {
//        Player player = event.getPlayer();
//        player.sendMessage("----------------PlayerInteractEntityEvent-----------------");
//        // 得到玩家当前的坐标.
//        Location location =  player.getLocation();
//        Location spawnLocation = player.getWorld().getSpawnLocation();
//        if(location.getX()-spawnLocation.getX()<=30 && location.getZ()-spawnLocation.getZ()<=30 && !event.getPlayer().hasPermission("op")){
//            Entity block = event.getRightClicked();
//            player.sendMessage("你点击了Entity"+block.getType());
//        }

    }

    @EventHandler
    public void DamageEvent(BlockDamageEvent event){
        Player player = event.getPlayer();
        if(PeaceAreaUtil.IsPeaceArea(player.getLocation()) && !player.hasPermission("op")) {
            event.getPlayer().sendMessage("你不能搞破坏"+event.getBlock().getType());
            event.setCancelled(true);
        }
        //event.setCancelled(true);
    }

    @EventHandler
    public void EntityDamage(EntityDamageByEntityEvent event) {

        //如果点击到的是放商品的ITEM_FRAME

        if (PeaceAreaUtil.IsPeaceArea(event.getDamager().getLocation())) {
            try {
                if (event.getEntity().getType().equals(ITEM_FRAME) && event.getDamager().getType().equals(EntityType.PLAYER)) {
                    event.setCancelled(true);
                    ItemFrame frame = (ItemFrame) event.getEntity();
                    ItemStack itemStack = frame.getItem();
                    Player player = (Player) event.getDamager();
                    //frame.setItem();
                    player.sendMessage("§6查看商品");
                    VexViewAPI.openGui(player, InsureGui.AffirmLogue(player, itemStack.getType(),frame));
                   // player.sendMessage("§6查看商品");
                    //TitleApi.sendTitle(player, 4, 5, 4, "成功打开GUI", "第一个界面");
                } else if (PeaceAreaUtil.IsPeaceArea(event.getDamager().getLocation())) {
                    event.getDamager().sendMessage("你不能伤害其他其他人");
                    event.setCancelled(true);
                }
            }catch (Exception e){

            }

        }
    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(PeaceAreaUtil.IsPeaceArea(player.getLocation()) && !player.hasPermission("op")) {
            //player.sendMessage("-------------BlockPlaceEvent--------------");
            //如果该玩家有开店的权限的话可以放置箱子
            if(player.hasPermission("setShopChest") && event.getBlockPlaced().getType()==Material.CHEST){
                event.setBuild(true);
                ShopChest.setShopChest(event.getBlockPlaced().getLocation(),player);
            }
            else {
                //player.sendMessage("getBlockAgainst" + event.getBlockAgainst());
                player.sendMessage("你不能在这里建造东西");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void InteractBlock(PlayerInteractEvent event){
        Player player = event.getPlayer();
        try {
            Block block = event.getClickedBlock();
            //如果是箱子则可以卖东西
            if(block.getType()== Material.CHEST){
                //player.sendMessage("你点击了shop");
                //如果该玩家是主人的话
                if(ShopChest.isOwner(block.getLocation(),player)){
                    player.sendMessage("你是店主,可以随意操作");
                }else{
                    player.sendMessage("你是买家");
                    Inventory inventory = Bukkit.createInventory(null, 54, "地摊");
                    Chest chest = (Chest)block;
                    Inventory chest_inventory = chest.getInventory();
                    inventory.addItem(chest_inventory.getContents());
                    //这里写具体怎么买东西

                }
            }
        }catch (Exception e){

        }
    }

//    @EventHandler
//    public void EntityDeath(EntityDeathEvent event){
//        MyPlugin.plugin.getLogger().info("-------------BlockBreakEvent--------------"+event.getEntity());
//    }
//    @EventHandler
//    public void EntityDrop(EntityExplodeEvent event){
//        MyPlugin.plugin.getLogger().info("-------------EntityExplodeEvent--------------"+event.getEntity());
//    }
//
//    @EventHandler
//    public void PlayerItemBreakEvent(PlayerItemBreakEvent event){
//        MyPlugin.plugin.getLogger().info("-------------PlayerItemBreakEvent--------------");
//    }
//
//    @EventHandler
//    public void PlayerDropItemEvent(PlayerDropItemEvent event){
//        MyPlugin.plugin.getLogger().info("-------------PlayerDropItemEvent--------------");
//    }

//    @EventHandler
//    public void EntitySpawn(EntitySpawnEvent event){
//        //Entity entity = event.getEntity();
//        HandlerList handlerList = event.getHandlers();
//        MyPlugin.plugin.getLogger().info(handlerList.toString());
//        if(event.getEntity().getType().equals(DROPPED_ITEM)){
//            MyPlugin.plugin.getLogger().info("-------------你不能伤害它1--------------");
//            //event.setCancelled(true);
//        }
//        if(event.getEntity().getType().toString().equalsIgnoreCase(String.valueOf(Material.ITEM_FRAME))){
//            MyPlugin.plugin.getLogger().info("-------------你不能伤害它2--------------");
//        }
//        if(event.getEntity().getType().equals(ITEM_FRAME)){
//            MyPlugin.plugin.getLogger().info("-------------你不能伤害它3--------------");
//        }
//        MyPlugin.plugin.getLogger().info("-------------EntitySpawnEvent--------------"+event.getEntity().getType());
//    }
//    @EventHandler
//    public void BlockBuildEvent(BlockCanBuildEvent event) {
//        Block b  = event.getBlock();
//        // 得到玩家当前的坐标.
//        Location location =  b.getLocation();
//        Location spawnLocation = b.getWorld().getSpawnLocation();
//        if(location.getX()-spawnLocation.getX()<=20 && location.getZ()-spawnLocation.getZ()<=20){
//            event.setBuildable(false);
//        }
//
//    }

//    @EventHandler
//    public void AreaBlockBreakEvent(BlockBreakEvent event) {
//        Block b  = event.getBlock();
//        // 得到玩家当前的坐标.
//        Location location =  b.getLocation();
//
//        Location spawnLocation = b.getWorld().getSpawnLocation();
//        if(location.getX()-spawnLocation.getX()<=30 && location.getZ()-spawnLocation.getZ()<=30 && !event.getPlayer().hasPermission("op")){
//            event.getPlayer().sendMessage("在该区域不能破坏物品");
//            event.setCancelled(true);
//        }
//
//    }

}
