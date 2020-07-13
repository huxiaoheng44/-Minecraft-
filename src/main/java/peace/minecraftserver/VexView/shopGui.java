package peace.minecraftserver.VexView;

import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexComponents;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class shopGui {
    public static  String PlayershopGui = "§2商城";

    public static void PlayershopGui(Player player){
        Inventory inventory = Bukkit.createInventory(null,54,PlayershopGui);
        ItemStack itemStack0 = new ItemStack(Material.IRON_INGOT);//铁锭
        ItemStack itemStack1 = new ItemStack(Material.GOLD_INGOT);//金锭
        ItemStack itemStack2 = new ItemStack(Material.DIAMOND);//钻石
        ItemStack itemStack3 = new ItemStack(Material.FISHING_ROD);//恐龙蛋
        ItemStack itemStack4 = new ItemStack(Material.COOKED_CHICKEN);//熟鸡肉
        ItemStack itemStack5 = new ItemStack(Material.COOKED_BEEF);//牛排
        ItemStack itemStack6 = new ItemStack(Material.BED);//床
        ItemStack itemStack7 = new ItemStack(Material.TORCH);//火把
        ItemStack itemStack8 = new ItemStack(Material.BOW);//弓

        ItemMeta itemMeta0 = itemStack0.getItemMeta();
        itemMeta0.setDisplayName("铁锭 售价10金币");
        itemStack0.setItemMeta(itemMeta0);
        inventory.setItem(0,itemStack0);

        ItemMeta itemMeta1 = itemStack1.getItemMeta();
        itemMeta1.setDisplayName("§5金锭 售价30金币");
        itemStack1.setItemMeta(itemMeta1);
        inventory.setItem(1,itemStack1);

        ItemMeta itemMeta2 = itemStack1.getItemMeta();
        itemMeta2.setDisplayName("§5钻石 售价100金币");
        itemStack2.setItemMeta(itemMeta2);
        inventory.setItem(2,itemStack2);

        ItemMeta itemMeta3 = itemStack1.getItemMeta();
        itemMeta3.setDisplayName("§5鱼竿 售价50金币");
        itemStack3.setItemMeta(itemMeta3);
        inventory.setItem(3,itemStack3);

        ItemMeta itemMeta4 = itemStack1.getItemMeta();
        itemMeta1.setDisplayName("§5熟鸡肉 售价6金币");
        itemStack4.setItemMeta(itemMeta4);
        inventory.setItem(4,itemStack4);

        ItemMeta itemMeta5 = itemStack1.getItemMeta();
        itemMeta5.setDisplayName("§5牛排 售价8金币");
        itemStack5.setItemMeta(itemMeta5);
        inventory.setItem(5,itemStack5);

        ItemMeta itemMeta6 = itemStack1.getItemMeta();
        itemMeta6.setDisplayName("§5床 售价50金币");
        itemStack6.setItemMeta(itemMeta6);
        inventory.setItem(6,itemStack6);

        ItemMeta itemMeta7 = itemStack1.getItemMeta();
        itemMeta7.setDisplayName("§5火把 售价2金币");
        itemStack7.setItemMeta(itemMeta7);
        inventory.setItem(7,itemStack7);

        ItemMeta itemMeta8 = itemStack1.getItemMeta();
        itemMeta8.setDisplayName("§5弓 售价100金币");
        itemStack8.setItemMeta(itemMeta8);
        inventory.setItem(8,itemStack8);
        player.openInventory(inventory);

        ItemStack itemStack49 = new ItemStack(Material.WOOD_BUTTON);
        ItemMeta itemMeta49 = itemStack49.getItemMeta();
        itemMeta49.setDisplayName("购买");
        itemStack49.setItemMeta((itemMeta49));
        inventory.setItem(49,itemStack49);

    }
}