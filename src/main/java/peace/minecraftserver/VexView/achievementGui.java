package peace.minecraftserver.VexView;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;
import lk.vexview.tag.TagDirection;
import lk.vexview.tag.components.VexImageTag;
import org.bukkit.entity.Player;
import peace.minecraftserver.commands.CommandTop10;
import peace.minecraftserver.utils.VaultUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class achievementGui {

    public static VexGui achievement(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        //返回主界面
        vexComponentsList.add(new VexButton(4,"返回","[local]button.png","[local]button_.png",10,10,23,13,player1 -> {
            VexViewAPI.openGui(player, MainGui.main(player));
        }));

        vexComponentsList.add(new VexButton(40,"显示排名","[local]button.png","[local]button_.png",40,10,40,13,player1 -> {
            player.performCommand("playtime top10");
        }));

        vexComponentsList.add(new VexText(35,25,Arrays.asList("人气之王"),2));
        //参数 x y 图片宽 图片高
        vexComponentsList.add(new VexImage("[local]ZanTag4.png",30,100,70,30));
        vexComponentsList.add(new VexImage("[local]inv.png",25,55,90,120));
        //参数 x y 内容 字体大小
        vexComponentsList.add(new VexText(30,70,Arrays.asList("全服人气第一","可获得此成就"),1));
        vexComponentsList.add(new VexButton("buy1","佩戴","[local]button.png","[local]button_.png",50,225,35,25,player1 -> {
            TagDirection td = new TagDirection(0,180,0,true,false);
            VexImageTag vit = new VexImageTag("成就",0,3,0,"[local]ZanTag4.png",100,50,1,1,td);
            VexViewAPI.addPlayerTag((Player) player,vit);
            player.sendMessage("头衔佩戴成功");
        }));


        vexComponentsList.add(new VexText(160,25,Arrays.asList("独孤求败"),2));
        vexComponentsList.add(new VexImage("[local]Du1.png",155,100,70,30));
        vexComponentsList.add(new VexImage("[local]inv.png",150,55,90,120));
        vexComponentsList.add(new VexText(155,70,Arrays.asList("等级大于10","可获得此成就"),1));
        vexComponentsList.add(new VexButton("buy2","佩戴","[local]button.png","[local]button_.png",175,225,35,25,player1 -> {
            TagDirection td = new TagDirection(0,180,0,true,false);
            VexImageTag vit = new VexImageTag("成就",0,3,0,"[local]Du1.png",100,50,1,1,td);
            VexViewAPI.addPlayerTag((Player) player,vit);
            player.sendMessage("头衔佩戴成功");
        }));

        vexComponentsList.add(new VexText(280,25,Arrays.asList("富可敌国"),2));
        vexComponentsList.add(new VexImage("[local]Fu1.png",275,100,70,30));
        vexComponentsList.add(new VexImage("[local]inv.png",270,55,90,120));
        vexComponentsList.add(new VexText(275,70,Arrays.asList("金币大于10000","可获得此成就"),1));
        vexComponentsList.add(new VexButton("buy3","佩戴","[local]button.png","[local]button_.png",295,225,35,25,player1 -> {
            if (VaultUtil.seemoney(player.getUniqueId())>10000) {
                TagDirection td = new TagDirection(0,180,0,true,false);
                VexImageTag vit = new VexImageTag("成就",0,3,0,"[local]Fu1.png",100,50,1,1,td);
                VexViewAPI.addPlayerTag((Player) player,vit);
                player.sendMessage("头衔佩戴成功");
            }else{
                player.sendMessage("金币不足10000，无法佩戴");
            }
        }));
        return new VexGui("[local]gui.png",-1,-1,400,270,vexComponentsList);
    }
}
