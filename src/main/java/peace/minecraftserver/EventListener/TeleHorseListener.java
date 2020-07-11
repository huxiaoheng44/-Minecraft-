package peace.minecraftserver.EventListener;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.scheduler.BukkitRunnable;
import peace.minecraftserver.Entity.TeleHorse;
import peace.minecraftserver.MinecraftServer;

public class TeleHorseListener implements Listener {
    @EventHandler
    public void rideTeleHorse(VehicleEnterEvent event) throws InterruptedException {
        Entity vehicle = event.getVehicle();
        Player player = (Player) event.getEntered();
        World world = player.getWorld();
        Location sourseLocation = vehicle.getLocation();
        Location location = TeleHorse.getTargetLoaction(vehicle.getUniqueId());
        if(vehicle.getName().equalsIgnoreCase("TeleHorse")) {
            if (location != null && player.getUniqueId()==TeleHorse.getPlayer(vehicle.getUniqueId())) {
                while (world.getBlockAt(location).getType() != Material.AIR) {
                    location.add(0, 1, 0);
                }
                new BukkitRunnable() {
                    int s = 3;
                    @Override
                    public void run() {
                        player.sendMessage("传送倒计时:"+s);
                        s--;
                        if(vehicle.getPassengers().isEmpty()){
                            player.sendMessage("传送取消！");
                            cancel();
                        }
                        else if(s==0 && vehicle.getPassengers()!=null) {
                            player.teleport(location);
                            vehicle.teleport(location.add(0,2,0));
                            //vehicle.addPassenger(player);
                            player.sendMessage("传送成功！");
                            //设置返回地点
                            TeleHorse.setLocationMap(vehicle.getUniqueId(),sourseLocation);
                            TeleHorse.SubtractLeftTimes(vehicle.getUniqueId());

                            if(TeleHorse.getLeftTimes(vehicle.getUniqueId())<=0) {
                                player.sendMessage("你的传送马使用次数已耗尽");
                                TeleHorse.removeEntity(vehicle.getUniqueId());
                                vehicle.remove();
                            }
                            cancel();
                        }

                    }
                }.runTaskTimer(MinecraftServer.plugin,0L,20L);

            } else {
                player.sendMessage("传送失败！");
            }
        }
    }
}
