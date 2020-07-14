package peace.minecraftserver.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import peace.minecraftserver.MinecraftServer;

public class PeaceAreaUtil {
    //区域中心点坐标
    public static Location peaceAreaLocation = null;
    //距离区域多少范围为安全区域
    public static int domain = 70;
    public static boolean IsPeaceArea(Location location){
        peaceAreaLocation = new Location(location.getWorld(),0,0,0);
        //peaceAreaLocation = location.getWorld().getSpawnLocation();
        //如果该位置在安全区域内
        if(Math.abs(location.getBlockX()-peaceAreaLocation.getBlockX())<=domain && Math.abs(location.getBlockZ()-peaceAreaLocation.getBlockZ())<=domain){
            return true;
        }else{
            return false;
        }
    }

    public  static  void setArea(){
        Location location = peaceAreaLocation.clone();
        Location border_l = location.subtract(domain+10,0,domain+10);

        for(int x=0;x<=150;x++){
            for(int z=0;z<=150;z++){
                Location loc = border_l.clone().add(x,0,z);
//                if(Math.abs(l.getBlockX()-peaceAreaLocation.getBlockX())<=domain || Math.abs(l.getBlockZ()-peaceAreaLocation.getBlockZ())<=domain){
//                    MinecraftServer.plugin.getLogger().info("接下来设置区域");
//                    setDiamond(l);
//                }
                if(PeaceAreaUtil.IsPeaceArea(loc)){
                    //MinecraftServer.plugin.getLogger().info("设置钻石"+loc);
                    PeaceAreaUtil.setDiamond(loc);
                }
            }
        }

    }

    public static void setDiamond(Location location){
        int y = 200;
        Location tLoc = new Location(location.getWorld(),location.getBlockX(),y,location.getBlockZ());
        while(tLoc.getBlock().getType()== Material.AIR){
            //找到最高的不是空气的block
            tLoc.subtract(0,1,0);
        }
        //以下三十个全部设置为钻石
        for(int i=0;i<=20;i++){
           // MinecraftServer.plugin.getLogger().info("吧block编程砖石");
            tLoc.getBlock().setType(Material.DIAMOND_BLOCK);
            tLoc = tLoc.subtract(0,1,0);
        }
    }
}
