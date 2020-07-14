package peace.minecraftserver.CommendExecutor;

import com.connorlinfoot.titleapi.TitleAPI;
import com.google.common.collect.Lists;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.util.Vector;
import peace.minecraftserver.Entity.TeleHorse;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.VaultUtil;

import java.util.ArrayList;
import java.util.List;

public class RoundCommandExecutor implements CommandExecutor {
    private final MinecraftServer plugin;

    public RoundCommandExecutor(MinecraftServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            PermissionAttachment attachment = sender.addAttachment(MinecraftServer.plugin);
            if(command.getName().equalsIgnoreCase("round")){
                if(args.length==0){
                    if(VaultUtil.seemoney(((Player) sender).getUniqueId())>10000){
                        sender.sendMessage("落地特效开启");
                        attachment.setPermission("round",true);
                    }else {
                        sender.sendMessage("你没有权限,需要金币大于10000");
                    }
                }
                else if (args[0].equalsIgnoreCase("cancel")){
                    sender.sendMessage("落地特效关闭");
                    attachment.setPermission("round",false);
                }
            }

        }else {
           sender.sendMessage("You must be a player!");
            return false;
        }

        return false;
    }

}
