package peace.minecraftserver.EventListener;

import com.connorlinfoot.titleapi.TitleAPI;
import lk.vexview.api.VexViewAPI;
import lk.vexview.event.ButtonClickEvent;
import lk.vexview.gui.VexGui;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.permissions.PermissionAttachment;
import peace.minecraftserver.Entity.TeleHorse;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.VexView.InsureGui;
import peace.minecraftserver.utils.TitleApi;
import peace.minecraftserver.utils.VaultUtil;

import java.util.ArrayList;
import java.util.List;

public class FunctionButtonEvent implements Listener {
    @EventHandler
    public void PlayClickButton(ButtonClickEvent event){
        Player player = event.getPlayer();
        String id = (String) event.getButtonID();
        List<String> messages = new ArrayList<String>();
        MinecraftServer.plugin.getLogger().info(id);
        //第一个飞行
        if(id.equalsIgnoreCase("buyFly")){
            VaultUtil.pay(player.getUniqueId(),50);
            PermissionAttachment attachment = player.addAttachment(MinecraftServer.plugin,1200);
            attachment.setPermission("fly",true);
            MinecraftServer.plugin.getLogger().info(player.getName()+"获得飞行权限");
            //TitleAPI.sendTitle();
            player.sendMessage("金币-50");
            player.sendMessage("你已经获取飞行权限");
            //TitleApi.sendTitle(player,2,2,2,"你现在能飞了","");

        }
        //传送功能
        else if(id.equalsIgnoreCase("buyTele")){

        }
        //属性强化
        else if(id.equalsIgnoreCase("buyAttr")){

        }
        //获取坐标
        else if(id.equalsIgnoreCase("getLoc")){
            Location l = player.getLocation();
            player.sendMessage("Your location:\n"+"x:"+l.getBlockX()+"\ny:"+l.getBlockY()+"\nz:"+l.getBlockZ());
        }
        //取消确认
        else if(id.equalsIgnoreCase("cancel")){

        }
        //下面三个是设置地点的
        //选择家里
        else if(id.equalsIgnoreCase("homeLoc")){
            if(player.getBedSpawnLocation() == null){
                player.sendMessage("获取不到家庭住址,可能是你家没有放床！");
                player.sendMessage("已经设置目标地址为默认地址0,0,0");
                //player.setBedSpawnLocation(new Location(player.getWorld(),0,0,0));
                TeleHorse.spawnTelehorse(player,new Location(player.getWorld(),0,0,0));
            }else {
                Location location = player.getBedSpawnLocation();
                TeleHorse.spawnTelehorse(player, location);
            }
            //记得关闭界面
            player.closeInventory();
        }
        //选择其他玩家
        else if(id.equalsIgnoreCase("otherPlayerLoc")){
            VexGui gui = event.getGui();
            List<String> name = gui.getTextArea(4).getTypedText();
            Player target = player.getServer().getPlayer(name.get(0));
            TeleHorse.spawnTelehorse(player,target.getLocation());
            //记得关闭界面
            player.closeInventory();
        }
        //自定义坐标
        else if(id.equalsIgnoreCase("setLoc")){
            VexGui gui = event.getGui();
            List<String> x = gui.getTextArea(1).getTypedText();
            List<String> y = gui.getTextArea(2).getTypedText();
            List<String> z = gui.getTextArea(3).getTypedText();
            Location location = new Location(player.getWorld(),Integer.valueOf(x.get(0)),Integer.valueOf(y.get(0)),Integer.valueOf(z.get(0)));
            TeleHorse.spawnTelehorse(player,location);
            //记得关闭界面
            player.closeInventory();
        }
    }

    @EventHandler void PlayerFly(PlayerMoveEvent event){
        Player player = event.getPlayer();
        //player.sendMessage("fffff");
        if(player.hasPermission("fly")){
            //player.sendMessage("fffff");
            player.setAllowFlight(true);
            player.setFlying(true);
            //TitleAPI.sendTitle(player,2,2,2,"你现在能飞了","");
            //player.sendMessage("你现在能飞了");
        }else {
            player.setAllowFlight(false);
            player.setFlying(false);
        }
    }
}
