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
import peace.minecraftserver.Entity.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class shopGui {
    public static  String PlayershopGui = "§2商城";

    public static Inventory setItem(Inventory inventory,Material material){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ShopItem.getName(material));
        List<String> lores = new ArrayList<String>();
        int price = ShopItem.getPrice(material);
        //添加物品的具体信息
        lores.add("§5价格:"+price);  //设置价格
        itemMeta.setLore(lores); //add the lores of course
        itemStack.setItemMeta(itemMeta); //give the item the new itemmeta
        inventory.addItem(itemStack);
        return inventory;
    }

    public static Inventory playerSetItem(Inventory inventory,ItemStack itemStack){
        Material material = itemStack.getType();
        int price = ShopItem.getPrice(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ShopItem.getName(material));
        List<String> lores = new ArrayList<String>();
        //添加物品的具体信息
        lores.add("§5价格:"+price);  //设置价格
        itemMeta.setLore(lores); //add the lores of course
        itemStack.setItemMeta(itemMeta); //give the item the new itemmeta
        inventory.addItem(itemStack);
        return inventory;
    }
    public static void PlayershopGui(Player player){
          Inventory inventory = Bukkit.createInventory(null,54,PlayershopGui);
//        ItemStack itemStack0 = new ItemStack(Material.IRON_INGOT);//铁锭
//        ItemStack itemStack1 = new ItemStack(Material.GOLD_INGOT);//金锭
//        ItemStack itemStack2 = new ItemStack(Material.DIAMOND);//钻石
//        ItemStack itemStack3 = new ItemStack(Material.FISHING_ROD);//鱼竿
//        ItemStack itemStack4 = new ItemStack(Material.COOKED_CHICKEN);//熟鸡肉
//        ItemStack itemStack5 = new ItemStack(Material.COOKED_BEEF);//牛排
//        ItemStack itemStack6 = new ItemStack(Material.GLASS);//玻璃
//        ItemStack itemStack7 = new ItemStack(Material.TORCH);//火把
//        ItemStack itemStack8 = new ItemStack(Material.BOW);//弓

        shopGui.setItem(inventory,Material.IRON_INGOT);
        shopGui.setItem(inventory,Material.GOLD_INGOT);
        shopGui.setItem(inventory,Material.DIAMOND);
        shopGui.setItem(inventory,Material.FISHING_ROD);
        shopGui.setItem(inventory,Material.COOKED_CHICKEN);
        shopGui.setItem(inventory,Material.COOKED_BEEF);
        shopGui.setItem(inventory,Material.GLASS);
        shopGui.setItem(inventory,Material.TORCH);
        shopGui.setItem(inventory,Material.BOW);

//        ItemMeta itemMeta0 = itemStack0.getItemMeta();
//        itemMeta0.setDisplayName("铁锭 售价10金币");
//        itemStack0.setItemMeta(itemMeta0);
//        inventory.setItem(0,itemStack0);
//
//        ItemMeta itemMeta1 = itemStack1.getItemMeta();
//        itemMeta1.setDisplayName("§5金锭 售价30金币");
//        itemStack1.setItemMeta(itemMeta1);
//        inventory.setItem(1,itemStack1);
//
//        ItemMeta itemMeta2 = itemStack2.getItemMeta();
//        itemMeta2.setDisplayName("§5钻石 售价100金币");
//        itemStack2.setItemMeta(itemMeta2);
//        inventory.setItem(2,itemStack2);
//
//        ItemMeta itemMeta3 = itemStack3.getItemMeta();
//        itemMeta3.setDisplayName("§5鱼竿 售价50金币");
//        itemStack3.setItemMeta(itemMeta3);
//        inventory.setItem(3,itemStack3);
//
//        ItemMeta itemMeta4 = itemStack4.getItemMeta();
//        itemMeta1.setDisplayName("§5熟鸡肉 售价6金币");
//        itemStack4.setItemMeta(itemMeta4);
//        inventory.setItem(4,itemStack4);
//
//        ItemMeta itemMeta5 = itemStack5.getItemMeta();
//        itemMeta5.setDisplayName("§5牛排 售价8金币");
//        itemStack5.setItemMeta(itemMeta5);
//        inventory.setItem(5,itemStack5);
//
//        ItemMeta itemMeta6 = itemStack6.getItemMeta();
//        itemMeta6.setDisplayName("§5玻璃 售价1金币");
//        itemStack6.setItemMeta(itemMeta6);
//        inventory.setItem(6,itemStack6);
//
//        ItemMeta itemMeta7 = itemStack7.getItemMeta();
//        itemMeta7.setDisplayName("§5火把 售价2金币");
//        itemStack7.setItemMeta(itemMeta7);
//        inventory.setItem(7,itemStack7);
//
//        ItemMeta itemMeta8 = itemStack8.getItemMeta();
//        itemMeta8.setDisplayName("§5弓 售价100金币");
//        itemStack8.setItemMeta(itemMeta8);
//        inventory.setItem(8,itemStack8);
//        player.openInventory(inventory);

        ItemStack itemStack49 = new ItemStack(Material.WOOD_BUTTON);
        ItemMeta itemMeta49 = itemStack49.getItemMeta();
        itemMeta49.setDisplayName("抽奖界面");
        itemStack49.setItemMeta((itemMeta49));
        inventory.setItem(49,itemStack49);

    }
}