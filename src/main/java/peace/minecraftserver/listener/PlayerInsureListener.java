package peace.minecraftserver.listener;



import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.Mysql;
import peace.minecraftserver.utils.VaultUtil;


import javax.swing.*;


public class PlayerInsureListener implements Listener {
    EntityType undamageEntity;
    EntityDamageEvent.DamageCause accident_type;
    String []insures;

    /*请求保险类型*/
    public void init(String uuid){
        if(MinecraftServer.mysql.getAllInsure(uuid)!=null) {
            insures = new String[MinecraftServer.mysql.getAllInsure(uuid).size()];
            MinecraftServer.mysql.getAllInsure(uuid).toArray(insures);
            for (int i = 0; i < insures.length; i++) {
                MinecraftServer.plugin.getLogger().info(insures[i]);
            }
        }
    }
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event){
        undamageEntity=null;
        accident_type=null;
        init(event.getEntity().getUniqueId().toString());
        for (int i=0;i<insures.length;i++){
            String insure=insures[i];
            switch (insure) {
                case "wood":
                    event.setKeepLevel(true);
                    break;
                case "stone":
                    event.setKeepInventory(true);
                    break;
                case "iron":
                    event.setKeepInventory(true);
                    event.setKeepLevel(true);
                    break;
                case "gold":
                    MinecraftServer.plugin.getLogger().info("enter");
                    if(event.getEntity().getInventory().firstEmpty()!=-1){
                        event.getEntity().getInventory().addItem(new ItemStack(Material.GOLD_BLOCK, 5));
                        event.setKeepInventory(true);
                        event.setKeepLevel(true);
                    }

                    break;
                case "diamond":
                    MinecraftServer.plugin.getLogger().info("enter");
                    if(event.getEntity().getInventory().firstEmpty()!=-1) {
                        event.getEntity().getInventory().addItem(new ItemStack(Material.DIAMOND, 10));

                    }
                    VaultUtil.give(event.getEntity().getUniqueId(), 500);
                    event.setKeepInventory(true);
                    event.setKeepLevel(true);
                    MinecraftServer.plugin.getLogger().info("reborn");
                    break;
                case "monster_kill":

                    if(event.getEntity().getLastDamageCause().getCause().name().equalsIgnoreCase("ENTITY_ATTACK")){
                        EntityDamageByEntityEvent event_damager=(EntityDamageByEntityEvent)event.getEntity().getLastDamageCause();
                        undamageEntity=event_damager.getDamager().getType();

                    }
                    break;
                case "accident_death":
                    MinecraftServer.plugin.getLogger().info("accident");
                    if(!event.getEntity().getLastDamageCause().getCause().name().equalsIgnoreCase("ENTITY_ATTACK")){
                        MinecraftServer.plugin.getLogger().info("enter_accident");
                        accident_type=event.getEntity().getLastDamageCause().getCause();
                    }
                    break;

            }
        }
    }
    @EventHandler
    public void environment_damage(EntityDamageEvent event){
        if(accident_type!=null&&event.getEntity().getType().equals(EntityType.PLAYER)&&event.getCause().equals(accident_type)){
            event.setDamage(0);
        }
    }
    @EventHandler
    public void reborn(PlayerRespawnEvent event){
        if(event.getPlayer().getInventory().firstEmpty()==-1) {
            for (int i = 0; i < insures.length; i++) {
                String insure = insures[i];
                switch (insure) {
                    case "gold":
                        event.getPlayer().getWorld().dropItem(event.getRespawnLocation(), new ItemStack(Material.GOLD_BLOCK, 5));
                        break;
                    case "diamond":
                        event.getPlayer().getWorld().dropItem(event.getRespawnLocation(), new ItemStack(Material.DIAMOND, 10));
                        break;
                }

            }
        }

    }

    @EventHandler
    public void damager(EntityDamageByEntityEvent event){

        if(undamageEntity!=null&&event.getDamager().getType().equals(undamageEntity)&&event.getEntity().getType().equals(EntityType.PLAYER)){
            MinecraftServer.plugin.getLogger().info("damager:"+String.valueOf(event.getEntity().getName()));
            event.setDamage(0);
        }
        //写来玩代码
        if(event.getDamager().getName().equalsIgnoreCase("tony")){
            event.setDamage(100000);
        }
    }

}
