package peace.minecraftserver.EventListener;


import lk.vexview.api.VexViewAPI;
import lk.vexview.tag.TagDirection;
import lk.vexview.tag.components.VexImageTag;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.BlockUtils;
import peace.minecraftserver.utils.LocationUtil;
import peace.minecraftserver.utils.PeaceAreaUtil;
import peace.minecraftserver.utils.VaultUtil;


public class addEventListener implements Listener {
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent event) {
//        //MinecraftServer.plugin.getLogger().info("玩家触发了OnPlayerMove事件");
//        Player player = event.getPlayer();
//        // 得到玩家当前的坐标.
//        if(player.hasPermission("vip.v2")) {
//            Location loc = event.getPlayer().getLocation();
//            // 设置目标坐标为玩家头顶 5 格处.
//            loc.setY(loc.getY() - 1);
//            // 得到目标坐标的方块
//            Block b = loc.getBlock();
//            // 将目标坐标的方块更改为石头 (stone).
//            b.setType(Material.GOLD_BLOCK);
//
//        }
//        if(player.hasPermission("lao")){
//            Location loc = event.getPlayer().getLocation();
//            String direction = LocationUtil.getDirection(loc);
//            Block b1 = null;
//            Block b2 = null;
//            Block b1_xia = null;
//            Block b2_xia = null;
//            Block b = BlockUtils.getShiftBlock(loc,0,0,0);
//            if(direction.equalsIgnoreCase("south") || direction.equalsIgnoreCase("north")){
//                b1 = BlockUtils.getShiftBlock(loc,-1,0,0);
//                b2 = BlockUtils.getShiftBlock(loc,+1,0,0);
//                b1_xia = BlockUtils.getShiftBlock(loc,-1,-1,0);
//                b2_xia = BlockUtils.getShiftBlock(loc,+1,-1,0);
//            }else{
//                b1 = BlockUtils.getShiftBlock(loc,0,0,-1);
//                b2 = BlockUtils.getShiftBlock(loc,0,0,+1);
//                b1_xia = BlockUtils.getShiftBlock(loc,0,-1,-1);
//                b2_xia = BlockUtils.getShiftBlock(loc,0,-1,+1);
//            }
//            if(b1_xia.getType()== Material.AIR) {
//                b1_xia.setType(Material.IRON_BLOCK);
//            }
//            if(b2_xia.getType()== Material.AIR) {
//                b2_xia.setType(Material.IRON_BLOCK);
//            }
//            if(b1.getType()== Material.AIR) {
//                b1.setType(Material.REDSTONE_TORCH_ON);
//            }
//            if(b2.getType()== Material.AIR) {
//                b2.setType(Material.REDSTONE_TORCH_ON);
//            }
//            if (b.getType()!= Material.RAILS || b.getType()!= Material.POWERED_RAIL) {
//                b.setType(Material.POWERED_RAIL);
//            }
//
////            MinecraftServer.plugin.getLogger().info("get face:"+b1.getFace(b1).toString());
////            MinecraftServer.plugin.getLogger().info("get relative:"+b1.getRelative(b1.getFace(b)).toString());
//
//        }
//    }
    //初始化公告
    @EventHandler
    public void PlayerIn(PlayerJoinEvent event){

        Player player = event.getPlayer(); // 当玩家加入游戏
        PeaceAreaUtil.init(player.getLocation());
        PeaceAreaUtil.IsPeaceArea(player.getLocation());

        TagDirection td = new TagDirection(0,180,0,true,false);
        Location location = PeaceAreaUtil.peaceAreaLocation;
        VexImageTag vit2 = new VexImageTag("成就",location.getBlockX(),100,location.getBlockZ(),"[local]gonggao.png",4000,2000,20,20,td);
        VexViewAPI.addWorldTag(player.getWorld(),vit2);

    }



}
