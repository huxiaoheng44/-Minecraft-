package peace.minecraftserver.utils;

import org.bukkit.Location;

public class PeaceAreaUtil {
    //区域中心点坐标
    public static Location peaceAreaLocation = null;
    //距离区域多少范围为安全区域
    public static int domain = 30;
    public static boolean IsPeaceArea(Location location){
        peaceAreaLocation = new Location(location.getWorld(),0,0,0);
        //如果该位置在安全区域内
        if(Math.abs(location.getBlockX()-peaceAreaLocation.getBlockX())<=domain && Math.abs(location.getBlockZ()-peaceAreaLocation.getBlockZ())<=domain){
            return true;
        }else{
            return false;
        }
    }
}
