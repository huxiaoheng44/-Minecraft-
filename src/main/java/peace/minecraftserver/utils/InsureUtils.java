package peace.minecraftserver.utils;

import org.bukkit.entity.Player;
import peace.minecraftserver.MinecraftServer;

public class InsureUtils {
    int vilid=0;




    public int getVilid() {
        return vilid;
    }

    public void setVilid(int vilid) {
        this.vilid = vilid;
    }

    //设置保险有效时间
    public void set_expires(Player player,int time){
        setVilid(MinecraftServer.mysql.getSeconds(player.getUniqueId().toString())+time);
    }
    //放入数据库
    public void save_to_db(Player player,String gid){
        MinecraftServer.plugin.getLogger().info(player.getUniqueId().toString());
        MinecraftServer.mysql.setGuarantee(player,gid,vilid);
    }
    //判断保险是否过期
    public int is_insur_out(Player player,String gid){
        int expires=MinecraftServer.mysql.getGuarantee(player,gid);
        if(MinecraftServer.mysql.getGuarantee(player,gid)!=-1){
            if(MinecraftServer.mysql.getSeconds(player.getUniqueId().toString())>expires) {
                //player.sendMessage("保险存在且已到期");
                MinecraftServer.plugin.getLogger().info("保险存在且已到期");
                MinecraftServer.mysql.deletInsure(player.getUniqueId().toString(),gid);
                return 1;
            }
            else {
                //player.sendMessage("保险存在未到期");
                MinecraftServer.plugin.getLogger().info("保险存在未到期");
                return 0;
            }
        }
        //player.sendMessage("保险不存在");
        MinecraftServer.plugin.getLogger().info("保险不存在");
        return -1;
    }
    //是否可以购买保险
    public void could_buy(Player player,double pay,String gid,int time){
        if(pay>VaultUtil.seemoney(player.getUniqueId())){
            player.sendMessage("没有足够的金币");
        }else {
            player.sendMessage("购买成功");
            VaultUtil.pay(player.getUniqueId(),pay);
            set_expires(player, time);
            save_to_db(player, gid);
        }
    }
}
