package peace.minecraftserver.Entity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class ShopItem {
    private static HashMap<Material, Integer> priceMap = new HashMap<Material, Integer>();
    private static HashMap<Material, String> nameMap = new HashMap<Material, String>();

    private static HashMap<String, Material> nameMaterial = new HashMap<String, Material>();

    public static void init(){


        nameMap.put(Material.IRON_INGOT,"铁锭");
        priceMap.put(Material.IRON_INGOT,10);

        nameMap.put(Material.GOLD_INGOT,"金锭");
        priceMap.put(Material.GOLD_INGOT,30);

        nameMap.put(Material.DIAMOND,"钻石");
        priceMap.put(Material.DIAMOND,100);

        nameMap.put(Material.FISHING_ROD,"鱼竿");
        priceMap.put(Material.FISHING_ROD,50);

        nameMap.put(Material.COOKED_CHICKEN,"熟鸡肉");
        priceMap.put(Material.COOKED_CHICKEN,6);

        nameMap.put(Material.COOKED_BEEF,"牛排");
        priceMap.put(Material.COOKED_BEEF,6);

        nameMap.put(Material.GLASS,"玻璃");
        priceMap.put(Material.GLASS,5);

        nameMap.put(Material.TORCH,"火把");
        priceMap.put(Material.TORCH,1);

        nameMap.put(Material.BOW,"弓");
        priceMap.put(Material.BOW,50);

        nameMap.put(Material.DIRT,"泥土块");
        priceMap.put(Material.DIRT,1);


        nameMap.put(Material.SAND,"沙子");
        priceMap.put(Material.SAND,1);

        nameMap.put(Material.STONE,"石头");
        priceMap.put(Material.STONE,1);

        nameMap.put(Material.DIAMOND_AXE,"钻石斧");
        priceMap.put(Material.DIAMOND_AXE,500);

        nameMap.put(Material.DIAMOND_SWORD,"钻石剑");
        priceMap.put(Material.DIAMOND_SWORD,500);

        nameMap.put(Material.DIAMOND_HOE,"钻石锄");
        priceMap.put(Material.DIAMOND_HOE,500);

        nameMap.put(Material.DIAMOND_SPADE,"钻石铲");
        priceMap.put(Material.DIAMOND_SPADE,500);

        nameMap.put(Material.DIAMOND_PICKAXE,"钻石镐");
        priceMap.put(Material.DIAMOND_PICKAXE,500);

        nameMap.put(Material.ARROW,"箭");
        priceMap.put(Material.ARROW,1);
//        nameMap.put("STONE","石头");
//        priceMap.put("STONE",1);
//
//        nameMap.put("STONE","石头");
//        priceMap.put("STONE",1);
//
//        nameMap.put("STONE","石头");
//        priceMap.put("STONE",1);
//
//        nameMap.put("STONE","石头");
//        priceMap.put("STONE",1);


    }
    public static String getName(Material item){
        return nameMap.get(item);
    }
    public static int getPrice(Material item){
        return priceMap.get(item);
    }
}
