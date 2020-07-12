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

public class FunctionGui {

    public static VexGui FunctionMain(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回主界面
        vexComponentsList.add(new VexButton(4,"返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, MainGui.main(player));
        }));
        vexComponentsList.add(new VexButton(1,"付费功能","[local]button.png","[local]button_.png",-1,80,120,30, p -> {
            VexViewAPI.openGui(player, FunctionGui.FunctionCharge(player));
        }));
        vexComponentsList.add(new VexButton(2,"免费功能","[local]button.png","[local]button_.png",-1,140,120,30,player1 -> {
            VexViewAPI.openGui(player, FunctionGui.FunctionFree(player));
        }));
        //标题
        vexComponentsList.add(new VexText(75,15, Arrays.asList("辅助功能"),3));
        return new VexGui("[local]gui.png",-1,-1,250,230,vexComponentsList);
    }

    public static VexGui FunctionCharge(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回辅助功能主界面
        vexComponentsList.add(new VexButton(4,"返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, FunctionGui.FunctionMain(player));
        }));
        //vexComponentsList.add(new VexImage("[local]inv.png",25,55,90,120));
        vexComponentsList.add(new VexText(30,70,Arrays.asList("飞行功能","可以获得飞行能力10分钟","价格：50金币"),1));
        vexComponentsList.add(new VexButton("buyFly","飞行功能","[local]button.png","[local]button_.png",50,225,80,30));

        //vexComponentsList.add(new VexImage("[local]inv.png",150,55,90,120));
        vexComponentsList.add(new VexText(155,70,Arrays.asList("定点传送","可以指定传送地点","价格：50金币"),1));
        vexComponentsList.add(new VexButton("buyTele","定点传送","[local]button.png","[local]button_.png",175,225,80,30));

        //vexComponentsList.add(new VexImage("[local]inv.png",270,55,90,120));
        vexComponentsList.add(new VexText(275,70,Arrays.asList("属性增强","可以获得一堆铁质礼品","价格：100金币"),1));
        vexComponentsList.add(new VexButton("buyAttr","属性增强","[local]button.png","[local]button_.png",295,225,80,30));
        //标题
        vexComponentsList.add(new VexText(75,15,Arrays.asList("付费功能"),3));
        return new VexGui("[local]gui.png",-1,-1,400,270,vexComponentsList);
    }

    public static VexGui FunctionFree(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回辅助功能主界面
        vexComponentsList.add(new VexButton(4,"返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, FunctionGui.FunctionMain(player));
        }));
        vexComponentsList.add(new VexButton("getLoc","显示坐标","[local]button.png","[local]button_.png",-1,80,120,30,p -> {

        }));
        vexComponentsList.add(new VexButton(2,"我也不知道还有啥功能","[local]button.png","[local]button_.png",-1,140,120,30,player1 -> {

        }));
        //标题
        vexComponentsList.add(new VexText(75,15,Arrays.asList("免费功能"),3));
        return new VexGui("[local]gui.png",-1,-1,250,230,vexComponentsList);
    }
}
