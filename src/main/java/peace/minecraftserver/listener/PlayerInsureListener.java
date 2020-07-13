package peace.minecraftserver.listener;



import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import peace.minecraftserver.MinecraftServer;
import peace.minecraftserver.utils.InsureUtils;
import peace.minecraftserver.utils.Mysql;
import peace.minecraftserver.utils.VaultUtil;


import javax.swing.*;


public class PlayerInsureListener implements Listener {
    EntityType undamageEntity;
    EntityDamageEvent.DamageCause accident_type;
    String []insures;
    InsureUtils insureUtils=new InsureUtils();

    /*请求保险类型*/
    public void init(String uuid){
        if(MinecraftServer.mysql.getAllInsure(uuid)!=null) {
            insures = new String[MinecraftServer.mysql.getAllInsure(uuid).size()];
            MinecraftServer.mysql.getAllInsure(uuid).toArray(insures);
        }
    }
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event){
        undamageEntity=null;
        accident_type=null;
        init(event.getEntity().getUniqueId().toString());
        for (int i=0;i<insures.length;i++){
            String insure=insures[i];
            if(insureUtils.is_insur_out(event.getEntity(),insure))
                continue;
            switch (insure) {
                case "wood":
                    event.setKeepLevel(true);
                    MinecraftServer.mysql.deletInsure(event.getEntity().getUniqueId().toString(),insure);
                    break;
                case "stone":
                    event.setKeepInventory(true);
                    MinecraftServer.mysql.deletInsure(event.getEntity().getUniqueId().toString(),insure);
                    break;
                case "iron":
                    event.setKeepInventory(true);
                    event.setKeepLevel(true);
                    MinecraftServer.mysql.deletInsure(event.getEntity().getUniqueId().toString(),insure);
                    break;
                case "gold":
                    MinecraftServer.plugin.getLogger().info("enter");
                    if(event.getEntity().getInventory().firstEmpty()!=-1){
                        event.getEntity().getInventory().addItem(new ItemStack(Material.GOLD_BLOCK, 5));
                        event.setKeepInventory(true);
                        event.setKeepLevel(true);
                        MinecraftServer.mysql.deletInsure(event.getEntity().getUniqueId().toString(),insure);
                    }

                    break;
                case "diamond":
                    MinecraftServer.plugin.getLogger().info("enter");
                    if(event.getEntity().getInventory().firstEmpty()!=-1) {
                        event.getEntity().getInventory().addItem(new ItemStack(Material.DIAMOND, 10));
                        MinecraftServer.mysql.deletInsure(event.getEntity().getUniqueId().toString(),insure);
                    }
                    VaultUtil.give(event.getEntity().getUniqueId(), 10);
                    event.setKeepInventory(true);
                    event.setKeepLevel(true);
                    MinecraftServer.plugin.getLogger().info("reborn");
                    break;
                case "monster_kill":

                    if(event.getEntity().getLastDamageCause().getCause().name().equalsIgnoreCase("ENTITY_ATTACK")){
                        EntityDamageByEntityEvent event_damager=(EntityDamageByEntityEvent)event.getEntity().getLastDamageCause();
                        undamageEntity=event_damager.getDamager().getType();

                    }
                    MinecraftServer.mysql.deletInsure(event.getEntity().getUniqueId().toString(),insure);
                    break;
                case "accident_death":
                    MinecraftServer.plugin.getLogger().info("accident");
                    if(!event.getEntity().getLastDamageCause().getCause().name().equalsIgnoreCase("ENTITY_ATTACK")){
                        MinecraftServer.plugin.getLogger().info("enter_accident");
                        accident_type=event.getEntity().getLastDamageCause().getCause();
                    }
                    MinecraftServer.mysql.deletInsure(event.getEntity().getUniqueId().toString(),insure);
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
                if(insureUtils.is_insur_out(event.getPlayer(),insure))
                    continue;
                switch (insure) {
                    case "gold":
                        event.getPlayer().getWorld().dropItem(event.getRespawnLocation(), new ItemStack(Material.GOLD_BLOCK, 5));
                        break;
                    case "diamond":
                        event.getPlayer().getWorld().dropItem(event.getRespawnLocation(), new ItemStack(Material.DIAMOND, 10));
                        break;
                }

                MinecraftServer.mysql.deletInsure(event.getPlayer().getUniqueId().toString(),insure);
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
//    @EventHandler
//    public void test(PlayerInteractEvent event){
//        if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
//            event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(),EntityType.HUSK);
//        }
//        if (event.getAction().equals(Action.LEFT_CLICK_AIR)){
//            InsureUtils insureUtils = new InsureUtils();
//            insureUtils.is_insur_out(event.getPlayer(),"diamond");
//        }
//    }
}
