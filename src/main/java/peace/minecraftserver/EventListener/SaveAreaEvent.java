package peace.minecraftserver.EventListener;


import lk.vexview.api.VexViewAPI;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.VexView.InsureGui;
import peace.minecraftserver.utils.PeaceAreaUtil;
import peace.minecraftserver.utils.TitleApi;
import static org.bukkit.entity.EntityType.ITEM_FRAME;


public class SaveAreaEvent implements Listener {
    @EventHandler
    public void SaveAreaInteract(PlayerInteractEvent event) {
        //这部分好像暂时不需要
//        Player player = event.getPlayer();
//        if(PeaceAreaUtil.IsPeaceArea(player.getLocation()) && !player.hasPermission("op")) {
//            player.sendMessage(String.valueOf(event.getClickedBlock().getType()));
//            if(event.getClickedBlock().getType().equals(ITEM_FRAME)){
//                VexViewAPI.openGui(player, MyGUI.AffirmLogue(player,1));
//                TitleApi.sendTitle(player,4,5,4,"成功打开GUI","第一个界面");
//            }
//        }
    }
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
    public void EntityDamage(EntityDamageByEntityEvent event){



        //如果点击到的是放商品的ITEM_FRAME
        if(event.getDamager().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) event.getDamager();
            if (event.getEntity().getType().equals(ITEM_FRAME)) {
                event.setCancelled(true);
                ItemFrame frame = (ItemFrame) event.getEntity();
                ItemStack itemStack = frame.getItem();
                String type = itemStack.getType().toString();
                VexViewAPI.openGui(player, InsureGui.AffirmLogue(player, type));
                TitleApi.sendTitle(player, 4, 5, 4, "成功打开GUI", "第一个界面");
            } else if (PeaceAreaUtil.IsPeaceArea(event.getDamager().getLocation())) {
                event.getDamager().sendMessage("你不能伤害其他其他人");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(PeaceAreaUtil.IsPeaceArea(player.getLocation()) && !player.hasPermission("op")) {
            //player.sendMessage("-------------BlockPlaceEvent--------------");
            player.sendMessage("你不能在这里建造东西");
            event.setCancelled(true);
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
