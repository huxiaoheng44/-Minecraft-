package peace.minecraftserver.EventListener;

import lk.vexview.event.ButtonClickEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class FunctionButtonEvent implements Listener {
    @EventHandler
    public void PlayClickButton(ButtonClickEvent event){
        Player player = event.getPlayer();
        String id = (String) event.getButtonID();
        List<String> messages = new ArrayList<String>();
        //第一个飞行
        if(id.equalsIgnoreCase("buyFly")){

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
    }
}
