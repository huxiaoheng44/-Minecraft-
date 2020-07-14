package peace.minecraftserver.VexView;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexComponents;
import lk.vexview.gui.components.VexText;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import peace.minecraftserver.MinecraftServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessGui {

    public static VexGui BusinessMainGui(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回主界面
        vexComponentsList.add(new VexButton("back","返回","[local]button.png","[local]button_.png",10,10,23,13, player1 -> {
            VexViewAPI.openGui(player, MainGui.main(player));
        }));
        vexComponentsList.add(new VexButton("shop","§2商城","[local]button.png","[local]button_.png",-1,60,120,30,player1 -> {
            //直接打开商城
            shopGui.PlayershopGui(player);
        }));
        vexComponentsList.add(new VexButton("lottery","§3抽奖","[local]button.png","[local]button_.png",-1,110,120,30,player1 -> {
            //直接抽奖
            lotteryGui.PlayerLotteryGui(player);
        }));

        vexComponentsList.add(new VexButton("shopPermission","§7摆摊","[local]button.png","[local]button_.png",-1,160,120,30,player1 -> {
            //获取摆摊机会
            PermissionAttachment attachment = player.addAttachment(MinecraftServer.plugin);
            attachment.setPermission("setShopChest",true);
            player.sendMessage("§7你已经获得了摆摊的资格");
        }));
        vexComponentsList.add(new VexText(95,15, Arrays.asList("§5交易"),3));
        return new VexGui("[local]gui.png",-1,-1,250,230,vexComponentsList);
    }
}
