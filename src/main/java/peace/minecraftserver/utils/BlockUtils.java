package peace.minecraftserver.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BlockUtils {
    public static Block getShiftBlock(Location location, double shiftx, double shifty, double shitfz) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        x += shiftx;
        y += shifty;
        z += shitfz;
        Location shLocation = new Location(location.getWorld(),x,y,z);
        return shLocation.getBlock();
    }

    public static void createTeleStone(Player player, Location bornlocation, Location telelocation, String label){

        List<String> lores = new ArrayList<String>();
        lores.add("§2传送石");
        lores.add("§4 Label信息");  //这条lore在123之后
        lores.add("Location:"+telelocation);  //第一行lore
    }
}
