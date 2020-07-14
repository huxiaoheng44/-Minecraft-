package peace.minecraftserver.VexView;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexComponents;
import lk.vexview.gui.components.VexText;
import org.bukkit.entity.Player;

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
        vexComponentsList.add(new VexButton("shop","商城","[local]button.png","[local]button_.png",-1,80,120,30,player1 -> {
            //直接打开商城
            shopGui.PlayershopGui(player);
        }));
        vexComponentsList.add(new VexButton("lottery","抽奖","[local]button.png","[local]button_.png",-1,140,120,30,player1 -> {
            //直接抽奖
            lotteryGui.PlayerLotteryGui(player);
        }));
        vexComponentsList.add(new VexText(65,15, Arrays.asList("§5交易"),3));
        return new VexGui("[local]gui.png",-1,-1,250,230,vexComponentsList);
    }
}
