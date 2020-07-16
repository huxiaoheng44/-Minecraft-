package peace.minecraftserver.VexView;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexComponents;
import lk.vexview.gui.components.VexText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import peace.minecraftserver.EventListener.SaveAreaEvent;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.MysqlUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainGui {
    public static VexGui main(Player player){
        List<VexComponents> vexComponentsList = new ArrayList<>();
        vexComponentsList.add(new VexButton("businessmain","交易系统","[local]button.png","[local]button_.png",-1,40,80,20, p -> {
            VexViewAPI.openGui(player, BusinessGui.BusinessMainGui(player));
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

        vexComponentsList.add(new VexButton("special","特效系统","[local]button.png","[local]button_.png",-1,200,80,20,player1 -> {
            //直接打开辅助功能信息界面
            VexViewAPI.openGui(player, SpecialGui.specialgui(player));

        }));
        //在线时长
        String onlinetime="在线时长："+getOnlinetime(player);
        vexComponentsList.add(new VexText(120,30, Arrays.asList(onlinetime),1));
        //连续签到显示
        String arriveday="连续签到:"+getConday(player)+"天";
        vexComponentsList.add(new VexText(5,200, Arrays.asList(arriveday),1));
        //标题
        vexComponentsList.add(new VexText(77,7, Arrays.asList("平安组插件"),2));
        return new VexGui("[local]gui.png",-1,-1,250,240,vexComponentsList);
    }
    //获取用户在线时长 格式： 时  分  秒
    private static String getOnlinetime(Player player){
        int second=MinecraftServer.mysql.getSeconds(player.getUniqueId().toString()).intValue()%60;
        int minute=MinecraftServer.mysql.getSeconds(player.getUniqueId().toString()).intValue()%3600/60;
        int hour=MinecraftServer.mysql.getSeconds(player.getUniqueId().toString()).intValue()/3600;
        return hour+"小时"+minute+"分钟"+second+"秒";
    }
    public static String getConday(Player player){
        ResultSet rs = MinecraftServer.mysql.getCheckIN(player);
        int conday=0;
        if(rs!=null) {
           try {
               conday=rs.getInt("conday");
           } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return  String.valueOf(conday);
    }
}
