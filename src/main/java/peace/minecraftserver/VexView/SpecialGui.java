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

public class SpecialGui {

    public static VexGui specialgui(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回主界面
        vexComponentsList.add(new VexButton("back","返回","[local]button.png","[local]button_.png",10,10,23,13, player1 -> {
            VexViewAPI.openGui(player, MainGui.main(player));
        }));
        vexComponentsList.add(new VexButton("special1","魔影颗粒","[local]button.png","[local]button_.png",-1,60,120,30,player1 -> {
            //开启魔影
        }));
        vexComponentsList.add(new VexButton("special2","火焰颗粒","[local]button.png","[local]button_.png",-1,110,120,30,player1 -> {
            //开启火焰
        }));

        vexComponentsList.add(new VexButton("special3","药水视觉","[local]button.png","[local]button_.png",-1,160,120,30,player1 -> {
            //开启药水
        }));

        vexComponentsList.add(new VexButton("cancel1","取消","[local]button.png","[local]button_.png",200,65,20,20,player1 -> {
            //取消魔影
        }));

        vexComponentsList.add(new VexButton("cancel2","取消","[local]button.png","[local]button_.png",200,115,20,20,player1 -> {
            //取消火焰
        }));

        vexComponentsList.add(new VexButton("cancel3","取消","[local]button.png","[local]button_.png",200,165,20,20,player1 -> {
            //取消药水
        }));

        vexComponentsList.add(new VexText(75,15, Arrays.asList("特效管理"),3));
        return new VexGui("[local]gui.png",-1,-1,250,230,vexComponentsList);
    }
}
