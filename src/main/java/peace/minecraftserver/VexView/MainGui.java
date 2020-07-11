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

public class MainGui {
    public static VexGui main(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        vexComponentsList.add(new VexButton(1,"商店","[local]button.png","[local]button_.png",-1,80,120,30, p -> {

        }));
        vexComponentsList.add(new VexButton(2,"保险系统","[local]button.png","[local]button_.png",-1,140,120,30,player1 -> {
            //直接打开保险信息界面
            VexViewAPI.openGui(player, InsureGui.InsureMainGui(player));
        }));
        vexComponentsList.add(new VexButton(3,"辅助功能","[local]button.png","[local]button_.png",-1,200,120,30,player1 -> {
            //直接打开辅助功能信息界面
            VexViewAPI.openGui(player, FunctionGui.FunctionMain(player));
        }));
        //标题
        vexComponentsList.add(new VexText(70,15, Arrays.asList("平安组插件"),3));
        return new VexGui("[local]gui.png",-1,-1,250,350,vexComponentsList);
    }
}
