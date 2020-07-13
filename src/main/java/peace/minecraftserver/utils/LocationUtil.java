package peace.minecraftserver.utils;


import org.bukkit.Location;

public class LocationUtil {
    public static String getDirection(Location location){
        double yaw = location.getYaw();
        if(yaw>=45 && yaw<135){
            return "west";
        }else if(yaw>=135 && yaw<225){
            return "north";
        }else if(yaw>=225 && yaw<315) {
            return "east";
        }else if((yaw>=315 && yaw<360)||(yaw>=0 && yaw<45)) {
            return "south";
        }
        return "";
    }
}
