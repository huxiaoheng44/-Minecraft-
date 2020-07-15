package peace.minecraftserver.VexView;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class lotteryGui {

    public static String PlayerlottryGui = "§2抽奖";

    public static void PlayerLotteryGui(Player player) {
        Inventory inventory1 = Bukkit.createInventory(null, 54, PlayerlottryGui);
        ItemStack itemStack10 = new ItemStack(Material.DRAGON_EGG);//龙蛋
        ItemStack itemStack11 = new ItemStack(Material.DIAMOND_HOE);//钻石锄
        ItemStack itemStack12 = new ItemStack(Material.DIAMOND_SWORD);//钻石剑
        ItemStack itemStack13 = new ItemStack(Material.DIAMOND_AXE);//钻石斧
        ItemStack itemStack14 = new ItemStack(Material.GOLDEN_APPLE);//金苹果
        ItemStack itemStack15 = new ItemStack(Material.BONE);//骨头
        ItemStack itemStack16 = new ItemStack(Material.BREAD);//面包
        ItemStack itemStack17 = new ItemStack(Material.TORCH);//火把
        ItemStack itemStack18 = new ItemStack(Material.COAL);//煤炭

        ItemMeta itemMeta10 = itemStack10.getItemMeta();
        itemStack10.setItemMeta(itemMeta10);
        inventory1.setItem(0, itemStack10);

        ItemMeta itemMeta11 = itemStack11.getItemMeta();
        itemStack11.setItemMeta(itemMeta11);
        inventory1.setItem(1, itemStack11);

        ItemMeta itemMeta12 = itemStack12.getItemMeta();
        itemStack12.setItemMeta(itemMeta12);
        inventory1.setItem(2, itemStack12);

        ItemMeta itemMeta13 = itemStack13.getItemMeta();
        itemStack13.setItemMeta(itemMeta13);
        inventory1.setItem(3, itemStack13);

        ItemMeta itemMeta14 = itemStack14.getItemMeta();
        itemStack14.setItemMeta(itemMeta14);
        inventory1.setItem(4, itemStack14);

        ItemMeta itemMeta15 = itemStack15.getItemMeta();
        itemStack15.setItemMeta(itemMeta15);
        inventory1.setItem(5, itemStack15);

        ItemMeta itemMeta16 = itemStack16.getItemMeta();
        itemStack16.setItemMeta(itemMeta16);
        inventory1.setItem(6, itemStack16);

        ItemMeta itemMeta17 = itemStack17.getItemMeta();
        itemStack17.setItemMeta(itemMeta17);
        inventory1.setItem(7, itemStack17);

        ItemMeta itemMeta18 = itemStack18.getItemMeta();
        itemStack18.setItemMeta(itemMeta18);
        inventory1.setItem(8, itemStack18);
        player.openInventory(inventory1);

        ItemStack itemStack49 = new ItemStack(Material.WOOD_BUTTON);
        ItemMeta itemMeta49 = itemStack49.getItemMeta();
        itemMeta49.setDisplayName("开始抽奖");
        itemStack49.setItemMeta((itemMeta49));
        inventory1.setItem(49, itemStack49);

    }
}