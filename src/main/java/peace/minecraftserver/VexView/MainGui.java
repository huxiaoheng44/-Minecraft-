package peace.minecraftserver.VexView;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexComponents;
import lk.vexview.gui.components.VexText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import peace.minecraftserver.EventListener.SaveAreaEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainGui {
    public static VexGui main(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();

        vexComponentsList.add(new VexButton("shopmain","商店","[local]button.png","[local]button_.png",-1,40,80,20, p -> {
            shopGui.PlayershopGui(player);
        }));
        vexComponentsList.add(new VexButton("insuremain","保险系统","[local]button.png","[local]button_.png",-1,80,80,20,player1 -> {
            //直接打开保险信息界面
            VexViewAPI.openGui(player, InsureGui.InsureMainGui(player));
        }));
        vexComponentsList.add(new VexButton("functionmain","辅助功能","[local]button.png","[local]button_.png",-1,120,80,20,player1 -> {
            //直接打开辅助功能信息界面
            VexViewAPI.openGui(player, FunctionGui.FunctionMain(player));
        }));
        vexComponentsList.add(new VexButton("achievemain","成就系统","[local]button.png","[local]button_.png",-1,160,80,20,player1 -> {
            //直接打开辅助功能信息界面
            VexViewAPI.openGui(player, achievementGui.achievement(player));

        }));

        //标题
        vexComponentsList.add(new VexText(70,15, Arrays.asList("平安组插件"),2));
        return new VexGui("[local]gui.png",-1,-1,250,240,vexComponentsList);
    }
}
