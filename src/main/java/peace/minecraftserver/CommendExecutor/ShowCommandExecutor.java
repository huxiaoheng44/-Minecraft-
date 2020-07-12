package peace.minecraftserver.CommendExecutor;

import lk.vexview.api.VexViewAPI;
import lk.vexview.tag.TagDirection;
import lk.vexview.tag.components.VexImageTag;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.VexView.InsureGui;
import peace.minecraftserver.VexView.MainGui;
import peace.minecraftserver.utils.TitleApi;

public class ShowCommandExecutor implements CommandExecutor {
    private final MinecraftServer plugin;

    public ShowCommandExecutor(MinecraftServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if(command.getName().equalsIgnoreCase("show")){
                plugin.getLogger().info("show命令已被检测到");
                plugin.getLogger().info("Player name:"+commandSender.getName());
                commandSender.sendMessage("Your name:"+commandSender.getName());
                Location l = ((Player) commandSender).getLocation();
                plugin.getLogger().info("Player location:\n"+"x:"+l.getBlockX()+"\ny:"+l.getBlockY()+"\nz:"+l.getBlockZ());
                commandSender.sendMessage("Your location:\n"+"x:"+l.getBlockX()+"\ny:"+l.getBlockY()+"\nz:"+l.getBlockZ());
                Player player = (Player)commandSender;
                if(strings.length==0) {
                    //自转角偏转角:
                    player.sendMessage("自转角" + String.valueOf(player.getLocation().getPitch()));
                    player.sendMessage("偏转角" + String.valueOf(player.getLocation().getYaw()));
                    player.sendMessage("方向" + String.valueOf(player.getLocation().getDirection()));
                    //粒子特效
                    Location location = player.getLocation();
                    player.sendMessage("例子特效启动");
                    for (int degree = 0; degree < 360; degree++) {

                        double radians = Math.toRadians(degree);
                        double x = Math.cos(radians);
                        double y = Math.sin(radians);
                        location.add(x, 0, y);
                        location.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 1);
                        location.subtract(x, 0, y);
                    }
                    //权限
                    if (player.isPermissionSet("vip")) {
                        player.sendMessage("用户设置了vip权限");
                    } else {
                        player.sendMessage("用户没有设置vip权限");
                    }

                    if (player.isPermissionSet("vip.v1")) {
                        player.sendMessage("用户设置了v1权限");
                    } else {
                        player.sendMessage("用户没有设置v1权限");
                    }

                    if (player.hasPermission("vip")) {
                        player.sendMessage("你有vip权限");
                    } else {
                        player.sendMessage("你没有vip权限");
                    }
                    if (player.hasPermission("vip.v1")) {
                        player.sendMessage("你有v1权限");
                    } else {
                        player.sendMessage("你没有v1权限");
                    }
                    if (player.hasPermission("vip.v2")) {
                        player.sendMessage("你有v2权限");
                    } else {
                        player.sendMessage("你没有v2权限");
                    }
                    if (player.hasPermission("vip.v3")) {
                        player.sendMessage("你有v3权限");
                    } else {
                        player.sendMessage("你没有v3权限");
                    }
                    return true;
                }else if(strings[0].equalsIgnoreCase("gui")){
                    MinecraftServer.plugin.getLogger().info("成功打开GUI");
                    VexViewAPI.openGui(player, MainGui.main(player));
                    TitleApi.sendTitle(player,4,5,4,"成功打开GUI","第一个界面");
                    return true;
                }else if(strings[0].equalsIgnoreCase("ren")){
                    TagDirection td = new TagDirection(0,180,0,true,false);
                    VexImageTag vit = new VexImageTag("成就",0,3,0,"[local]ZanTag4.png",100,50,1,1,td);
                    VexImageTag vit3 = new VexImageTag("成就",0,80,0,"[local]ZanTag4.png",100,50,1,1,td);
                    VexImageTag vit2 = new VexImageTag("成就",0,100,0,"[local]gonggao.png",4000,2000,20,20,td);
                    VexViewAPI.addPlayerTag((Player) commandSender,vit);
                    VexViewAPI.addWorldTag(((Player) commandSender).getWorld(),vit3);
                    VexViewAPI.addWorldTag(((Player) commandSender).getWorld(),vit2);
                    commandSender.sendMessage("头衔显示成功");
                    return true;
                }else if(strings[0].equalsIgnoreCase("remove")){
                    VexViewAPI.removePlayerTag((Player) commandSender,"成就");
                    commandSender.sendMessage("头衔取消成功");
                    return true;
                }else if(strings[0].equalsIgnoreCase("insure")){
                    VexViewAPI.openGui(player, InsureGui.InsureMessage(player));
                    //TitleApi.sendTitle(player,4,5,4,"成功打开GUI","第一个界面");
                    return true;
                }else if(strings[0].equalsIgnoreCase("buy")){
                    VexViewAPI.openGui(player, InsureGui.InsurePurchase(player));
                    //TitleApi.sendTitle(player,4,5,4,"成功打开GUI","第一个界面");
                    return true;
                }
            }
        }else {
            commandSender.sendMessage("You must be a player!");
            return false;
        }
        return false;
    }
}
