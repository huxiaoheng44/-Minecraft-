package peace.minecraftserver.VexView;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexComponents;
import lk.vexview.gui.components.VexText;
import lk.vexview.gui.components.VexTextArea;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionGui {

    public static VexGui FunctionMain(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回主界面
        vexComponentsList.add(new VexButton("back","返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, MainGui.main(player));
        }));
        vexComponentsList.add(new VexButton("payfunction","付费功能","[local]button.png","[local]button_.png",-1,80,120,30, p -> {
            VexViewAPI.openGui(player, FunctionGui.FunctionCharge(player));
        }));
        vexComponentsList.add(new VexButton("freefunction","免费功能","[local]button.png","[local]button_.png",-1,140,120,30,player1 -> {
            VexViewAPI.openGui(player, FunctionGui.FunctionFree(player));
        }));
        //标题
        vexComponentsList.add(new VexText(75,15, Arrays.asList("辅助功能"),3));
        return new VexGui("[local]gui.png",-1,-1,250,230,vexComponentsList);
    }

    public static VexGui FunctionCharge(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回辅助功能主界面
        vexComponentsList.add(new VexButton("back","返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, FunctionGui.FunctionMain(player));
        }));
        //vexComponentsList.add(new VexImage("[local]inv.png",25,55,90,120));
        vexComponentsList.add(new VexText(45,70,Arrays.asList("飞行功能","可以获得飞行能力10分钟","价格：50金币"),1));
        vexComponentsList.add(new VexButton("buyFly","飞行功能","[local]button.png","[local]button_.png",45,200,80,30));

        //vexComponentsList.add(new VexImage("[local]inv.png",150,55,90,120));
        vexComponentsList.add(new VexText(170,70,Arrays.asList("定点传送","可以指定传送地点","价格：20金币"),1));
        vexComponentsList.add(new VexButton("buyTele","定点传送","[local]button.png","[local]button_.png",170,200,80,30,player1 -> {
            VexViewAPI.openGui(player,SetLocation(player));
        }));

        //vexComponentsList.add(new VexImage("[local]inv.png",270,55,90,120));
        vexComponentsList.add(new VexText(290,70,Arrays.asList("属性增强","可以增加5点攻击力","价格：50金币"),1));
        vexComponentsList.add(new VexButton("buyAttr","属性增强","[local]button.png","[local]button_.png",290,200,80,30));
        //标题
        vexComponentsList.add(new VexText(140,15,Arrays.asList("付费功能"),3));
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
        vexComponentsList.add(new VexButton("recover","自动回血","[local]button.png","[local]button_.png",-1,140,120,30,player1 -> {

        }));
        //标题
        vexComponentsList.add(new VexText(75,15,Arrays.asList("免费功能"),3));
        return new VexGui("[local]gui.png",-1,-1,250,230,vexComponentsList);
    }

    public static VexGui SetLocation(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回辅助功能主界面
        vexComponentsList.add(new VexButton(5,"返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, FunctionGui.FunctionCharge(player));
        }));

        vexComponentsList.add(new VexButton("homeLoc","老家","[local]button.png","[local]button_.png",-1,40,40,20));
        vexComponentsList.add(new VexButton("otherPlayerLoc","其他玩家","[local]button.png","[local]button_.png",-1,80,40,20));
        vexComponentsList.add(new VexText(30,120,Arrays.asList("请输入玩家名称："),1));
        vexComponentsList.add(new VexTextArea(110,115,30,15,4,4));
        vexComponentsList.add(new VexButton("setLoc","自定义","[local]button.png","[local]button_.png",-1,155,40,20));
        //参数 x y 编辑长度 宽度 最大字数 id
        vexComponentsList.add(new VexText(20,185,Arrays.asList("x坐标"),1));
        vexComponentsList.add(new VexTextArea(45,180,30,15,4,1));
        vexComponentsList.add(new VexText(80,185,Arrays.asList("y坐标"),1));
        vexComponentsList.add(new VexTextArea(105,180,30,15,4,2));
        vexComponentsList.add(new VexText(140,185,Arrays.asList("z坐标"),1));
        vexComponentsList.add(new VexTextArea(165,180,30,15,4,3));
        //标题
        vexComponentsList.add(new VexText(75,15,Arrays.asList("地点选择"),2));
        return new VexGui("[local]gui.png",-1,-1,220,230,vexComponentsList);
    }
}
