package peace.minecraftserver.VexView;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;
import org.bukkit.entity.Player;

import java.util.*;

public class InsureGui {

    //public static HashMap<UUID,Boolean> affirmMap = new HashMap<UUID, Boolean>();

    //保险信息主界面
    public static VexGui InsureMainGui(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回主界面
        vexComponentsList.add(new VexButton(4,"返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, MainGui.main(player));
        }));
        vexComponentsList.add(new VexButton(1,"购买保险","[local]button.png","[local]button_.png",-1,80,120,30,p -> {
            //直接打开购买保险界面
            VexViewAPI.openGui(player, InsureGui.InsurePurchase(player));
        }));
        vexComponentsList.add(new VexButton(2,"查看保险信息","[local]button.png","[local]button_.png",-1,140,120,30,player1 -> {
            //直接打开保险信息界面
            VexViewAPI.openGui(player, InsureGui.InsureMessage(player));
        }));
        vexComponentsList.add(new VexText(75,15,Arrays.asList("平安保险"),3));
        return new VexGui("[local]gui.png",-1,-1,250,230,vexComponentsList);
    }

    //查看保险信息界面
    public static VexGui InsureMessage(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //添加返回上一级的按钮
        vexComponentsList.add(new VexButton(2,"返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, InsureGui.InsureMainGui(player));
        }));
        vexComponentsList.add(new VexText(75,15,Arrays.asList("平安保险"),2.5));
        vexComponentsList.add(new VexImage("[local]inv.png",40,55,150,130));
        vexComponentsList.add(new VexText(50,70,Arrays.asList("黄金保险","可以获得一堆黄金礼品","达成条件意外击杀"),1.5));
        vexComponentsList.add(new VexText(75,200,Arrays.asList("过期时间"),1.5));
        vexComponentsList.add(new VexPlayerDraw(300,190,80,player.getUniqueId(),player.getName()));
        return new VexGui("[local]gui.png",-1,-1,400,230,vexComponentsList);
    }

    //购买保险界面
    public static VexGui InsurePurchase(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();

        //添加返回上一级的按钮
        vexComponentsList.add(new VexButton(2,"返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, InsureGui.InsureMainGui(player));
        }));

        //参数 x y 字体大小
        vexComponentsList.add(new VexText(35,25,Arrays.asList("钻石保险"),2));
        //参数 x y 图片宽 图片高
        vexComponentsList.add(new VexImage("[local]inv.png",25,55,90,120));
        //参数 x y 内容 字体大小
        vexComponentsList.add(new VexText(30,70,Arrays.asList("钻石保险","可以获得一钻石礼品","价格：100金币","达成条件：意外击杀"),1));
        vexComponentsList.add(new VexText(35,200,Arrays.asList("有效时间:30天"),1.5));
        vexComponentsList.add(new VexButton("buy1","购买","[local]button.png","[local]button_.png",50,225,35,25));

        vexComponentsList.add(new VexText(160,25,Arrays.asList("黄金保险"),2));
        vexComponentsList.add(new VexImage("[local]inv.png",150,55,90,120));
        vexComponentsList.add(new VexText(155,70,Arrays.asList("黄金保险","可以获得一堆黄金礼品","价格：50金币","达成条件意外击杀"),1));
        vexComponentsList.add(new VexText(160,200,Arrays.asList("有效时间:30天"),1.5));
        vexComponentsList.add(new VexButton("buy2","购买","[local]button.png","[local]button_.png",175,225,35,25));

        vexComponentsList.add(new VexText(280,25,Arrays.asList("白银保险"),2));
        vexComponentsList.add(new VexImage("[local]inv.png",270,55,90,120));
        vexComponentsList.add(new VexText(275,70,Arrays.asList("白银保险","可以获得一堆铁质礼品","价格：30金币","达成条件:意外击杀"),1));
        vexComponentsList.add(new VexText(275,200,Arrays.asList("有效时间:30天"),1.5));
        vexComponentsList.add(new VexButton("buy3","购买","[local]button.png","[local]button_.png",295,225,35,25));
        return new VexGui("[local]gui.png",-1,-1,400,270,vexComponentsList);
    }


    public static VexGui AffirmLogue(Player player, String item){
        List<VexComponents> vexComponentsList = new ArrayList<>();

        //添加中间text信息
        //使用该句子List<String> message = new ArrayList<String>();
        List<String> messages = new ArrayList<String>();
        messages.add("确认要购买吗");
        messages.add(item);
        vexComponentsList.add(new VexText(30,70,messages,1));
        vexComponentsList.add(new VexImage("[local]gui.png",-1,-1,100,100));
        vexComponentsList.add(new VexButton("affirm","确认","[local]button.png","[local]button_.png",20,50,30,15));
        vexComponentsList.add(new VexButton("cancel","取消","[local]button.png","[local]button_.png",70,50,30,15));
        return new VexGui("[local]gui.png",-1,-1,100,100,vexComponentsList);
    }



}
