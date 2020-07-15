package peace.minecraftserver.VexView;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import peace.minecraftserver.Entity.ShopItem;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.InsureUtils;

import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.utils.VaultUtil;


import javax.swing.*;
import java.util.*;

public class InsureGui {

    //public static HashMap<UUID,Boolean> affirmMap = new HashMap<UUID, Boolean>();

    //保险信息主界面
    public static VexGui InsureMainGui(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回主界面
        vexComponentsList.add(new VexButton("back","返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, MainGui.main(player));
        }));
        vexComponentsList.add(new VexButton("1","购买保险","[local]button.png","[local]button_.png",-1,80,120,30,p -> {
            //直接打开购买保险界面
            VexViewAPI.openGui(player, InsureGui.InsurePurchase(player));
        }));
        vexComponentsList.add(new VexButton("2","查看保险信息","[local]button.png","[local]button_.png",-1,140,120,30,player1 -> {
            //直接打开保险信息界面
            VexViewAPI.openGui(player, InsureGui.InsureMessage(player));
        }));
        vexComponentsList.add(new VexText(75,15,Arrays.asList("平安保险"),3));
        return new VexGui("[local]gui.png",-1,-1,250,230,vexComponentsList);
    }

    //查看保险信息界面
    public static VexGui InsureMessage(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //保险信息获取
        String []insures={"wood","stone","iron","gold","diamond","monster_kill","accident_death"};
        InsureUtils insureUtils=new InsureUtils();
        for (int i = 0; i < insures.length; i++) {
            if (insureUtils.is_insur_out(player, insures[i])==1) {
                //sender.sendMessage("§1"+insures[i] + "§1保险已到期");
            } else if(insureUtils.is_insur_out(player,insures[i])==0) {

                int seconds = MinecraftServer.mysql.getSeconds(player.getUniqueId().toString());
                int value = MinecraftServer.mysql.getGuarantee(player, insures[i]) - seconds;
                if(insures[i].equals("monster_kill")){
                    vexComponentsList.add(new VexText(50,70,Arrays.asList("怪物保险","被怪物杀死死时下一次重生","不再受到该类型的怪物伤害"),1.5));
                }
                if(insures[i].equals("gold")){
                    vexComponentsList.add(new VexText(50,70,Arrays.asList("黄金保险","用户死亡时不掉物品","且返还一定量黄金块"),1.5));
                }
                if(insures[i].equals("diamond")){
                    vexComponentsList.add(new VexText(50,70,Arrays.asList("钻石保险","用户死亡时不掉落","并返还钻石和金币"),1.5));
                }
                vexComponentsList.add(new VexText(75,200,Arrays.asList("过期时间"+value/60+"分钟"),1.5));
                break;
                //sender.sendMessage(insures[i] + "保险已购买   剩余时间：" + value/60+"分钟");
            }else if(i>insures.length) {
                vexComponentsList.add(new VexText(50,70,Arrays.asList("保险未购买"),1.5));
                //sender.sendMessage(insures[i]+"保险未购买");
            }
        }
        //添加返回上一级的按钮
        vexComponentsList.add(new VexButton("back","返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, InsureGui.InsureMainGui(player));
        }));
        vexComponentsList.add(new VexText(75,15,Arrays.asList("平安保险"),2.5));
        vexComponentsList.add(new VexImage("[local]inv.png",40,55,150,130));
//        vexComponentsList.add(new VexText(50,70,Arrays.asList("黄金保险","可以获得一堆黄金礼品","达成条件意外击杀"),1.5));
//        vexComponentsList.add(new VexText(75,200,Arrays.asList("过期时间"),1.5));
        vexComponentsList.add(new VexPlayerDraw(300,190,80,player.getUniqueId(),player.getName()));
        return new VexGui("[local]gui.png",-1,-1,400,230,vexComponentsList);
    }

    //购买保险界面
    public static VexGui InsurePurchase(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();

        //添加返回上一级的按钮
        vexComponentsList.add(new VexButton("back","返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, InsureGui.InsureMainGui(player));
        }));

        //参数 x y 字体大小
        vexComponentsList.add(new VexText(35,25,Arrays.asList("钻石保险"),2));
        //参数 x y 图片宽 图片高
        vexComponentsList.add(new VexImage("[local]inv.png",25,55,90,120));
        //参数 x y 内容 字体大小
        vexComponentsList.add(new VexText(30,70,Arrays.asList("§6钻石保险:","返还钻石和金币","价格：100金币","达成条件：角色死亡"),1));
        vexComponentsList.add(new VexText(35,200,Arrays.asList("有效时间:1小时"),1.5));
        vexComponentsList.add(new VexButton("buy1","购买","[local]button.png","[local]button_.png",50,225,35,25));

        vexComponentsList.add(new VexText(160,25,Arrays.asList("黄金保险"),2));
        vexComponentsList.add(new VexImage("[local]inv.png",150,55,90,120));
        vexComponentsList.add(new VexText(155,70,Arrays.asList("§6黄金保险:","返还黄金","价格：50金币","达成条件：角色死亡"),1));
        vexComponentsList.add(new VexText(160,200,Arrays.asList("有效时间:1小时"),1.5));
        vexComponentsList.add(new VexButton("buy2","购买","[local]button.png","[local]button_.png",175,225,35,25));

        vexComponentsList.add(new VexText(280,25,Arrays.asList("怪物保险"),2));
        vexComponentsList.add(new VexImage("[local]inv.png",270,55,90,120));
        vexComponentsList.add(new VexText(275,70,Arrays.asList("§6怪物保险:","不受该种怪物伤害","价格：100金币","达成条件:被怪物击杀"),1));
        vexComponentsList.add(new VexText(275,200,Arrays.asList("有效时间:1小时"),1.5));
        vexComponentsList.add(new VexButton("buy3","购买","[local]button.png","[local]button_.png",295,225,35,25));
        return new VexGui("[local]gui.png",-1,-1,400,270,vexComponentsList);
    }


    public static VexGui AffirmLogue(Player player, Material material){
        List<VexComponents> vexComponentsList = new ArrayList<>();

        //添加中间text信息
        //使用该句子List<String> message = new ArrayList<String>();
        List<String> messages = new ArrayList<String>();

        messages.add(ShopItem.getName(material));
        messages.add("价格"+ShopItem.getPrice(material));
        messages.add("确认要购买吗");
        vexComponentsList.add(new VexText(20,10,messages,1));
        vexComponentsList.add(new VexImage("[local]gui.png",-1,-1,90,90));
        vexComponentsList.add(new VexButton("affirm","确认","[local]button.png","[local]button_.png",10,50,30,15,player1 -> {
            if(VaultUtil.pay(player.getUniqueId(),ShopItem.getPrice(material))){
                ItemStack itemStack = new ItemStack(material);
                player.getInventory().addItem(itemStack);
                //加声音
                player.sendMessage("您本次消费"+ShopItem.getPrice(material));
                player.sendMessage("您的余额还有："+VaultUtil.seemoney(player.getUniqueId()));
                //把里面的东西变没

                player.closeInventory();
            }else{
                player.sendMessage(("余额不足"));
            }
        }));
        vexComponentsList.add(new VexButton("cancel","取消","[local]button.png","[local]button_.png",55,50,30,15,player1 -> {
            player.closeInventory();
        }));
        return new VexGui("[local]gui.png",-1,-1,90,90,vexComponentsList);
    }



}
